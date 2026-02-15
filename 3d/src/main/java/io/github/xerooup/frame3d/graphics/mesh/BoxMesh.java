package io.github.xerooup.frame3d.graphics.mesh;

import io.github.xerooup.frame3d.graphics.Color;

public class BoxMesh extends BaseMesh {
    private final float[] vertices;
    private final int[] indices;

    public BoxMesh() {
        this(Color.WHITE);
    }

    public BoxMesh(Color color) {
        this(1.0f, 1.0f, 1.0f, color);
    }

    public BoxMesh(float width, float height, float depth, Color color) {
        float w = width / 2;
        float h = height / 2;
        float d = depth / 2;
        float r = color.r;
        float g = color.g;
        float b = color.b;

        // Vertices: position (3) + color (3) + normal (3) + texCoord (2) = 11 floats
        vertices = new float[] {
                // Front face (normal: 0, 0, 1)
                -w, -h,  d,  r, g, b,  0, 0, 1,  0, 0,
                w, -h,  d,  r, g, b,  0, 0, 1,  1, 0,
                w,  h,  d,  r, g, b,  0, 0, 1,  1, 1,
                -w,  h,  d,  r, g, b,  0, 0, 1,  0, 1,

                // Back face (normal: 0, 0, -1)
                -w, -h, -d,  r, g, b,  0, 0, -1,  1, 0,
                w, -h, -d,  r, g, b,  0, 0, -1,  0, 0,
                w,  h, -d,  r, g, b,  0, 0, -1,  0, 1,
                -w,  h, -d,  r, g, b,  0, 0, -1,  1, 1,

                // Top face (normal: 0, 1, 0)
                -w,  h, -d,  r, g, b,  0, 1, 0,  0, 0,
                -w,  h,  d,  r, g, b,  0, 1, 0,  0, 1,
                w,  h,  d,  r, g, b,  0, 1, 0,  1, 1,
                w,  h, -d,  r, g, b,  0, 1, 0,  1, 0,

                // Bottom face (normal: 0, -1, 0)
                -w, -h, -d,  r, g, b,  0, -1, 0,  0, 1,
                w, -h, -d,  r, g, b,  0, -1, 0,  1, 1,
                w, -h,  d,  r, g, b,  0, -1, 0,  1, 0,
                -w, -h,  d,  r, g, b,  0, -1, 0,  0, 0,

                // Right face (normal: 1, 0, 0)
                w, -h, -d,  r, g, b,  1, 0, 0,  0, 0,
                w,  h, -d,  r, g, b,  1, 0, 0,  0, 1,
                w,  h,  d,  r, g, b,  1, 0, 0,  1, 1,
                w, -h,  d,  r, g, b,  1, 0, 0,  1, 0,

                // Left face (normal: -1, 0, 0)
                -w, -h, -d,  r, g, b,  -1, 0, 0,  1, 0,
                -w, -h,  d,  r, g, b,  -1, 0, 0,  0, 0,
                -w,  h,  d,  r, g, b,  -1, 0, 0,  0, 1,
                -w,  h, -d,  r, g, b,  -1, 0, 0,  1, 1
        };

        indices = new int[] {
                0,  1,  2,  2,  3,  0,   // Front
                4,  5,  6,  6,  7,  4,   // Back
                8,  9, 10, 10, 11,  8,   // Top
                12, 13, 14, 14, 15, 12,  // Bottom
                16, 17, 18, 18, 19, 16,  // Right
                20, 21, 22, 22, 23, 20   // Left
        };

        setup(vertices, indices);
    }

    @Override
    public float[] getVertices() {
        return vertices;
    }

    @Override
    public int[] getIndices() {
        return indices;
    }
}