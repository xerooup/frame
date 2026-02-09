package io.github.xerooup.frame2d.graphics.objects.ui

import io.github.xerooup.frame2d.graphics.Color
import io.github.xerooup.frame2d.graphics.DrawContext
import io.github.xerooup.frame2d.graphics.objects.text.Font
import io.github.xerooup.frame2d.input.Keyboard
import io.github.xerooup.frame2d.input.Mouse

class TextInputElement : InterfaceElement() {
    var text: String = ""
    var font: Font? = null
    var isFocused: Boolean = false

    var onAction: (String) -> Unit = {}

    var clearOnAction: Boolean = false
    var maxLength: Int = 256

    var backgroundColor: Color = Color(0x616161)
    var focusedColor: Color = Color(0x737373)
    var textColor: Color = Color.WHITE
    var caretColor: Color = Color.WHITE

    var textOffsetX: Int = 8
    var textOffsetY: Int = 0

    var shadow: Boolean = true
    var shadowColor: Color = Color(0x000000)
    var shadowOffsetX: Int = 4
    var shadowOffsetY: Int = 4

    fun style(init: TextInputElement.() -> Unit) {
        this.init()
    }

    override fun render(draw: DrawContext) {
        val f = font ?: return

        if (shadow) {
            draw.fillRect(x + shadowOffsetX, y + shadowOffsetY, width, height, shadowColor)
        }

        // background
        val currentBg = if (isFocused) focusedColor else backgroundColor
        draw.fillRect(x, y, width, height, currentBg)

        // text rendering with offsets
        val textY = y + (height - f.charHeight) / 2 + textOffsetY
        f.render(draw, text, x + textOffsetX, textY, textColor)

        // caret |
        if (isFocused && System.currentTimeMillis() % 1000 < 500) {
            val textWidth = f.getStringWidth(text)
            val caretX = x + textOffsetX + textWidth + 2
            draw.fillRect(caretX, y + 8, 2, height - 16, caretColor)
        }
    }

    fun update() {
        if (Mouse.isButtonJustPressed(Mouse.Buttons.LEFT)) {
            val mx = Mouse.getX()
            val my = Mouse.getY()
            isFocused = mx >= x && mx <= x + width && my >= y && my <= y + height
        }

        if (isFocused) {
            val chars = Keyboard.getChars()
            for (c in chars) {
                if (text.length < maxLength) text += c
            }

            if (Keyboard.isKeyJustPressed(Keyboard.Keys.BACKSPACE) && text.isNotEmpty()) {
                text = text.dropLast(1)
            }

            if (Keyboard.isKeyJustPressed(Keyboard.Keys.ENTER)) {
                onAction(text)
                if (clearOnAction) text = ""
                isFocused = false
            }
        }
    }
}