package io.github.xerooup.frame3d.graphics.mesh;

import io.github.xerooup.frame3d.graphics.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OBJMesh extends BaseMesh {
    private final float[] vertices;
    private final int[] indices;

    OBJMesh(List<float[]> positions, List<float[]> texCoords, List<float[]> normals,
            List<MeshLoader.Face> faces, Color color) {

        List<Float> vertexList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();

        Map<String, Integer> vertexMap = new HashMap<>();
        int currentIndex = 0;

        int faceIndex = 0;
        for (MeshLoader.Face face : faces) {
            for (MeshLoader.FaceVertex fv : face.vertices) {
                String key = fv.posIndex + "/" + fv.texIndex + "/" + fv.normIndex;

                Integer existingIndex = vertexMap.get(key);
                if (existingIndex != null) {
                    indexList.add(existingIndex);
                } else {
                    float[] pos = positions.get(fv.posIndex);

                    // Position
                    vertexList.add(pos[0]);
                    vertexList.add(pos[1]);
                    vertexList.add(pos[2]);

                    // Color
                    vertexList.add(color.r);
                    vertexList.add(color.g);
                    vertexList.add(color.b);

                    // Normal
                    if (fv.normIndex >= 0 && fv.normIndex < normals.size()) {
                        float[] normal = normals.get(fv.normIndex);
                        vertexList.add(normal[0]);
                        vertexList.add(normal[1]);
                        vertexList.add(normal[2]);
                    } else if (faceIndex < normals.size()) {
                        float[] normal = normals.get(faceIndex);
                        vertexList.add(normal[0]);
                        vertexList.add(normal[1]);
                        vertexList.add(normal[2]);
                    } else {
                        vertexList.add(0f);
                        vertexList.add(1f);
                        vertexList.add(0f);
                    }

                    // Texture coordinate
                    if (fv.texIndex >= 0 && fv.texIndex < texCoords.size()) {
                        float[] tex = texCoords.get(fv.texIndex);
                        vertexList.add(tex[0]);
                        vertexList.add(tex[1]);
                    } else {
                        // Default UV
                        vertexList.add(0.5f);
                        vertexList.add(0.5f);
                    }

                    vertexMap.put(key, currentIndex);
                    indexList.add(currentIndex);
                    currentIndex++;
                }
            }
            faceIndex++;
        }

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