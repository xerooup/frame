package io.github.xerooup.frame3d.graphics.mesh;

import io.github.xerooup.frame3d.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class CylinderMesh extends BaseMesh {
    private final float[] vertices;
    private final int[] indices;

    public CylinderMesh() {
        this(Color.WHITE);
    }

    public CylinderMesh(Color color) {
        this(0.5f, 1.0f, 32, color);
    }

    public CylinderMesh(float radius, float height, int segments, Color color) {
        List<Float> vertexList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();

        float r = color.r;
        float g = color.g;
        float b = color.b;

        float halfHeight = height / 2;

        // Generate side vertices
        for (int i = 0; i <= segments; i++) {
            float angle = i * 2 * (float)Math.PI / segments;
            float x = (float)Math.cos(angle) * radius;
            float z = (float)Math.sin(angle) * radius;
            float nx = (float)Math.cos(angle);
            float nz = (float)Math.sin(angle);
            float u = (float)i / segments;

            // Top vertex (pos + color + normal + texCoord)
            vertexList.add(x);
            vertexList.add(halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(nx);
            vertexList.add(0f);
            vertexList.add(nz);
            vertexList.add(u);
            vertexList.add(1f);

            // Bottom vertex
            vertexList.add(x);
            vertexList.add(-halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(nx);
            vertexList.add(0f);
            vertexList.add(nz);
            vertexList.add(u);
            vertexList.add(0f);
        }

        // Side indices
        for (int i = 0; i < segments; i++) {
            int topLeft = i * 2;
            int bottomLeft = topLeft + 1;
            int topRight = topLeft + 2;
            int bottomRight = topRight + 1;

            indexList.add(topLeft);
            indexList.add(bottomLeft);
            indexList.add(topRight);

            indexList.add(topRight);
            indexList.add(bottomLeft);
            indexList.add(bottomRight);
        }

        int capStartIndex = vertexList.size() / 11;

        // Top cap center
        vertexList.add(0f);
        vertexList.add(halfHeight);
        vertexList.add(0f);
        vertexList.add(r);
        vertexList.add(g);
        vertexList.add(b);
        vertexList.add(0f);
        vertexList.add(1f);
        vertexList.add(0f);
        vertexList.add(0.5f);
        vertexList.add(0.5f);

        int topCenterIndex = capStartIndex;

        // Top cap vertices
        for (int i = 0; i <= segments; i++) {
            float angle = i * 2 * (float)Math.PI / segments;
            float x = (float)Math.cos(angle) * radius;
            float z = (float)Math.sin(angle) * radius;
            float u = ((float)Math.cos(angle) + 1) * 0.5f;
            float v = ((float)Math.sin(angle) + 1) * 0.5f;

            vertexList.add(x);
            vertexList.add(halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(0f);
            vertexList.add(1f);
            vertexList.add(0f);
            vertexList.add(u);
            vertexList.add(v);
        }

        // Top cap indices
        for (int i = 0; i < segments; i++) {
            indexList.add(topCenterIndex);
            indexList.add(topCenterIndex + i + 1);
            indexList.add(topCenterIndex + i + 2);
        }

        int bottomCapStart = vertexList.size() / 11;

        // Bottom cap center
        vertexList.add(0f);
        vertexList.add(-halfHeight);
        vertexList.add(0f);
        vertexList.add(r);
        vertexList.add(g);
        vertexList.add(b);
        vertexList.add(0f);
        vertexList.add(-1f);
        vertexList.add(0f);
        vertexList.add(0.5f);
        vertexList.add(0.5f);

        int bottomCenterIndex = bottomCapStart;

        // Bottom cap vertices
        for (int i = 0; i <= segments; i++) {
            float angle = i * 2 * (float)Math.PI / segments;
            float x = (float)Math.cos(angle) * radius;
            float z = (float)Math.sin(angle) * radius;
            float u = ((float)Math.cos(angle) + 1) * 0.5f;
            float v = ((float)Math.sin(angle) + 1) * 0.5f;

            vertexList.add(x);
            vertexList.add(-halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(0f);
            vertexList.add(-1f);
            vertexList.add(0f);
            vertexList.add(u);
            vertexList.add(v);
        }

        // Bottom cap indices (reversed winding)
        for (int i = 0; i < segments; i++) {
            indexList.add(bottomCenterIndex);
            indexList.add(bottomCenterIndex + i + 2);
            indexList.add(bottomCenterIndex + i + 1);
        }

        // Convert to arrays
        vertices = new float[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            vertices[i] = vertexList.get(i);
        }

        indices = new int[indexList.size()];
        for (int i = 0; i < indexList.size(); i++) {
            indices[i] = indexList.get(i);
        }

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