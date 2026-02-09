package io.github.xerooup.frame2d.graphics.objects.ui

import io.github.xerooup.frame2d.graphics.Color
import io.github.xerooup.frame2d.graphics.DrawContext
import io.github.xerooup.frame2d.graphics.objects.text.Font

class LabelElement : InterfaceElement() {
    var text: String = ""
    var font: Font? = null
    var color: Color = Color.WHITE

    var offsetX: Int = 0
    var offsetY: Int = 0

    var shadow: Boolean = false
    var shadowColor: Color = Color.BLACK
    var shadowOffsetX: Int = 2
    var shadowOffsetY: Int = 2

    fun style(init: LabelElement.() -> Unit) {
        this.init()
    }

    override fun render(draw: DrawContext) {
        val currentFont = font ?: return

        val tx = x + offsetX
        val ty = y + offsetY

        if (shadow) {
            currentFont.render(draw, text, tx + shadowOffsetX, ty + shadowOffsetY, shadowColor)
        }

        currentFont.render(draw, text, tx, ty, color)
    }
}