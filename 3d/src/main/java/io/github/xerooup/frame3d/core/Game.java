package io.github.xerooup.frame3d.core;

import io.github.xerooup.frame3d.core.internal.Engine;
import io.github.xerooup.frame3d.graphics.Color;
import io.github.xerooup.frame3d.graphics.RenderContext;

public abstract class Game {
    public abstract void settings(Settings settings);

    public void create() {}
    public void update(float delta) {}
    public void render(RenderContext render) {}

    public static void run(Game game) {
        Settings settings = new Settings();
        game.settings(settings);
        new Engine(settings, game).run();
    }

    public static void stop() {
        Engine.forceStop();
    }

    public static int fps = 0;
    public static float uptime = 0f;
}