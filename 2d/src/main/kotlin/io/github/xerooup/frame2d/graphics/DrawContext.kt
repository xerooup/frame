package io.github.xerooup.frame2d.graphics

import io.github.xerooup.frame2d.core.camera.Camera
import io.github.xerooup.frame2d.graphics.objects.text.Font
import io.github.xerooup.frame2d.graphics.objects.text.Text
import io.github.xerooup.frame2d.graphics.objects.Texture
import io.github.xerooup.frame2d.graphics.objects.tile.Tile
import io.github.xerooup.frame2d.graphics.objects.tile.TileMap
import io.github.xerooup.frame2d.graphics.objects.tile.Tileset
import io.github.xerooup.frame2d.graphics.objects.ui.InterfaceContainer

interface DrawContext {
    fun pixel(x: Int, y: Int, color: Color = Color.WHITE)

    // rect drawing
    fun fillRect(x: Int, y: Int, width: Int, height: Int, color: Color = Color.WHITE)
    fun rect(x: Int, y: Int, width: Int, height: Int, color: Color = Color.WHITE)

    // circle drawing
    fun circle(x: Int, y: Int, radius: Int, color: Color = Color.WHITE)
    fun fillCircle(x: Int, y: Int, radius: Int, color: Color = Color.WHITE)

    // line drawing
    fun line(x1: Int, y1: Int, x2: Int, y2: Int, width: Int, color: Color)

    // texture drawing
    fun texture(texture: Texture, x: Int, y: Int)
    fun texture(texture: Texture, x: Int, y: Int, width: Int, height: Int)

    // tile drawing
    fun tilemap(tilemap: TileMap, tileset: Tileset, x: Int, y: Int)
    fun tile(tile: Tile, x: Int, y: Int)
    fun tile(tile: Tile, x: Int, y: Int, width: Int, height: Int)

    // camera RENDERING
    fun camera(camera: Camera)
    fun resetCamera()

    fun ui(builder: InterfaceContainer)

    // text drawing
    fun text(text: Text, font: Font, x: Int, y: Int) {
        font.render(this, text.content, x, y, text.color)
    }
}