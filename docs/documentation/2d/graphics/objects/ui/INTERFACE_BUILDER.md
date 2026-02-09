# Interface Builder
Using InterfaceBuilder, we can create various game menus, chats, and much more!
```kt 
// declare a variable that will store our interface
private lateinit var ui: InterfaceContainer

// and then initialize it in create()
override fun create() {
    ui = InterfaceBuilder {
        // here we will create the interface
        // check out other ui sections to familiarize yourself with the syntax
    }
}
```