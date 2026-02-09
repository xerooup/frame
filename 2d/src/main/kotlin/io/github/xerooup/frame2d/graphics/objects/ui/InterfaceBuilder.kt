package io.github.xerooup.frame2d.graphics.objects.ui

abstract class InterfaceElement {
    var x: Int = 0
    var y: Int = 0
    var width: Int = 0
    var height: Int = 0

    abstract fun render(draw: io.github.xerooup.frame2d.graphics.DrawContext)
}

class InterfaceContainer {
    val elements = mutableListOf<InterfaceElement>()

    fun button(init: ButtonElement.() -> Unit) {
        val btn = ButtonElement().apply(init)
        elements.add(btn)
    }

    fun label(init: LabelElement.() -> Unit) {
        val lb = LabelElement().apply(init)
        elements.add(lb)
    }

    fun input(init: TextInputElement.() -> Unit) {
        val inp = TextInputElement().apply(init)
        elements.add(inp)
    }

    fun update() {
        for (element in elements) {
            when (element) {
                is ButtonElement -> element.update()
                is TextInputElement -> element.update()
            }
        }
    }
}

fun InterfaceBuilder(init: InterfaceContainer.() -> Unit): InterfaceContainer {
    return InterfaceContainer().apply(init)
}