# AssetManager
To avoid storing each object in a variable or creating a new object every frame (which can cause memory leaks), you can use AssetManager, where you can load and retrieve your objects.
```kt 
// id is needed so that we can somehow retrieve our object
AssetManager.loadTexture("id", Texture)
AssetManager.loadFont("id", Font)
AssetManager.loadSound("id", Sound)
AssetManager.loadTileset("id", Tileset)
AssetManager.loadTilemap("id", Tilemap)

// each load-method also returns the same object you passed, allowing you to write like this:
val myTexture = AssetManager.loadTexture("my_texture", Texture("textures/my_texture.png"))
// instead of:
AssetManager.loadTexture("my_texture", Texture("textures/my_texture.png"))
val myTexture = AssetManager.getTexture("my_texture")
```
```kt 
AssetManager.getTexture("id")
AssetManager.getFont("id")
AssetManager.getSound("id")
AssetManager.getTileset("id")
AssetManager.getTilemap("id")
```
```kt 
AssetManager.unloadTexture("id")
AssetManager.unloadFont("id")
AssetManager.unloadSound("id")
AssetManager.unloadTileset("id")
AssetManager.unloadTilemap("id")
```
```kt 
AssetManager.clearTextures()
AssetManager.clearFonts()
AssetManager.clearSounds()
AssetManager.clearTilesets()
AssetManager.clearTilemaps()
```
and also we can clear the entire storage:
```kt 
AssetManager.clear()
```