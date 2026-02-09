# Mouse input
mouse input - a system that processes mouse button clicks or obtains the current cursor position and converts them into actions in the game (for example, clicking a button).<br><br>
let's not write the full code, but instead show an example of its usage in the **update** function right away:
```kt 
override fun update(delta: Float) {
    Mouse.isButtonPressed(Mouse.Buttons.ANY_BUTTON)
    
    // returns true only on the frame when button was pressed
    Mouse.isButtonJustPressed(Mouse.Buttons.ANY_BUTTON)
    
    // returns a negative value if backward and a positive value if forward
    Mouse.getScrollDirection()
    
    //methods that check which direction the scroll was turned
    Mouse.isScrolledUp()
    Mouse.isScrolledDown()
    
    // get last cursor x or y
    Mouse.getX()
    Mouse.getY()
}
```