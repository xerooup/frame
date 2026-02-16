package io.github.xerooup.frame3d.input

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWKeyCallbackI

object Keyboard {
    private var window: Long = 0
    private val keys: MutableMap<Int?, Boolean?> = HashMap<Int?, Boolean?>()
    private val keysPressed: MutableMap<Int?, Boolean?> = HashMap<Int?, Boolean?>()
    private val keysReleased: MutableMap<Int?, Boolean?> = HashMap<Int?, Boolean?>()

    // Initialize keyboard input
    @JvmStatic
    fun init(windowHandle: Long) {
        window = windowHandle

        GLFW.glfwSetKeyCallback(window, GLFWKeyCallbackI { win: Long, key: Int, scancode: Int, action: Int, mods: Int ->
            if (action == GLFW.GLFW_PRESS) {
                keys[key] = true
                keysPressed[key] = true
            } else if (action == GLFW.GLFW_RELEASE) {
                keys[key] = false
                keysReleased[key] = true
            }
        })
    }

    // Update state (call at the end of frame)
    @JvmStatic
    fun update() {
        keysPressed.clear()
        keysReleased.clear()
    }

    // Check if key is currently held down
    fun isKeyDown(key: Int): Boolean {
        return keys.getOrDefault(key, false)!!
    }

    // Check if key was just pressed this frame
    fun isKeyPressed(key: Int): Boolean {
        return keysPressed.getOrDefault(key, false)!!
    }

    // Check if key was just released this frame
    fun isKeyReleased(key: Int): Boolean {
        return keysReleased.getOrDefault(key, false)!!
    }

    // Letters A-Z
    const val A = GLFW.GLFW_KEY_A
    const val B = GLFW.GLFW_KEY_B
    const val C = GLFW.GLFW_KEY_C
    const val D = GLFW.GLFW_KEY_D
    const val E = GLFW.GLFW_KEY_E
    const val F = GLFW.GLFW_KEY_F
    const val G = GLFW.GLFW_KEY_G
    const val H = GLFW.GLFW_KEY_H
    const val I = GLFW.GLFW_KEY_I
    const val J = GLFW.GLFW_KEY_J
    const val K = GLFW.GLFW_KEY_K
    const val L = GLFW.GLFW_KEY_L
    const val M = GLFW.GLFW_KEY_M
    const val N = GLFW.GLFW_KEY_N
    const val O = GLFW.GLFW_KEY_O
    const val P = GLFW.GLFW_KEY_P
    const val Q = GLFW.GLFW_KEY_Q
    const val R = GLFW.GLFW_KEY_R
    const val S = GLFW.GLFW_KEY_S
    const val T = GLFW.GLFW_KEY_T
    const val U = GLFW.GLFW_KEY_U
    const val V = GLFW.GLFW_KEY_V
    const val W = GLFW.GLFW_KEY_W
    const val X = GLFW.GLFW_KEY_X
    const val Y = GLFW.GLFW_KEY_Y
    const val Z = GLFW.GLFW_KEY_Z

    // Numbers 0-9
    const val NUM_0 = GLFW.GLFW_KEY_0
    const val NUM_1 = GLFW.GLFW_KEY_1
    const val NUM_2 = GLFW.GLFW_KEY_2
    const val NUM_3 = GLFW.GLFW_KEY_3
    const val NUM_4 = GLFW.GLFW_KEY_4
    const val NUM_5 = GLFW.GLFW_KEY_5
    const val NUM_6 = GLFW.GLFW_KEY_6
    const val NUM_7 = GLFW.GLFW_KEY_7
    const val NUM_8 = GLFW.GLFW_KEY_8
    const val NUM_9 = GLFW.GLFW_KEY_9

    // Function keys F1-F12
    const val F1 = GLFW.GLFW_KEY_F1
    const val F2 = GLFW.GLFW_KEY_F2
    const val F3 = GLFW.GLFW_KEY_F3
    const val F4 = GLFW.GLFW_KEY_F4
    const val F5 = GLFW.GLFW_KEY_F5
    const val F6 = GLFW.GLFW_KEY_F6
    const val F7 = GLFW.GLFW_KEY_F7
    const val F8 = GLFW.GLFW_KEY_F8
    const val F9 = GLFW.GLFW_KEY_F9
    const val F10 = GLFW.GLFW_KEY_F10
    const val F11 = GLFW.GLFW_KEY_F11
    const val F12 = GLFW.GLFW_KEY_F12

