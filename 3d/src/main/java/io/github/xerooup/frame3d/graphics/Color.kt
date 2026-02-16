package io.github.xerooup.frame3d.graphics

class Color(@JvmField val r: Float, @JvmField val g: Float, @JvmField val b: Float) {
    constructor(hex: Int) : this(
        ((hex shr 16) and 0xFF) / 255f,
        ((hex shr 8) and 0xFF) / 255f,
        (hex and 0xFF) / 255f
    )

    constructor(r: Int, g: Int, b: Int) : this(r / 255f, g / 255f, b / 255f)

    fun toRgb888(): Int {
        return ((r * 255).toInt() shl 16) or
                ((g * 255).toInt() shl 8) or
                ((b * 255).toInt())
    }

    companion object {
        @JvmField
        val WHITE: Color = Color(0xFFFFFF)
        val BLACK: Color = Color(0x000000)
        val RED: Color = Color(0xFF0000)
        val GREEN: Color = Color(0x00FF00)
        val BLUE: Color = Color(0x0000FF)
        val YELLOW: Color = Color(0xFFFF00)
        val CYAN: Color = Color(0x00FFFF)
        val MAGENTA: Color = Color(0xFF00FF)
    }
}