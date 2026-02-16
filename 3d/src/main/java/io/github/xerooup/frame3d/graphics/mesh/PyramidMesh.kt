package io.github.xerooup.frame3d.graphics.mesh

import io.github.xerooup.frame3d.graphics.Color

class PyramidMesh(
    private val baseSize: Float = 1.0f,
    private val height: Float = 1.0f,
    color: Color = Color.WHITE
) : BaseMesh() {

    constructor() : this(1.0f, 1.0f, Color.WHITE)
    constructor(color: Color) : this(1.0f, 1.0f, color)

    private val vertices: FloatArray
    private val indices: IntArray

    init {
        val halfBase = baseSize / 2
        val halfHeight = height / 2
        val r = color.r
        val g = color.g
        val b = color.b

        vertices = floatArrayOf(
            // Top vertex (apex)
            0.0f, halfHeight, 0.0f,  r, g, b,  0f, 1f, 0f,  0.5f, 0.5f,

            // Base vertices
            -halfBase, -halfHeight, halfBase,  r, g, b,  0f, -1f, 0f,  0f, 0f,
            halfBase, -halfHeight, halfBase,  r, g, b,  0f, -1f, 0f,  1f, 0f,
            halfBase, -halfHeight, -halfBase,  r, g, b,  0f, -1f, 0f,  1f, 1f,
            -halfBase, -halfHeight, -halfBase,  r, g, b,  0f, -1f, 0f,  0f, 1f,

            // Side faces with proper normals
            // Front face
            0.0f, halfHeight, 0.0f,  r, g, b,  0f, 0.6f, 0.8f,  0.5f, 1.0f,
            -halfBase, -halfHeight, halfBase,  r, g, b,  0f, 0.6f, 0.8f,  0.0f, 0.0f,
            halfBase, -halfHeight, halfBase,  r, g, b,  0f, 0.6f, 0.8f,  1.0f, 0.0f,

            // Right face
            0.0f, halfHeight, 0.0f,  r, g, b,  0.8f, 0.6f, 0f,  0.5f, 1.0f,
            halfBase, -halfHeight, halfBase,  r, g, b,  0.8f, 0.6f, 0f,  0.0f, 0.0f,
            halfBase, -halfHeight, -halfBase,  r, g, b,  0.8f, 0.6f, 0f,  1.0f, 0.0f,

            // Back face
            0.0f, halfHeight, 0.0f,  r, g, b,  0f, 0.6f, -0.8f,  0.5f, 1.0f,
            halfBase, -halfHeight, -halfBase,  r, g, b,  0f, 0.6f, -0.8f,  0.0f, 0.0f,
            -halfBase, -halfHeight, -halfBase,  r, g, b,  0f, 0.6f, -0.8f,  1.0f, 0.0f,

            // Left face
            0.0f, halfHeight, 0.0f,  r, g, b,  -0.8f, 0.6f, 0f,  0.5f, 1.0f,
            -halfBase, -halfHeight, -halfBase,  r, g, b,  -0.8f, 0.6f, 0f,  0.0f, 0.0f,
            -halfBase, -halfHeight, halfBase,  r, g, b,  -0.8f, 0.6f, 0f,  1.0f, 0.0f
        )

        indices = intArrayOf(
            // Base
            1, 2, 3,
            3, 4, 1,

            // Faces
            5, 6, 7,
            8, 9, 10,
            11, 12, 13,
            14, 15, 16
        )

        setup(vertices, indices)
    }

    override fun getVertices() = vertices
    override fun getIndices() = indices
}