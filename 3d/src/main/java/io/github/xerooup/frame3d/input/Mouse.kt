package io.github.xerooup.frame3d.input

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWCursorPosCallbackI
import org.lwjgl.glfw.GLFWMouseButtonCallbackI
import org.lwjgl.glfw.GLFWScrollCallbackI

object Mouse {
    private var window: Long = 0

    // Mouse position
    var x: Double = 0.0
        private set
    var y: Double = 0.0
        private set
    private var lastX = 0.0
    private var lastY = 0.0

    // Mouse delta (movement since last frame)
    var deltaX: Double = 0.0
        private set
    var deltaY: Double = 0.0
        private set

    // Mouse scroll
    var scrollX: Double = 0.0
        private set
    var scrollY: Double = 0.0
        private set

    private val buttons: MutableMap<Int?, Boolean?> = HashMap<Int?, Boolean?>()
    private val buttonsPressed: MutableMap<Int?, Boolean?> = HashMap<Int?, Boolean?>()
    private val buttonsReleased: MutableMap<Int?, Boolean?> = HashMap<Int?, Boolean?>()

    var isCursorLocked: Boolean = false
        private set

    // Initialize mouse input
    @JvmStatic
    fun init(windowHandle: Long) {
        window = windowHandle

        // Mouse position callback
        GLFW.glfwSetCursorPosCallback(window, GLFWCursorPosCallbackI { win: Long, xpos: Double, ypos: Double ->
            x = xpos
            y = ypos
        })

        // Mouse button callback
        GLFW.glfwSetMouseButtonCallback(
            window,
            GLFWMouseButtonCallbackI { win: Long, button: Int, action: Int, mods: Int ->
                if (action == GLFW.GLFW_PRESS) {
                    buttons.put(button, true)
                    buttonsPressed.put(button, true)
                } else if (action == GLFW.GLFW_RELEASE) {
                    buttons.put(button, false)
                    buttonsReleased.put(button, true)
                }
            })

        // Mouse scroll callback
        GLFW.glfwSetScrollCallback(window, GLFWScrollCallbackI { win: Long, xoffset: Double, yoffset: Double ->
            scrollX = xoffset
            scrollY = yoffset
        })
    }

    // Update state (call at the end of frame)
    @JvmStatic
    fun update() {
        deltaX = x - lastX
        deltaY = y - lastY
        lastX = x
        lastY = y

        buttonsPressed.clear()
        buttonsReleased.clear()
        scrollX = 0.0
        scrollY = 0.0
    }

    // Mouse buttons
    fun isButtonDown(button: Int): Boolean {
        return buttons.getOrDefault(button, false)!!
    }

    fun isButtonPressed(button: Int): Boolean {
        return buttonsPressed.getOrDefault(button, false)!!
    }

    fun isButtonReleased(button: Int): Boolean {
        return buttonsReleased.getOrDefault(button, false)!!
    }

    // Cursor lock/unlock (for FPS camera)
    fun lockCursor() {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED)
        isCursorLocked = true
    }

    fun unlockCursor() {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL)
        isCursorLocked = false
    }

    fun setCursorPosition(xpos: Double, ypos: Double) {
        GLFW.glfwSetCursorPos(window, xpos, ypos)
        x = xpos
        y = ypos
        lastX = xpos
        lastY = ypos
    }

    // Mouse button constants
    val LEFT: Int = GLFW.GLFW_MOUSE_BUTTON_LEFT
    val RIGHT: Int = GLFW.GLFW_MOUSE_BUTTON_RIGHT
    val MIDDLE: Int = GLFW.GLFW_MOUSE_BUTTON_MIDDLE
}