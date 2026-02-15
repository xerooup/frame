package io.github.xerooup.frame3d.input;

import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    private static long window;
    private static final Map<Integer, Boolean> keys = new HashMap<>();
    private static final Map<Integer, Boolean> keysPressed = new HashMap<>();
    private static final Map<Integer, Boolean> keysReleased = new HashMap<>();

    // Initialize keyboard input
    public static void init(long windowHandle) {
        window = windowHandle;

        GLFW.glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            if (action == GLFW.GLFW_PRESS) {
                keys.put(key, true);
                keysPressed.put(key, true);
            } else if (action == GLFW.GLFW_RELEASE) {
                keys.put(key, false);
                keysReleased.put(key, true);
            }
        });
    }

    // Update state (call at the end of frame)
    public static void update() {
        keysPressed.clear();
        keysReleased.clear();
    }

    // Check if key is currently held down
    public static boolean isKeyDown(int key) {
        return keys.getOrDefault(key, false);
    }

    // Check if key was just pressed this frame
    public static boolean isKeyPressed(int key) {
        return keysPressed.getOrDefault(key, false);
    }

    // Check if key was just released this frame
    public static boolean isKeyReleased(int key) {
        return keysReleased.getOrDefault(key, false);
    }

    // Letters A-Z
    public static final int A = GLFW.GLFW_KEY_A;
    public static final int B = GLFW.GLFW_KEY_B;
    public static final int C = GLFW.GLFW_KEY_C;
    public static final int D = GLFW.GLFW_KEY_D;
    public static final int E = GLFW.GLFW_KEY_E;
    public static final int F = GLFW.GLFW_KEY_F;
    public static final int G = GLFW.GLFW_KEY_G;
    public static final int H = GLFW.GLFW_KEY_H;
    public static final int I = GLFW.GLFW_KEY_I;
    public static final int J = GLFW.GLFW_KEY_J;
    public static final int K = GLFW.GLFW_KEY_K;
    public static final int L = GLFW.GLFW_KEY_L;
    public static final int M = GLFW.GLFW_KEY_M;
    public static final int N = GLFW.GLFW_KEY_N;
    public static final int O = GLFW.GLFW_KEY_O;
    public static final int P = GLFW.GLFW_KEY_P;
    public static final int Q = GLFW.GLFW_KEY_Q;
    public static final int R = GLFW.GLFW_KEY_R;
    public static final int S = GLFW.GLFW_KEY_S;
    public static final int T = GLFW.GLFW_KEY_T;
    public static final int U = GLFW.GLFW_KEY_U;
    public static final int V = GLFW.GLFW_KEY_V;
    public static final int W = GLFW.GLFW_KEY_W;
    public static final int X = GLFW.GLFW_KEY_X;
    public static final int Y = GLFW.GLFW_KEY_Y;
    public static final int Z = GLFW.GLFW_KEY_Z;

    // Numbers 0-9
    public static final int NUM_0 = GLFW.GLFW_KEY_0;
    public static final int NUM_1 = GLFW.GLFW_KEY_1;
    public static final int NUM_2 = GLFW.GLFW_KEY_2;
    public static final int NUM_3 = GLFW.GLFW_KEY_3;
    public static final int NUM_4 = GLFW.GLFW_KEY_4;
    public static final int NUM_5 = GLFW.GLFW_KEY_5;
    public static final int NUM_6 = GLFW.GLFW_KEY_6;
    public static final int NUM_7 = GLFW.GLFW_KEY_7;
    public static final int NUM_8 = GLFW.GLFW_KEY_8;
    public static final int NUM_9 = GLFW.GLFW_KEY_9;

    // Function keys F1-F12
    public static final int F1 = GLFW.GLFW_KEY_F1;
    public static final int F2 = GLFW.GLFW_KEY_F2;
    public static final int F3 = GLFW.GLFW_KEY_F3;
    public static final int F4 = GLFW.GLFW_KEY_F4;
    public static final int F5 = GLFW.GLFW_KEY_F5;
    public static final int F6 = GLFW.GLFW_KEY_F6;
    public static final int F7 = GLFW.GLFW_KEY_F7;
    public static final int F8 = GLFW.GLFW_KEY_F8;
    public static final int F9 = GLFW.GLFW_KEY_F9;
    public static final int F10 = GLFW.GLFW_KEY_F10;
    public static final int F11 = GLFW.GLFW_KEY_F11;
    public static final int F12 = GLFW.GLFW_KEY_F12;

    // Arrow keys
    public static final int UP = GLFW.GLFW_KEY_UP;
    public static final int DOWN = GLFW.GLFW_KEY_DOWN;
    public static final int LEFT = GLFW.GLFW_KEY_LEFT;
    public static final int RIGHT = GLFW.GLFW_KEY_RIGHT;

    // Modifiers
    public static final int SHIFT = GLFW.GLFW_KEY_LEFT_SHIFT;
    public static final int RIGHT_SHIFT = GLFW.GLFW_KEY_RIGHT_SHIFT;
    public static final int CTRL = GLFW.GLFW_KEY_LEFT_CONTROL;
    public static final int RIGHT_CTRL = GLFW.GLFW_KEY_RIGHT_CONTROL;
    public static final int ALT = GLFW.GLFW_KEY_LEFT_ALT;
    public static final int RIGHT_ALT = GLFW.GLFW_KEY_RIGHT_ALT;
    public static final int SUPER = GLFW.GLFW_KEY_LEFT_SUPER; // Windows/Command key
    public static final int RIGHT_SUPER = GLFW.GLFW_KEY_RIGHT_SUPER;

    // Special keys
    public static final int SPACE = GLFW.GLFW_KEY_SPACE;
    public static final int ENTER = GLFW.GLFW_KEY_ENTER;
    public static final int TAB = GLFW.GLFW_KEY_TAB;
    public static final int BACKSPACE = GLFW.GLFW_KEY_BACKSPACE;
    public static final int ESC = GLFW.GLFW_KEY_ESCAPE;
    public static final int DELETE = GLFW.GLFW_KEY_DELETE;
    public static final int INSERT = GLFW.GLFW_KEY_INSERT;
    public static final int HOME = GLFW.GLFW_KEY_HOME;
    public static final int END = GLFW.GLFW_KEY_END;
    public static final int PAGE_UP = GLFW.GLFW_KEY_PAGE_UP;
    public static final int PAGE_DOWN = GLFW.GLFW_KEY_PAGE_DOWN;
    public static final int CAPS_LOCK = GLFW.GLFW_KEY_CAPS_LOCK;
    public static final int NUM_LOCK = GLFW.GLFW_KEY_NUM_LOCK;
    public static final int SCROLL_LOCK = GLFW.GLFW_KEY_SCROLL_LOCK;
    public static final int PAUSE = GLFW.GLFW_KEY_PAUSE;
    public static final int PRINT_SCREEN = GLFW.GLFW_KEY_PRINT_SCREEN;

    // Symbols
    public static final int MINUS = GLFW.GLFW_KEY_MINUS;
    public static final int EQUALS = GLFW.GLFW_KEY_EQUAL;
    public static final int LEFT_BRACKET = GLFW.GLFW_KEY_LEFT_BRACKET;
    public static final int RIGHT_BRACKET = GLFW.GLFW_KEY_RIGHT_BRACKET;
    public static final int SEMICOLON = GLFW.GLFW_KEY_SEMICOLON;
    public static final int APOSTROPHE = GLFW.GLFW_KEY_APOSTROPHE;
    public static final int GRAVE_ACCENT = GLFW.GLFW_KEY_GRAVE_ACCENT; // `
    public static final int BACKSLASH = GLFW.GLFW_KEY_BACKSLASH;
    public static final int COMMA = GLFW.GLFW_KEY_COMMA;
    public static final int PERIOD = GLFW.GLFW_KEY_PERIOD;
    public static final int SLASH = GLFW.GLFW_KEY_SLASH;

    // Numpad
    public static final int NUMPAD_0 = GLFW.GLFW_KEY_KP_0;
    public static final int NUMPAD_1 = GLFW.GLFW_KEY_KP_1;
    public static final int NUMPAD_2 = GLFW.GLFW_KEY_KP_2;
    public static final int NUMPAD_3 = GLFW.GLFW_KEY_KP_3;
    public static final int NUMPAD_4 = GLFW.GLFW_KEY_KP_4;
    public static final int NUMPAD_5 = GLFW.GLFW_KEY_KP_5;
    public static final int NUMPAD_6 = GLFW.GLFW_KEY_KP_6;
    public static final int NUMPAD_7 = GLFW.GLFW_KEY_KP_7;
    public static final int NUMPAD_8 = GLFW.GLFW_KEY_KP_8;
    public static final int NUMPAD_9 = GLFW.GLFW_KEY_KP_9;
    public static final int NUMPAD_DECIMAL = GLFW.GLFW_KEY_KP_DECIMAL;
    public static final int NUMPAD_DIVIDE = GLFW.GLFW_KEY_KP_DIVIDE;
    public static final int NUMPAD_MULTIPLY = GLFW.GLFW_KEY_KP_MULTIPLY;
    public static final int NUMPAD_SUBTRACT = GLFW.GLFW_KEY_KP_SUBTRACT;
    public static final int NUMPAD_ADD = GLFW.GLFW_KEY_KP_ADD;
    public static final int NUMPAD_ENTER = GLFW.GLFW_KEY_KP_ENTER;
    public static final int NUMPAD_EQUAL = GLFW.GLFW_KEY_KP_EQUAL;
}