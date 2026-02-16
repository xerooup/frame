package io.github.xerooup.frame3d.graphics.mesh

import io.github.xerooup.frame3d.graphics.Color
import kotlin.math.*

class SphereMesh(
    private val radius: Float = 0.5f,
    private val segments: Int = 16,
    private val rings: Int = 16,
    color: Color = Color.WHITE
) : BaseMesh() {

    constructor() : this(0.5f, 16, 16, Color.WHITE)
    constructor(color: Color) : this(0.5f, 16, 16, color)

    private val vertices: FloatArray
    private val indices: IntArray

    init {
        val vertexList = mutableListOf<Float>()
        val indexList = mutableListOf<Int>()

        val r = color.r
        val g = color.g
        val b = color.b

        // Generate vertices
        for (ring in 0..rings) {
            val theta = ring * PI.toFloat() / rings
            val sinTheta = sin(theta)
            val cosTheta = cos(theta)

            for (segment in 0..segments) {
                val phi = segment * 2 * PI.toFloat() / segments
                val sinPhi = sin(phi)
                val cosPhi = cos(phi)

                val x = cosPhi * sinTheta
                val y = cosTheta
                val z = sinPhi * sinTheta

                val u = segment.toFloat() / segments
                val v = ring.toFloat() / rings

                // Position
                vertexList.add(x * radius)
                vertexList.add(y * radius)
                vertexList.add(z * radius)

                // Color
                vertexList.add(r)
                vertexList.add(g)
                vertexList.add(b)

                // Normal
                vertexList.add(x)
                vertexList.add(y)
                vertexList.add(z)

                // Texture coordinate
                vertexList.add(u)
                vertexList.add(v)
            }
        }

        // Generate indices
        for (ring in 0 until rings) {
            for (segment in 0 until segments) {
                val current = ring * (segments + 1) + segment
                val next = current + segments + 1

                indexList.add(current)
                indexList.add(next)
                indexList.add(current + 1)

                indexList.add(current + 1)
                indexList.add(next)
                indexList.add(next + 1)
            }
        }

        vertices = vertexList.toFloatArray()
        indices = indexList.toIntArray()

        setup(vertices, indices)
    }

    override fun getVertices() = vertices
    override fun getIndices() = indices
}