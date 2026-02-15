package io.github.xerooup.frame3d.input;

import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class Mouse {
    private static long window;

    private static double x = 0;
    private static double y = 0;
    private static double lastX = 0;
    private static double lastY = 0;
    private static double deltaX = 0;
    private static double deltaY = 0;
    private static double scrollX = 0;
    private static double scrollY = 0;

    private static final Map<Integer, Boolean> buttons = new HashMap<>();
    private static final Map<Integer, Boolean> buttonsPressed = new HashMap<>();
    private static final Map<Integer, Boolean> buttonsReleased = new HashMap<>();

    private static boolean cursorLocked = false;

    // Initialize mouse input
    public static void init(long windowHandle) {
        window = windowHandle;

        // Mouse position callback
        GLFW.glfwSetCursorPosCallback(window, (win, xpos, ypos) -> {
            x = xpos;
            y = ypos;
        });

        // Mouse button callback
        GLFW.glfwSetMouseButtonCallback(window, (win, button, action, mods) -> {
            if (action == GLFW.GLFW_PRESS) {
                buttons.put(button, true);
                buttonsPressed.put(button, true);
            } else if (action == GLFW.GLFW_RELEASE) {
                buttons.put(button, false);
                buttonsReleased.put(button, true);
            }
        });

        // Mouse scroll callback
        GLFW.glfwSetScrollCallback(window, (win, xoffset, yoffset) -> {
            scrollX = xoffset;
            scrollY = yoffset;
        });
    }

    // Update state (call at the end of frame)
    public static void update() {
        deltaX = x - lastX;
        deltaY = y - lastY;
        lastX = x;
        lastY = y;

        buttonsPressed.clear();
        buttonsReleased.clear();
        scrollX = 0;
        scrollY = 0;
    }

    // Mouse position
    public static double getX() { return x; }
    public static double getY() { return y; }

    // Mouse delta (movement since last frame)
    public static double getDeltaX() { return deltaX; }
    public static double getDeltaY() { return deltaY; }

    // Mouse scroll
    public static double getScrollX() { return scrollX; }
    public static double getScrollY() { return scrollY; }

    // Mouse buttons
    public static boolean isButtonDown(int button) {
        return buttons.getOrDefault(button, false);
    }

    public static boolean isButtonPressed(int button) {
        return buttonsPressed.getOrDefault(button, false);
    }

    public static boolean isButtonReleased(int button) {
        return buttonsReleased.getOrDefault(button, false);
    }

    // Cursor lock/unlock (for FPS camera)
    public static void lockCursor() {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        cursorLocked = true;
    }

    public static void unlockCursor() {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        cursorLocked = false;
    }

    public static boolean isCursorLocked() {
        return cursorLocked;
    }

    public static void setCursorPosition(double xpos, double ypos) {
        GLFW.glfwSetCursorPos(window, xpos, ypos);
        x = xpos;
        y = ypos;
        lastX = xpos;
        lastY = ypos;
    }

    // Mouse button constants
    public static final int LEFT = GLFW.GLFW_MOUSE_BUTTON_LEFT;
    public static final int RIGHT = GLFW.GLFW_MOUSE_BUTTON_RIGHT;
    public static final int MIDDLE = GLFW.GLFW_MOUSE_BUTTON_MIDDLE;
}