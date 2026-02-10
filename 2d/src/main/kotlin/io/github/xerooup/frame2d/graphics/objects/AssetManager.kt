package io.github.xerooup.frame2d.graphics.objects

import io.github.xerooup.frame2d.graphics.objects.text.Font
import io.github.xerooup.frame2d.graphics.objects.tile.TileMap
import io.github.xerooup.frame2d.graphics.objects.tile.Tileset
import io.github.xerooup.frame2d.audio.Sound

object AssetManager {
    private val textures = mutableMapOf<String, Texture>()
    private val fonts = mutableMapOf<String, Font>()
    private val sounds = mutableMapOf<String, Sound>()
    private val tilesets = mutableMapOf<String, Tileset>()
    private val tilemaps = mutableMapOf<String, TileMap>()

    fun loadTexture(id: String, texture: Texture): Texture = texture.also { textures[id] = it }
    fun loadFont(id: String, font: Font): Font = font.also { fonts[id] = it }
    fun loadSound(id: String, sound: Sound): Sound = sound.also { sounds[id] = it }
    fun loadTileset(id: String, tileset: Tileset): Tileset = tileset.also { tilesets[id] = it }
    fun loadTilemap(id: String, tilemap: TileMap): TileMap = tilemap.also { tilemaps[id] = it }

    fun getTexture(id: String) = textures[id] ?: error("Texture '$id' not found")
    fun getFont(id: String) = fonts[id] ?: error("Font '$id' not found")
    fun getSound(id: String) = sounds[id] ?: error("Sound '$id' not found")
    fun getTileset(id: String) = tilesets[id] ?: error("Tileset '$id' not found")
    fun getTilemap(id: String) = tilemaps[id] ?: error("TileMap '$id' not found")

    fun unloadTexture(id: String) {
        textures[id]?.dispose()
        textures.remove(id)
    }

    fun unloadSound(id: String) {
        sounds[id]?.dispose()
        sounds.remove(id)
    }

    fun unloadFont(id: String) = fonts.remove(id)
    fun unloadTileset(id: String) = tilesets.remove(id)
    fun unloadTilemap(id: String) = tilemaps.remove(id)

    fun clearTextures() {
        textures.values.forEach { it.dispose() }
        textures.clear()
    }

    fun clearSounds() {
        sounds.values.forEach { it.dispose() }
        sounds.clear()
    }

    fun clearFonts() = fonts.clear()
    fun clearTilesets() = tilesets.clear()
    fun clearTilemaps() = tilemaps.clear()

    fun clear() {
        clearTextures()
        clearSounds()
        clearFonts()
        clearTilesets()
        clearTilemaps()
    }
}