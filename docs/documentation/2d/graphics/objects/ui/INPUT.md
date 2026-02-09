# Input
```kt 
InterfaceBuilder {
    input {
        text = ""
        // imagine that the font exists
        font = Font("fonts/myfont.ttf", 32)
        // will it be focused on creation
        isFocused = true
        
        clearOnAction = true
        maxLength = 256
        
        style {
            backgroundColor = Color(0x616161)
            focusedColor = Color(0x737373)
            textColor = Color.WHITE
            caretColor = Color.WHITE // |

            var textOffsetX: Int = 8 // 8 - default
            var textOffsetY: Int = 0

            shadow = true
            shadowColor = Color(0x000000)
            shadowOffsetX = 4 // 4 - default
            shadowOffsetY = 4 // 4 - default
        }

        // when the user finishes input and presses Enter
        onAction = { result ->
            println(result)
        }
    }
}
```