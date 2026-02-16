package io.github.xerooup.frame3d.graphics.mesh

import io.github.xerooup.frame3d.graphics.Color
import kotlin.math.*

class CylinderMesh(
    private val radius: Float = 0.5f,
    private val height: Float = 1.0f,
    private val segments: Int = 32,
    color: Color = Color.WHITE
) : BaseMesh() {

    constructor() : this(0.5f, 1.0f, 32, Color.WHITE)
    constructor(color: Color) : this(0.5f, 1.0f, 32, color)

    private val vertices: FloatArray
    private val indices: IntArray

    init {
        val vertexList = mutableListOf<Float>()
        val indexList = mutableListOf<Int>()

        val r = color.r
        val g = color.g
        val b = color.b
        val halfHeight = height / 2

        // Side vertices
        for (i in 0..segments) {
            val angle = i * 2 * PI.toFloat() / segments
            val x = cos(angle) * radius
            val z = sin(angle) * radius
            val nx = cos(angle)
            val nz = sin(angle)
            val u = i.toFloat() / segments

            // Top vertex
            vertexList.addAll(listOf(x, halfHeight, z, r, g, b, nx, 0f, nz, u, 1f))
            // Bottom vertex
            vertexList.addAll(listOf(x, -halfHeight, z, r, g, b, nx, 0f, nz, u, 0f))
        }

        // Side indices
        for (i in 0 until segments) {
            val topLeft = i * 2
            val bottomLeft = topLeft + 1
            val topRight = topLeft + 2
            val bottomRight = topRight + 1

            indexList.addAll(listOf(topLeft, bottomLeft, topRight))
            indexList.addAll(listOf(topRight, bottomLeft, bottomRight))
        }

        val capStartIndex = vertexList.size / 11

        // Top cap center
        vertexList.addAll(listOf(0f, halfHeight, 0f, r, g, b, 0f, 1f, 0f, 0.5f, 0.5f))
        val topCenterIndex = capStartIndex

        // Top cap vertices
        for (i in 0..segments) {
            val angle = i * 2 * PI.toFloat() / segments
            val x = cos(angle) * radius
            val z = sin(angle) * radius
            val u = (cos(angle) + 1) * 0.5f
            val v = (sin(angle) + 1) * 0.5f

            vertexList.addAll(listOf(x, halfHeight, z, r, g, b, 0f, 1f, 0f, u, v))
        }

        // Top cap indices
        for (i in 0 until segments) {
            indexList.addAll(listOf(topCenterIndex, topCenterIndex + i + 1, topCenterIndex + i + 2))
        }

        val bottomCapStart = vertexList.size / 11

        // Bottom cap center
        vertexList.addAll(listOf(0f, -halfHeight, 0f, r, g, b, 0f, -1f, 0f, 0.5f, 0.5f))
        val bottomCenterIndex = bottomCapStart

        // Bottom cap vertices
        for (i in 0..segments) {
            val angle = i * 2 * PI.toFloat() / segments
            val x = cos(angle) * radius
            val z = sin(angle) * radius
            val u = (cos(angle) + 1) * 0.5f
            val v = (sin(angle) + 1) * 0.5f

            vertexList.addAll(listOf(x, -halfHeight, z, r, g, b, 0f, -1f, 0f, u, v))
        }

        // Bottom cap indices (reversed)
        for (i in 0 until segments) {
            indexList.addAll(listOf(bottomCenterIndex, bottomCenterIndex + i + 2, bottomCenterIndex + i + 1))
        }

        vertices = vertexList.toFloatArray()
        indices = indexList.toIntArray()

        setup(vertices, indices)
    }

    override fun getVertices() = vertices
    override fun getIndices() = indices
}