package io.github.xerooup.frame2d.graphics.objects.ui

import io.github.xerooup.frame2d.graphics.Color
import io.github.xerooup.frame2d.graphics.DrawContext
import io.github.xerooup.frame2d.graphics.objects.text.Font
import io.github.xerooup.frame2d.graphics.objects.text.Text
import io.github.xerooup.frame2d.input.Mouse

class ButtonElement : InterfaceElement() {
    // core properties
    var label: Text? = null
    var font: Font? = null
    var color: Color = Color.RED
    var onClick: () -> Unit = {}

    // styling
    var hoverColor: Color = Color(0xbd0000)
    var labelAlign: TextAlign = TextAlign.CENTER

    // shadow settings
    var shadow: Boolean = false
    var shadowColor: Color = Color(0x222222)
    var shadowOffsetX: Int = 4
    var shadowOffsetY: Int = 4

    // text settings
    var textOffsetX: Int = 0
    var textOffsetY: Int = 0

    fun style(init: ButtonElement.() -> Unit) {
        this.init()
    }

    override fun render(draw: DrawContext) {
        val mx = Mouse.getX()
        val my = Mouse.getY()
        val isHovered = mx in x..(x + width) && my in y..(y + height)

        // draw shadow first with its own offsets
        if (shadow) {
            draw.fillRect(x + shadowOffsetX, y + shadowOffsetY, width, height, shadowColor)
        }

        // draw button body at base position
        val finalColor = if (isHovered) hoverColor else color
        draw.fillRect(x, y, width, height, finalColor)

        val currentFont = font
        val currentLabel = label

        if (currentFont != null && currentLabel != null) {
            val textWidth = currentFont.getStringWidth(currentLabel.content)
            val textHeight = currentFont.charHeight

            // horizontal alignment with extra text offset
            val tx = when (labelAlign) {
                TextAlign.LEFT -> x + 8 + textOffsetX
                TextAlign.CENTER -> x + (width - textWidth) / 2 + textOffsetX
                TextAlign.RIGHT -> x + width - textWidth - 8 + textOffsetX
            }

            // vertical centering with extra text offset
            val ty = y + (height - textHeight) / 2 + textOffsetY

            currentFont.render(draw, currentLabel.content, tx, ty, currentLabel.color)
        }
    }

    fun update() {
        val mx = Mouse.getX()
        val my = Mouse.getY()
        val isHovered = mx in x..(x + width) && my in y..(y + height)

        if (isHovered && Mouse.isButtonJustPressed(Mouse.Buttons.LEFT)) {
            onClick()
        }
    }
}