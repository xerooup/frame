package io.github.xerooup.frame3d.graphics.mesh

import io.github.xerooup.frame3d.graphics.Color

class OBJMesh(
    positions: List<FloatArray>,
    texCoords: List<FloatArray>,
    normals: List<FloatArray>,
    faces: List<MeshLoader.Face>,
    color: Color
) : BaseMesh() {

    private val vertices: FloatArray
    private val indices: IntArray

    init {
        val vertexList = mutableListOf<Float>()
        val indexList = mutableListOf<Int>()

        val vertexMap = mutableMapOf<String, Int>()
        var currentIndex = 0

        var faceIndex = 0
        for (face in faces) {
            for (fv in face.vertices) {
                val key = "${fv.posIndex}/${fv.texIndex}/${fv.normIndex}"

                val existingIndex = vertexMap[key]
                if (existingIndex != null) {
                    indexList.add(existingIndex)
                } else {
                    val pos = positions[fv.posIndex]

                    // Position
                    vertexList.add(pos[0])
                    vertexList.add(pos[1])
                    vertexList.add(pos[2])

                    // Color
                    vertexList.add(color.r)
                    vertexList.add(color.g)
                    vertexList.add(color.b)

                    // Normal
                    if (fv.normIndex >= 0 && fv.normIndex < normals.size) {
                        val normal = normals[fv.normIndex]
                        vertexList.add(normal[0])
                        vertexList.add(normal[1])
                        vertexList.add(normal[2])
                    } else if (faceIndex < normals.size) {
                        val normal = normals[faceIndex]
                        vertexList.add(normal[0])
                        vertexList.add(normal[1])
                        vertexList.add(normal[2])
                    } else {
                        vertexList.add(0f)
                        vertexList.add(1f)
                        vertexList.add(0f)
                    }

                    // Texture coordinate
                    if (fv.texIndex >= 0 && fv.texIndex < texCoords.size) {
                        val tex = texCoords[fv.texIndex]
                        vertexList.add(tex[0])
                        vertexList.add(tex[1])
                    } else {
                        vertexList.add(0.5f)
                        vertexList.add(0.5f)
                    }

                    vertexMap[key] = currentIndex
                    indexList.add(currentIndex)
                    currentIndex++
                }
            }
            faceIndex++
        }

        vertices = vertexList.toFloatArray()
        indices = indexList.toIntArray()

        setup(vertices, indices)
    }

    override fun getVertices() = vertices
    override fun getIndices() = indices
}