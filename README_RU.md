# Frame
**Frame** – игровой движок на kotlin, ориентированный на простоту создания игр и читаемость кода.<br>
*В настоящее время поддерживается только 2D-графика, но возможно будет поддержка 3D или 2.5D.*<br>

**Распространяется под лицензией GNU Lesser General Public License v3.0:**<br>
Имеет открытый исходный код, который можно свободно использовать и модифицировать, <br>
включать в коммерческие продукты при условии, что **Frame** и его изменения остаются открытыми.

## Установка
`build.gradle`:
```groovy 
implementation 'io.github.xerooup:frame-2d:0.1.1'
```
`build.gradle.kts`:
```kt 
implementation("io.github.xerooup:frame-2d:0.1.1")
```
`pom.xml`:
```xml
<dependency>
    <groupId>io.github.xerooup</groupId>
    <artifactId>frame-2d</artifactId>
    <version>0.1.1</version>
</dependency>
```

## Быстрый старт
Для быстрого ознакомления с **Frame** обратитесь к [документации.](https://xerooup.github.io/frame)

### Минимальный код
```kt
class Example : Game() {
    // используем настройки по умолчанию
    override fun settings(settings: Settings) {}
}

fun main() = Game.run(Example())
```
