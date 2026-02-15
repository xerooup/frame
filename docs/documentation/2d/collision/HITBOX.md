## Hitbox
using hitboxes, we can set boundaries for entities and objects, thereby allowing us to check for collisions with other objects or entities.<br><br>
let's examine an example of using hitboxes:
```kt
// create hitboxes
val rect = RectHitbox(100f, 100f, 50f, 50f)
val circle = CircleHitbox(200f, 200f, 25f)

// rect-rect collision
val rect2 = RectHitbox(120f, 120f, 30f, 30f)
rect.intersects(rect2)

// circle-circle collision
val circle2 = CircleHitbox(220f, 220f, 15f)
circle.intersects(circle2)

// rect-circle collision works automatically
rect.intersects(circle)
circle.intersects(rect)

// check if a point is inside the hitbox
rect.contains(mouseX, mouseY)
circle.contains(mouseX, mouseY)

// rect-specific methods
val left = rect.left()
val right = rect.right()
val top = rect.top()
val bottom = rect.bottom()

// circle-specific property
val radius = circle.radius

// get center coordinates (works for both)
val centerX = rect.centerX()
val centerY = circle.centerY()

// resolve collision returns Vector2 with correction offsets
val correction = rect.resolveCollision(circle)
val correction2 = rect.resolveCollision(rect2)
val correction3 = circle.resolveCollision(circle2)

// Vector2 contains x and y components
println("Correction: x=${correction.x}, y=${correction.y}")
```