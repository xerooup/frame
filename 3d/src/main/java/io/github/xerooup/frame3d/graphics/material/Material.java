package io.github.xerooup.frame3d.graphics.material;

import io.github.xerooup.frame3d.graphics.Color;

public class Material {
    private final MaterialType type;
    private Color color;
    private Texture texture;

    // Color material
    public Material(Color color) {
        this.type = MaterialType.COLOR;
        this.color = color;
    }

    // Texture material (UV mapped)
    public Material(Texture texture) {
        this.type = MaterialType.TEXTURE;
        this.texture = texture;
    }

    // Getters
    public MaterialType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Texture getTexture() {
        return texture;
    }

    // Factory methods
    public static Material color(Color color) {
        return new Material(color);
    }

    public static Material texture(String path) {
        return new Material(new Texture(path));
    }
}