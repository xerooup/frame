package io.github.xerooup.frame3d.graphics.material

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.stb.STBImage

class Texture(path: String?) {
    val textureId: Int
    val width: Int
    val height: Int

    init {
        try {
            // Load from resources
            val stream = javaClass.getClassLoader().getResourceAsStream(path)
                ?: throw RuntimeException("texture file not found: $path")

            val imageData = stream.readAllBytes()
            val imageBuffer = BufferUtils.createByteBuffer(imageData.size)
            imageBuffer.put(imageData)
            imageBuffer.flip()

            val widthBuf = BufferUtils.createIntBuffer(1)
            val heightBuf = BufferUtils.createIntBuffer(1)
            val channelsBuf = BufferUtils.createIntBuffer(1)

            STBImage.stbi_set_flip_vertically_on_load(true)
            val image = STBImage.stbi_load_from_memory(imageBuffer, widthBuf, heightBuf, channelsBuf, 4)
                ?: throw RuntimeException("failed to load texture: " + STBImage.stbi_failure_reason())

            this.width = widthBuf.get(0)
            this.height = heightBuf.get(0)

            // Create OpenGL texture
            textureId = GL11.glGenTextures()
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId)

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT)
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT)
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR)
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR)

            GL11.glTexImage2D(
                GL11.GL_TEXTURE_2D,
                0,
                GL11.GL_RGBA,
                width,
                height,
                0,
                GL11.GL_RGBA,
                GL11.GL_UNSIGNED_BYTE,
                image
            )
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D)

            STBImage.stbi_image_free(image)
            stream.close()
        } catch (e: Exception) {
            throw RuntimeException("failed to load texture: " + e.message, e)
        }
    }

    fun bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId)
    }

    fun unbind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)
    }

    fun cleanup() {
        GL11.glDeleteTextures(textureId)
    }
}