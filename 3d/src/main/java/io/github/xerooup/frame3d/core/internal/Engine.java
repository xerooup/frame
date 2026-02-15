package io.github.xerooup.frame3d.core.internal;

import io.github.xerooup.frame3d.core.Game;
import io.github.xerooup.frame3d.core.Settings;
import io.github.xerooup.frame3d.graphics.Color;
import io.github.xerooup.frame3d.graphics.internal.RenderContextImpl;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Engine {
    private final Settings settings;
    private final Game game;
    private long window;
    private boolean running = false;
    private int frameCount = 0;

    private static Engine instance;

    public Engine(Settings settings, Game game) {
        this.settings = settings;
        this.game = game;
    }

    public static void forceStop() {
        if (instance != null) {
            instance.running = false;
        }
    }

    public void run() {
        instance = this;

        try {
            init();
            game.create();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, settings.resizable ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_DECORATED, settings.decorated ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        window = glfwCreateWindow(settings.width, settings.height, settings.title, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("failed to create GLFW window");
        }

        if (settings.windowX != null && settings.windowY != null) {
            glfwSetWindowPos(window, settings.windowX, settings.windowY);
        } else {
            var vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            if (vidmode != null) {
                glfwSetWindowPos(window,
                        (vidmode.width() - settings.width) / 2,
                        (vidmode.height() - settings.height) / 2
                );
            }
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glCullFace(GL_BACK);

        Color bg = settings.background;
        glClearColor(bg.r, bg.g, bg.b, 1f);
    }

    private void loop() {
        running = true;
        double startTime = glfwGetTime();
        double lastFpsTime = startTime;
        double lastTime = startTime;

        RenderContextImpl renderContext = new RenderContextImpl(settings.width, settings.height);

        while (!glfwWindowShouldClose(window) && running) {
            double currentTime = glfwGetTime();
            float delta = (float)(currentTime - lastTime);
            lastTime = currentTime;

            frameCount++;
            if (currentTime - lastFpsTime >= 1.0) {
                Game.fps = frameCount;
                frameCount = 0;
                lastFpsTime = currentTime;
            }

            Game.uptime = (float)(currentTime - startTime);

            game.update(delta);

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            game.render(renderContext);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        renderContext.cleanup();
    }

    private void cleanup() {
        try {
            if (window != 0) {
                glfwFreeCallbacks(window);
                glfwDestroyWindow(window);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            glfwTerminate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (GLFWErrorCallback callback = glfwSetErrorCallback(null)) {
            if (callback != null) {
                callback.free();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}