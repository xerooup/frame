package io.github.xerooup.frame3d.graphics.mesh;

import io.github.xerooup.frame3d.graphics.Color;
import java.io.*;
import java.util.*;

public class MeshLoader {

    public static Mesh loadOBJ(String path) {
        return loadOBJ(path, Color.WHITE);
    }

    public static Mesh loadOBJ(String path, Color color) {
        try {
            InputStream stream = MeshLoader.class.getClassLoader().getResourceAsStream(path);
            if (stream == null) {
                throw new RuntimeException("OBJ file not found: " + path);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            List<float[]> positions = new ArrayList<>();
            List<float[]> texCoords = new ArrayList<>();
            List<float[]> normals = new ArrayList<>();
            List<Face> faces = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                switch (parts[0]) {
                    case "v":  // vertex position
                        positions.add(new float[]{
                                Float.parseFloat(parts[1]),
                                Float.parseFloat(parts[2]),
                                Float.parseFloat(parts[3])
                        });
                        break;

                    case "vt": // texture coordinate
                        texCoords.add(new float[]{
                                Float.parseFloat(parts[1]),
                                parts.length > 2 ? Float.parseFloat(parts[2]) : 0.0f
                        });
                        break;

                    case "vn": // vertex normal
                        normals.add(new float[]{
                                Float.parseFloat(parts[1]),
                                Float.parseFloat(parts[2]),
                                Float.parseFloat(parts[3])
                        });
                        break;

                    case "f":  // face
                        // Handle triangles and quads
                        List<FaceVertex> faceVertices = new ArrayList<>();
                        for (int i = 1; i < parts.length; i++) {
                            faceVertices.add(parseFaceVertex(parts[i]));
                        }

                        // Triangulate if quad (4 vertices)
                        if (faceVertices.size() == 3) {
                            faces.add(new Face(faceVertices));
                        } else if (faceVertices.size() == 4) {
                            // Split quad into two triangles
                            faces.add(new Face(Arrays.asList(
                                    faceVertices.get(0),
                                    faceVertices.get(1),
                                    faceVertices.get(2)
                            )));
                            faces.add(new Face(Arrays.asList(
                                    faceVertices.get(0),
                                    faceVertices.get(2),
                                    faceVertices.get(3)
                            )));
                        } else if (faceVertices.size() > 4) {
                            // Fan triangulation for polygons
                            for (int i = 1; i < faceVertices.size() - 1; i++) {
                                faces.add(new Face(Arrays.asList(
                                        faceVertices.get(0),
                                        faceVertices.get(i),
                                        faceVertices.get(i + 1)
                                )));
                            }
                        }
                        break;
                }
            }

            reader.close();

            // Calculate normals if not provided
            if (normals.isEmpty()) {
                normals = calculateNormals(positions, faces);
            }

            return new OBJMesh(positions, texCoords, normals, faces, color);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load OBJ: " + e.getMessage(), e);
        }
    }

    // Parse face vertex: formats like "v", "v/vt", "v/vt/vn", "v//vn"
    private static FaceVertex parseFaceVertex(String vertex) {
        String[] parts = vertex.split("/");
        int posIndex = Integer.parseInt(parts[0]) - 1; // OBJ indices start at 1
        int texIndex = -1;
        int normIndex = -1;

        if (parts.length > 1 && !parts[1].isEmpty()) {
            texIndex = Integer.parseInt(parts[1]) - 1;
        }
        if (parts.length > 2 && !parts[2].isEmpty()) {
            normIndex = Integer.parseInt(parts[2]) - 1;
        }

        return new FaceVertex(posIndex, texIndex, normIndex);
    }

    // Calculate flat normals for faces without normal data
    private static List<float[]> calculateNormals(List<float[]> positions, List<Face> faces) {
        List<float[]> normals = new ArrayList<>();

        for (Face face : faces) {
            List<FaceVertex> verts = face.vertices;
            if (verts.size() < 3) continue;

            float[] v0 = positions.get(verts.get(0).posIndex);
            float[] v1 = positions.get(verts.get(1).posIndex);
            float[] v2 = positions.get(verts.get(2).posIndex);

            // Calculate edges
            float[] edge1 = {v1[0] - v0[0], v1[1] - v0[1], v1[2] - v0[2]};
            float[] edge2 = {v2[0] - v0[0], v2[1] - v0[1], v2[2] - v0[2]};

            // Cross product
            float[] normal = {
                    edge1[1] * edge2[2] - edge1[2] * edge2[1],
                    edge1[2] * edge2[0] - edge1[0] * edge2[2],
                    edge1[0] * edge2[1] - edge1[1] * edge2[0]
            };

            // Normalize
            float length = (float)Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);
            if (length > 0) {
                normal[0] /= length;
                normal[1] /= length;
                normal[2] /= length;
            }

            normals.add(normal);
        }

        return normals;
    }

    // Helper classes
    static class FaceVertex {
        int posIndex;
        int texIndex;
        int normIndex;

        FaceVertex(int posIndex, int texIndex, int normIndex) {
            this.posIndex = posIndex;
            this.texIndex = texIndex;
            this.normIndex = normIndex;
        }
    }

    static class Face {
        List<FaceVertex> vertices;

        Face(List<FaceVertex> vertices) {
            this.vertices = vertices;
        }
    }
}