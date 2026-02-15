package io.github.xerooup.frame3d.graphics.mesh;

import io.github.xerooup.frame3d.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class SphereMesh extends BaseMesh {
    private final float[] vertices;
    private final int[] indices;

    public SphereMesh() {
        this(Color.WHITE);
    }

    public SphereMesh(Color color) {
        this(0.5f, 16, 16, color);
    }

    public SphereMesh(float radius, int segments, int rings, Color color) {
        List<Float> vertexList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();

        float r = color.r;
        float g = color.g;
        float b = color.b;

        // Generate vertices
        for (int ring = 0; ring <= rings; ring++) {
            float theta = ring * (float)Math.PI / rings;
            float sinTheta = (float)Math.sin(theta);
            float cosTheta = (float)Math.cos(theta);

            for (int segment = 0; segment <= segments; segment++) {
                float phi = segment * 2 * (float)Math.PI / segments;
                float sinPhi = (float)Math.sin(phi);
                float cosPhi = (float)Math.cos(phi);

                float x = cosPhi * sinTheta;
                float y = cosTheta;
                float z = sinPhi * sinTheta;

                // Position
                vertexList.add(x * radius);
                vertexList.add(y * radius);
                vertexList.add(z * radius);

                // Color
                vertexList.add(r);
                vertexList.add(g);
                vertexList.add(b);

                // Normal
                vertexList.add(x);
                vertexList.add(y);
                vertexList.add(z);
            }
        }

        // Generate indices
        for (int ring = 0; ring < rings; ring++) {
            for (int segment = 0; segment < segments; segment++) {
                int current = ring * (segments + 1) + segment;
                int next = current + segments + 1;

                indexList.add(current);
                indexList.add(next);
                indexList.add(current + 1);

                indexList.add(current + 1);
                indexList.add(next);
                indexList.add(next + 1);
            }
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