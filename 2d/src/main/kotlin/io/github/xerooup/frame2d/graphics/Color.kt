package io.github.xerooup.frame2d.graphics

class Color(val r: Float, val g: Float, val b: Float) {
    constructor(hex: Int) : this(
        ((hex shr 16) and 0xFF) / 255f,
        ((hex shr 8) and 0xFF) / 255f,
        (hex and 0xFF) / 255f
    )

    constructor(r: Int, g: Int, b: Int) : this(r / 255f, g / 255f, b / 255f)

    companion object {
        val WHITE = Color(0xFFFFFF)
        val BLACK = Color(0x000000)
        val RED = Color(0xFF0000)
        val GREEN = Color(0x00FF00)
        val BLUE = Color(0x0000FF)
        val YELLOW = Color(0xFFFF00)
        val CYAN = Color(0x00FFFF)
        val MAGENTA = Color(0xFF00FF)
        val ORANGE = Color(0xFFA500)
        val PURPLE = Color(0x800080)

        val DARK_GRAY = Color(0x212121)
        val GRAY = Color(0x757575)
        val LIGHT_GRAY = Color(0xBDBDBD)
        val SLATE = Color(0x2C3E50)
        val CLOUD = Color(0xECF0F1)
        val EMERALD = Color(0x2ECC71)
        val RIVER = Color(0x3498DB)
        val ALIZARIN = Color(0xE74C3C)
        val SUNFLOWER = Color(0xF1C40F)

        val GOLD = Color(0xFFD700)
        val SILVER = Color(0xC0C0C0)
        val BRONZE = Color(0xCD7F32)
        val PINK = Color(0xFFC0CB)
        val SKY_BLUE = Color(0x87CEEB)
        val FOREST_GREEN = Color(0x228B22)

        val CHARCOAL = Color(0x36454F)
        val GUNMETAL = Color(0x2A3439)
        val ONYX = Color(0x353935)
    }

    internal fun toRgb888(): Int {
        return ((r * 255).toInt() shl 16) or
                ((g * 255).toInt() shl 8) or
                ((b * 255).toInt())
    }
}