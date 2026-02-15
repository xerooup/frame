package io.github.xerooup.frame3d.graphics.mesh;

import io.github.xerooup.frame3d.graphics.Color;

public class PyramidMesh extends BaseMesh {
    private final float[] vertices;
    private final int[] indices;

    public PyramidMesh() {
        this(Color.WHITE);
    }

    public PyramidMesh(Color color) {
        this(1.0f, 1.0f, color);
    }

    public PyramidMesh(float baseSize, float height, Color color) {
        float halfBase = baseSize / 2;
        float halfHeight = height / 2;

        vertices = new float[] {
                // position (x, y, z) + color (r, g, b) + normal (nx, ny, nz) + texCoord (u, v)

                // Top vertex (apex)
                0.0f, halfHeight, 0.0f,  color.r, color.g, color.b,  0, 1, 0,  0.5f, 0.5f,

                // Base vertices
                -halfBase, -halfHeight, halfBase,  color.r, color.g, color.b,  0, -1, 0,  0, 0,
                halfBase, -halfHeight, halfBase,  color.r, color.g, color.b,  0, -1, 0,  1, 0,
                halfBase, -halfHeight, -halfBase,  color.r, color.g, color.b,  0, -1, 0,  1, 1,
                -halfBase, -halfHeight, -halfBase,  color.r, color.g, color.b,  0, -1, 0,  0, 1,

                // Side faces with proper normals and tex coords
                // Front face
                0.0f, halfHeight, 0.0f,  color.r, color.g, color.b,  0, 0.6f, 0.8f,  0.5f, 1.0f,
                -halfBase, -halfHeight, halfBase,  color.r, color.g, color.b,  0, 0.6f, 0.8f,  0.0f, 0.0f,
                halfBase, -halfHeight, halfBase,  color.r, color.g, color.b,  0, 0.6f, 0.8f,  1.0f, 0.0f,

                // Right face
                0.0f, halfHeight, 0.0f,  color.r, color.g, color.b,  0.8f, 0.6f, 0,  0.5f, 1.0f,
                halfBase, -halfHeight, halfBase,  color.r, color.g, color.b,  0.8f, 0.6f, 0,  0.0f, 0.0f,
                halfBase, -halfHeight, -halfBase,  color.r, color.g, color.b,  0.8f, 0.6f, 0,  1.0f, 0.0f,

                // Back face
                0.0f, halfHeight, 0.0f,  color.r, color.g, color.b,  0, 0.6f, -0.8f,  0.5f, 1.0f,
                halfBase, -halfHeight, -halfBase,  color.r, color.g, color.b,  0, 0.6f, -0.8f,  0.0f, 0.0f,
                -halfBase, -halfHeight, -halfBase,  color.r, color.g, color.b,  0, 0.6f, -0.8f,  1.0f, 0.0f,

                // Left face
                0.0f, halfHeight, 0.0f,  color.r, color.g, color.b,  -0.8f, 0.6f, 0,  0.5f, 1.0f,
                -halfBase, -halfHeight, -halfBase,  color.r, color.g, color.b,  -0.8f, 0.6f, 0,  0.0f, 0.0f,
                -halfBase, -halfHeight, halfBase,  color.r, color.g, color.b,  -0.8f, 0.6f, 0,  1.0f, 0.0f
        };

        indices = new int[] {
                // Base
                1, 2, 3,
                3, 4, 1,

                // Faces
                5, 6, 7,
                8, 9, 10,
                11, 12, 13,
                14, 15, 16
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