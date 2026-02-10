# Frame
**Frame** – a kotlin game engine focused on simple game creation and code readability.<br>
*Currently, only 2D graphics are supported, but 3D or 2.5D support may be added in the future.*<br>

**Licensed under GNU Lesser General Public License v3.0:**<br>
It has open-source code that can be freely used, modified,<br>
and include in commercial products, provided that the **Frame** itself and its modifications remain open.

## Installation
`build.gradle`:
```groovy 
implementation 'io.github.xerooup:frame-2d:0.2.3'
```
`build.gradle.kts`:
```kt 
implementation("io.github.xerooup:frame-2d:0.2.3")
```
`pom.xml`:
```xml
<dependency>
    <groupId>io.github.xerooup</groupId>
    <artifactId>frame-2d</artifactId>
    <version>0.2.3</version>
</dependency>
```

## Quick start
For a quick dive into **Frame**, refer to the [documentation.](https://xerooup.github.io/frame)

### Minimal code
```kt
class Example : Game() {
    // using default settings
    override fun settings(settings: Settings) {}
}

fun main() = Game.run(Example())
```
