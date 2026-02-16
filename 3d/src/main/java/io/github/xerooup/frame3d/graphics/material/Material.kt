package io.github.xerooup.frame3d.graphics.material

import io.github.xerooup.frame3d.graphics.Color

class Material {
    // Getters
    @JvmField
    val type: MaterialType
    var color: Color? = null
        private set
    var texture: Texture? = null
        private set

    // Color material
    constructor(color: Color?) {
        this.type = MaterialType.COLOR
        this.color = color
    }

    // Texture material (UV mapped)
    constructor(texture: Texture?) {
        this.type = MaterialType.TEXTURE
        this.texture = texture
    }

    companion object {
        // Factory methods
        fun color(color: Color?): Material {
            return Material(color)
        }

        fun texture(path: String?): Material {
            return Material(Texture(path))
        }
    }
}