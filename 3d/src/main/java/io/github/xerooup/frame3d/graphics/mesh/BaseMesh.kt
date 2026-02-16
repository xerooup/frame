package io.github.xerooup.frame3d.graphics.mesh

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL30.*

abstract class BaseMesh : Mesh {
    protected var vao = 0
    protected var vbo = 0
    protected var ebo = 0
    protected var vertexCount = 0

    // 11 floats per vertex: pos(3) + color(3) + normal(3) + texCoord(2)
    protected fun setup(vertices: FloatArray, indices: IntArray) {
        vertexCount = indices.size

        vao = glGenVertexArrays()
        vbo = glGenBuffers()
        ebo = glGenBuffers()

        glBindVertexArray(vao)

        // VBO
        val vertexBuffer = BufferUtils.createFloatBuffer(vertices.size)
        vertexBuffer.put(vertices).flip()

        glBindBuffer(GL_ARRAY_BUFFER, vbo)
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW)

        // EBO
        val indexBuffer = BufferUtils.createIntBuffer(indices.size)
        indexBuffer.put(indices).flip()

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW)

        val stride = 11 * Float.SIZE_BYTES

        // Position (location = 0)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, stride, 0)
        glEnableVertexAttribArray(0)

        // Color (location = 1)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, stride, 3L * Float.SIZE_BYTES)
        glEnableVertexAttribArray(1)

        // Normal (location = 2)
        glVertexAttribPointer(2, 3, GL_FLOAT, false, stride, 6L * Float.SIZE_BYTES)
        glEnableVertexAttribArray(2)

        // Texture coordinate (location = 3)
        glVertexAttribPointer(3, 2, GL_FLOAT, false, stride, 9L * Float.SIZE_BYTES)
        glEnableVertexAttribArray(3)

        glBindVertexArray(0)
    }

    override fun bind() {
        glBindVertexArray(vao)
    }

    override fun unbind() {
        glBindVertexArray(0)
    }

    override fun render() {
        bind()
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0)
        unbind()
    }

    override fun cleanup() {
        glDeleteVertexArrays(vao)
        glDeleteBuffers(vbo)
        glDeleteBuffers(ebo)
    }
}