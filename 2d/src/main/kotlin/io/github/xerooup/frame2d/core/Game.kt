package io.github.xerooup.frame2d.core

import io.github.xerooup.frame2d.core.internal.Engine
import io.github.xerooup.frame2d.graphics.Color
import io.github.xerooup.frame2d.graphics.DrawContext

data class Settings(
    var width: Int = 800,
    var height: Int = 600,
    var title: String = "my very mega super cool great amazing awesome game",
    var decorated: Boolean = true,
    var targetFPS: Int = 60,
    var background: Color = Color.WHITE,
    var icon: String? = null,
    var windowX: Int? = null,
    var windowY: Int? = null
)

abstract class Game {
    abstract fun settings(settings: Settings)
    open fun create() {}
    open fun update(delta: Float) {}
    open fun render(draw: DrawContext) {}

    companion object {
        fun run(game: Game) {
            val settings = Settings()
            game.settings(settings)
            Engine(settings, game).run()
        }
        fun stop() {
            Engine.forceStop()
        }
        var fps: Int = 0
            internal set

        var uptime: Float = 0f
            internal set
    }
}