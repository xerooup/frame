package io.github.xerooup.frame3d.graphics.mesh

import io.github.xerooup.frame3d.graphics.Color
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.sqrt

object MeshLoader {

    @JvmStatic
    fun loadOBJ(path: String): Mesh = loadOBJ(path, Color.WHITE)

    @JvmStatic
    fun loadOBJ(path: String, color: Color): Mesh {
        try {
            val stream = MeshLoader::class.java.classLoader.getResourceAsStream(path)
                ?: throw RuntimeException("OBJ file not found in resources: $path")

            val reader = BufferedReader(InputStreamReader(stream))

            val positions = mutableListOf<FloatArray>()
            val texCoords = mutableListOf<FloatArray>()
            val normals = mutableListOf<FloatArray>()
            val faces = mutableListOf<Face>()

            reader.useLines { lines ->
                for (line in lines) {
                    val trimmed = line.trim()
                    if (trimmed.isEmpty() || trimmed.startsWith("#")) continue

                    val parts = trimmed.split("\\s+".toRegex())

                    when (parts[0]) {
                        "v" -> {
                            // Vertex position
                            positions.add(floatArrayOf(
                                parts[1].toFloat(),
                                parts[2].toFloat(),
                                parts[3].toFloat()
                            ))
                        }

                        "vt" -> {
                            // Texture coordinate
                            texCoords.add(floatArrayOf(
                                parts[1].toFloat(),
                                if (parts.size > 2) parts[2].toFloat() else 0f
                            ))
                        }

                        "vn" -> {
                            // Vertex normal
                            normals.add(floatArrayOf(
                                parts[1].toFloat(),
                                parts[2].toFloat(),
                                parts[3].toFloat()
                            ))
                        }

                        "f" -> {
                            // Face
                            val faceVertices = mutableListOf<FaceVertex>()
                            for (i in 1 until parts.size) {
                                faceVertices.add(parseFaceVertex(parts[i]))
                            }

                            // Triangulate if necessary
                            when {
                                faceVertices.size == 3 -> {
                                    faces.add(Face(faceVertices))
                                }
                                faceVertices.size == 4 -> {
                                    // Split quad into two triangles
                                    faces.add(Face(listOf(
                                        faceVertices[0],
                                        faceVertices[1],
                                        faceVertices[2]
                                    )))
                                    faces.add(Face(listOf(
                                        faceVertices[0],
                                        faceVertices[2],
                                        faceVertices[3]
                                    )))
                                }
                                faceVertices.size > 4 -> {
                                    // Fan triangulation for polygons
                                    for (i in 1 until faceVertices.size - 1) {
                                        faces.add(Face(listOf(
                                            faceVertices[0],
                                            faceVertices[i],
                                            faceVertices[i + 1]
                                        )))
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Calculate normals if not provided
            val finalNormals = if (normals.isEmpty()) {
                calculateNormals(positions, faces)
            } else {
                normals
            }

            return OBJMesh(positions, texCoords, finalNormals, faces, color)

        } catch (e: Exception) {
            throw RuntimeException("Failed to load OBJ: ${e.message}", e)
        }
    }

    // Parse face vertex: formats like "v", "v/vt", "v/vt/vn", "v//vn"
    private fun parseFaceVertex(vertex: String): FaceVertex {
        val parts = vertex.split("/")
        val posIndex = parts[0].toInt() - 1 // OBJ indices start at 1
        val texIndex = if (parts.size > 1 && parts[1].isNotEmpty()) parts[1].toInt() - 1 else -1
        val normIndex = if (parts.size > 2 && parts[2].isNotEmpty()) parts[2].toInt() - 1 else -1

        return FaceVertex(posIndex, texIndex, normIndex)
    }

    // Calculate flat normals for faces without normal data
    private fun calculateNormals(positions: List<FloatArray>, faces: List<Face>): List<FloatArray> {
        val normals = mutableListOf<FloatArray>()

        for (face in faces) {
            val verts = face.vertices
            if (verts.size < 3) continue

            val v0 = positions[verts[0].posIndex]
            val v1 = positions[verts[1].posIndex]
            val v2 = positions[verts[2].posIndex]

            // Calculate edges
            val edge1 = floatArrayOf(v1[0] - v0[0], v1[1] - v0[1], v1[2] - v0[2])
            val edge2 = floatArrayOf(v2[0] - v0[0], v2[1] - v0[1], v2[2] - v0[2])

            // Cross product
            val normal = floatArrayOf(
                edge1[1] * edge2[2] - edge1[2] * edge2[1],
                edge1[2] * edge2[0] - edge1[0] * edge2[2],
                edge1[0] * edge2[1] - edge1[1] * edge2[0]
            )

            // Normalize
            val length = sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2])
            if (length > 0) {
                normal[0] /= length
                normal[1] /= length
                normal[2] /= length
            }

            normals.add(normal)
        }

        return normals
    }

    // Helper classes
    data class FaceVertex(
        val posIndex: Int,
        val texIndex: Int,
        val normIndex: Int
    )

    data class Face(
        val vertices: List<FaceVertex>
    )
}