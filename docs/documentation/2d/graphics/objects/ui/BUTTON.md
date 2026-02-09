# Button
let's look at an example of creating a button:
```kt 
InterfaceBuilder {
    button {
        x = 350; y = 250
        width = 350; height = 100
        
        label = Text("Click me!", Color.WHITE)
        // imagine that the font exists
        font = Font("fonts/myfont.ttf", 16)
        
        style {
            color = Color.BLACK
            hoverColor = Color(0x403f3f)
            labelAlign = TextAlign.LEFT // or .RIGHT, .CENTER

            shadow = false // if true, will create a simple shadow
            // shadowColor = ...
            // shadowOffsetX = ...
            // shadowOffsetY = ...
            
            textOffsetX = 0
            textOffsetY = 0
        }

        onClick = {
            println("button clicked!")
        }
    }
}
```