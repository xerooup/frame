package io.github.xerooup.frame3d.graphics;

public class Color {
    public final float r, g, b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(int hex) {
        this(
                ((hex >> 16) & 0xFF) / 255f,
                ((hex >> 8) & 0xFF) / 255f,
                (hex & 0xFF) / 255f
        );
    }

    public Color(int r, int g, int b) {
        this(r / 255f, g / 255f, b / 255f);
    }

    public static final Color WHITE = new Color(0xFFFFFF);
    public static final Color BLACK = new Color(0x000000);
    public static final Color RED = new Color(0xFF0000);
    public static final Color GREEN = new Color(0x00FF00);
    public static final Color BLUE = new Color(0x0000FF);
    public static final Color YELLOW = new Color(0xFFFF00);
    public static final Color CYAN = new Color(0x00FFFF);
    public static final Color MAGENTA = new Color(0xFF00FF);

    int toRgb888() {
        return ((int)(r * 255) << 16) |
                ((int)(g * 255) << 8) |
                ((int)(b * 255));
    }
}