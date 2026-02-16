package io.github.xerooup.frame3d.graphics.mesh

interface Mesh {
    fun getVertices(): FloatArray
    fun getIndices(): IntArray
    fun bind()
    fun unbind()
    fun render()
    fun cleanup()
}