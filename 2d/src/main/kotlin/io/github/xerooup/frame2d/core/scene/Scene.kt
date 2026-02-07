package io.github.xerooup.frame2d.core.scene

import io.github.xerooup.frame2d.graphics.DrawContext

abstract class Scene {
    open fun create() {}
    open fun update(delta: Float) {}
    open fun render(draw: DrawContext) {}
    open fun dispose() {}
}