package io.github.xerooup.frame3d.graphics.mesh

import io.github.xerooup.frame3d.graphics.Color

class BoxMesh(color: Color = Color.WHITE) : BaseMesh() {
    constructor() : this(Color.WHITE)
    constructor(width: Float, height: Float, depth: Float, color: Color) : this(color) {
        // Recreate with custom size
        val w = width / 2
        val h = height / 2
        val d = depth / 2
        val r = color.r
        val g = color.g
        val b = color.b

        val customVertices = floatArrayOf(
            // Front face
            -w, -h,  d,  r, g, b,  0f, 0f, 1f,  0f, 0f,
            w, -h,  d,  r, g, b,  0f, 0f, 1f,  1f, 0f,
            w,  h,  d,  r, g, b,  0f, 0f, 1f,  1f, 1f,
            -w,  h,  d,  r, g, b,  0f, 0f, 1f,  0f, 1f,

            // Back face
            -w, -h, -d,  r, g, b,  0f, 0f, -1f,  1f, 0f,
            w, -h, -d,  r, g, b,  0f, 0f, -1f,  0f, 0f,
            w,  h, -d,  r, g, b,  0f, 0f, -1f,  0f, 1f,
            -w,  h, -d,  r, g, b,  0f, 0f, -1f,  1f, 1f,

            // Top face
            -w,  h, -d,  r, g, b,  0f, 1f, 0f,  0f, 0f,
            -w,  h,  d,  r, g, b,  0f, 1f, 0f,  0f, 1f,
            w,  h,  d,  r, g, b,  0f, 1f, 0f,  1f, 1f,
            w,  h, -d,  r, g, b,  0f, 1f, 0f,  1f, 0f,

            // Bottom face
            -w, -h, -d,  r, g, b,  0f, -1f, 0f,  0f, 1f,
            w, -h, -d,  r, g, b,  0f, -1f, 0f,  1f, 1f,
            w, -h,  d,  r, g, b,  0f, -1f, 0f,  1f, 0f,
            -w, -h,  d,  r, g, b,  0f, -1f, 0f,  0f, 0f,

            // Right face
            w, -h, -d,  r, g, b,  1f, 0f, 0f,  0f, 0f,
            w,  h, -d,  r, g, b,  1f, 0f, 0f,  0f, 1f,
            w,  h,  d,  r, g, b,  1f, 0f, 0f,  1f, 1f,
            w, -h,  d,  r, g, b,  1f, 0f, 0f,  1f, 0f,

            // Left face
            -w, -h, -d,  r, g, b,  -1f, 0f, 0f,  1f, 0f,
            -w, -h,  d,  r, g, b,  -1f, 0f, 0f,  0f, 0f,
            -w,  h,  d,  r, g, b,  -1f, 0f, 0f,  0f, 1f,
            -w,  h, -d,  r, g, b,  -1f, 0f, 0f,  1f, 1f
        )

        setup(customVertices, indices)
    }

    private val w = 0.5f
    private val h = 0.5f
    private val d = 0.5f
    private val r = color.r
    private val g = color.g
    private val b = color.b

    private val vertices = floatArrayOf(
        // Front face
        -w, -h,  d,  r, g, b,  0f, 0f, 1f,  0f, 0f,
        w, -h,  d,  r, g, b,  0f, 0f, 1f,  1f, 0f,
        w,  h,  d,  r, g, b,  0f, 0f, 1f,  1f, 1f,
        -w,  h,  d,  r, g, b,  0f, 0f, 1f,  0f, 1f,

        // Back face
        -w, -h, -d,  r, g, b,  0f, 0f, -1f,  1f, 0f,
        w, -h, -d,  r, g, b,  0f, 0f, -1f,  0f, 0f,
        w,  h, -d,  r, g, b,  0f, 0f, -1f,  0f, 1f,
        -w,  h, -d,  r, g, b,  0f, 0f, -1f,  1f, 1f,

        // Top face
        -w,  h, -d,  r, g, b,  0f, 1f, 0f,  0f, 0f,
        -w,  h,  d,  r, g, b,  0f, 1f, 0f,  0f, 1f,
        w,  h,  d,  r, g, b,  0f, 1f, 0f,  1f, 1f,
        w,  h, -d,  r, g, b,  0f, 1f, 0f,  1f, 0f,

        // Bottom face
        -w, -h, -d,  r, g, b,  0f, -1f, 0f,  0f, 1f,
        w, -h, -d,  r, g, b,  0f, -1f, 0f,  1f, 1f,
        w, -h,  d,  r, g, b,  0f, -1f, 0f,  1f, 0f,
        -w, -h,  d,  r, g, b,  0f, -1f, 0f,  0f, 0f,

        // Right face
        w, -h, -d,  r, g, b,  1f, 0f, 0f,  0f, 0f,
        w,  h, -d,  r, g, b,  1f, 0f, 0f,  0f, 1f,
        w,  h,  d,  r, g, b,  1f, 0f, 0f,  1f, 1f,
        w, -h,  d,  r, g, b,  1f, 0f, 0f,  1f, 0f,

        // Left face
        -w, -h, -d,  r, g, b,  -1f, 0f, 0f,  1f, 0f,
        -w, -h,  d,  r, g, b,  -1f, 0f, 0f,  0f, 0f,
        -w,  h,  d,  r, g, b,  -1f, 0f, 0f,  0f, 1f,
        -w,  h, -d,  r, g, b,  -1f, 0f, 0f,  1f, 1f
    )

    private val indices = intArrayOf(
        0,  1,  2,  2,  3,  0,   // Front
        4,  5,  6,  6,  7,  4,   // Back
        8,  9, 10, 10, 11,  8,   // Top
        12, 13, 14, 14, 15, 12,  // Bottom
        16, 17, 18, 18, 19, 16,  // Right
        20, 21, 22, 22, 23, 20   // Left
    )

    init {
        setup(vertices, indices)
    }

    override fun getVertices() = vertices
    override fun getIndices() = indices
}