    // Arrow keys
    const val UP = GLFW.GLFW_KEY_UP
    const val DOWN = GLFW.GLFW_KEY_DOWN
    const val LEFT = GLFW.GLFW_KEY_LEFT
    const val RIGHT = GLFW.GLFW_KEY_RIGHT

    // Modifiers
    const val SHIFT = GLFW.GLFW_KEY_LEFT_SHIFT
    const val RIGHT_SHIFT = GLFW.GLFW_KEY_RIGHT_SHIFT
    const val CTRL = GLFW.GLFW_KEY_LEFT_CONTROL
    const val RIGHT_CTRL = GLFW.GLFW_KEY_RIGHT_CONTROL
    const val ALT = GLFW.GLFW_KEY_LEFT_ALT
    const val RIGHT_ALT = GLFW.GLFW_KEY_RIGHT_ALT
    const val SUPER = GLFW.GLFW_KEY_LEFT_SUPER
    const val RIGHT_SUPER = GLFW.GLFW_KEY_RIGHT_SUPER

    // Special keys
    const val SPACE = GLFW.GLFW_KEY_SPACE
    const val ENTER = GLFW.GLFW_KEY_ENTER
    const val TAB = GLFW.GLFW_KEY_TAB
    const val BACKSPACE = GLFW.GLFW_KEY_BACKSPACE
    const val ESC = GLFW.GLFW_KEY_ESCAPE
    const val DELETE = GLFW.GLFW_KEY_DELETE
    const val INSERT = GLFW.GLFW_KEY_INSERT
    const val HOME = GLFW.GLFW_KEY_HOME
    const val END = GLFW.GLFW_KEY_END
    const val PAGE_UP = GLFW.GLFW_KEY_PAGE_UP
    const val PAGE_DOWN = GLFW.GLFW_KEY_PAGE_DOWN
    const val CAPS_LOCK = GLFW.GLFW_KEY_CAPS_LOCK
    const val NUM_LOCK = GLFW.GLFW_KEY_NUM_LOCK
    const val SCROLL_LOCK = GLFW.GLFW_KEY_SCROLL_LOCK
    const val PAUSE = GLFW.GLFW_KEY_PAUSE
    const val PRINT_SCREEN = GLFW.GLFW_KEY_PRINT_SCREEN

    // Symbols
    const val MINUS = GLFW.GLFW_KEY_MINUS
    const val EQUALS = GLFW.GLFW_KEY_EQUAL
    const val LEFT_BRACKET = GLFW.GLFW_KEY_LEFT_BRACKET
    const val RIGHT_BRACKET = GLFW.GLFW_KEY_RIGHT_BRACKET
    const val SEMICOLON = GLFW.GLFW_KEY_SEMICOLON
    const val APOSTROPHE = GLFW.GLFW_KEY_APOSTROPHE
    const val GRAVE_ACCENT = GLFW.GLFW_KEY_GRAVE_ACCENT
    const val BACKSLASH = GLFW.GLFW_KEY_BACKSLASH
    const val COMMA = GLFW.GLFW_KEY_COMMA
    const val PERIOD = GLFW.GLFW_KEY_PERIOD
    const val SLASH = GLFW.GLFW_KEY_SLASH

    // Numpad
    const val NUMPAD_0 = GLFW.GLFW_KEY_KP_0
    const val NUMPAD_1 = GLFW.GLFW_KEY_KP_1
    const val NUMPAD_2 = GLFW.GLFW_KEY_KP_2
    const val NUMPAD_3 = GLFW.GLFW_KEY_KP_3
    const val NUMPAD_4 = GLFW.GLFW_KEY_KP_4
    const val NUMPAD_5 = GLFW.GLFW_KEY_KP_5
    const val NUMPAD_6 = GLFW.GLFW_KEY_KP_6
    const val NUMPAD_7 = GLFW.GLFW_KEY_KP_7
    const val NUMPAD_8 = GLFW.GLFW_KEY_KP_8
    const val NUMPAD_9 = GLFW.GLFW_KEY_KP_9
    const val NUMPAD_DECIMAL = GLFW.GLFW_KEY_KP_DECIMAL
    const val NUMPAD_DIVIDE = GLFW.GLFW_KEY_KP_DIVIDE
    const val NUMPAD_MULTIPLY = GLFW.GLFW_KEY_KP_MULTIPLY
    const val NUMPAD_SUBTRACT = GLFW.GLFW_KEY_KP_SUBTRACT
    const val NUMPAD_ADD = GLFW.GLFW_KEY_KP_ADD
    const val NUMPAD_ENTER = GLFW.GLFW_KEY_KP_ENTER
    const val NUMPAD_EQUAL = GLFW.GLFW_KEY_KP_EQUAL
}