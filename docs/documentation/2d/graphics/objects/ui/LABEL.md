# Label
yeah, it's just a DSL wrapper over draw.text() :)
```kt 
InterfaceBuilder {
    label {
        x = 350
        y = 250

        text = "Hello"
        // imagine that the font exists
        font = Font("fonts/myfont.ttf", 64)
        
        style {
            color = Color(0x686868)
            
            offsetX = 0
            offsetY = 0

            shadow = true
            shadowColor = Color.BLACK
            shadowOffsetX = 2 // default value - 2
            shadowOffsetY = 2 // default value - 2
        }
    }
}
```