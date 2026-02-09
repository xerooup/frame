# Keyboard input
keyboard input - the system that processes key presses on the keyboard and converts them into actions in the game (for example, character movement).<br><br>
let's not write the full code, but instead show an example of its usage in the **update** function right away:
```kt 
override fun update(delta: Float) {
    Keyboard.isKeyPressed(Keyboard.Keys.ANY_KEY)
        
    // returns true only on the frame when key was pressed
    Keyboard.isKeyJustPressed(Keyboard.Keys.ANY_KEY)
}
```