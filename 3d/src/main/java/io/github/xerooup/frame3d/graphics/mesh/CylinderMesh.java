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
        this(0.5f, 1.0f, 16, color);
    }

    public CylinderMesh(float radius, float height, int segments, Color color) {
        List<Float> vertexList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();

        float r = color.r;
        float g = color.g;
        float b = color.b;

        float halfHeight = height / 2;

        // Top and bottom centers
        int topCenterIndex = 0;
        int bottomCenterIndex = segments + 1;

        // Top center
        vertexList.add(0f);
        vertexList.add(halfHeight);
        vertexList.add(0f);
        vertexList.add(r);
        vertexList.add(g);
        vertexList.add(b);
        vertexList.add(0f);
        vertexList.add(1f);
        vertexList.add(0f);

        // Top circle
        for (int i = 0; i <= segments; i++) {
            float angle = i * 2 * (float)Math.PI / segments;
            float x = (float)Math.cos(angle) * radius;
            float z = (float)Math.sin(angle) * radius;

            vertexList.add(x);
            vertexList.add(halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(0f);
            vertexList.add(1f);
            vertexList.add(0f);
        }

        // Bottom center
        vertexList.add(0f);
        vertexList.add(-halfHeight);
        vertexList.add(0f);
        vertexList.add(r);
        vertexList.add(g);
        vertexList.add(b);
        vertexList.add(0f);
        vertexList.add(-1f);
        vertexList.add(0f);

        // Bottom circle
        for (int i = 0; i <= segments; i++) {
            float angle = i * 2 * (float)Math.PI / segments;
            float x = (float)Math.cos(angle) * radius;
            float z = (float)Math.sin(angle) * radius;

            vertexList.add(x);
            vertexList.add(-halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(0f);
            vertexList.add(-1f);
            vertexList.add(0f);
        }

        // Side vertices (with outward normals)
        int sideTopStart = vertexList.size() / 9;
        for (int i = 0; i <= segments; i++) {
            float angle = i * 2 * (float)Math.PI / segments;
            float x = (float)Math.cos(angle) * radius;
            float z = (float)Math.sin(angle) * radius;
            float nx = (float)Math.cos(angle);
            float nz = (float)Math.sin(angle);

            // Top
            vertexList.add(x);
            vertexList.add(halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(nx);
            vertexList.add(0f);
            vertexList.add(nz);

            // Bottom
            vertexList.add(x);
            vertexList.add(-halfHeight);
            vertexList.add(z);
            vertexList.add(r);
            vertexList.add(g);
            vertexList.add(b);
            vertexList.add(nx);
            vertexList.add(0f);
            vertexList.add(nz);
        }

        // Top cap indices
        for (int i = 0; i < segments; i++) {
            indexList.add(topCenterIndex);
            indexList.add(i + 1);
            indexList.add(i + 2);
        }

        // Bottom cap indices
        for (int i = 0; i < segments; i++) {
            indexList.add(bottomCenterIndex);
            indexList.add(bottomCenterIndex + i + 2);
            indexList.add(bottomCenterIndex + i + 1);
        }

        // Side indices
        for (int i = 0; i < segments; i++) {
            int topLeft = sideTopStart + i * 2;
            int topRight = topLeft + 2;
            int bottomLeft = topLeft + 1;
            int bottomRight = topRight + 1;

            indexList.add(topLeft);
            indexList.add(bottomLeft);
            indexList.add(topRight);

            indexList.add(topRight);
            indexList.add(bottomLeft);
            indexList.add(bottomRight);
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