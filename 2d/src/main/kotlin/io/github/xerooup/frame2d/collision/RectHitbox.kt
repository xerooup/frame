package io.github.xerooup.frame2d.collision

import kotlin.math.abs
import kotlin.math.sqrt

data class RectHitbox(
    override var x: Float,
    override var y: Float,
    var width: Float,
    var height: Float
) : Hitbox {

    override fun intersects(other: RectHitbox): Boolean {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y
    }

    override fun intersects(other: CircleHitbox): Boolean {
        // find closest point on rect to circle center
        val closestX = other.x.coerceIn(x, x + width)
        val closestY = other.y.coerceIn(y, y + height)

        val dx = other.x - closestX
        val dy = other.y - closestY

        return (dx * dx + dy * dy) < (other.radius * other.radius)
    }

    override fun contains(px: Float, py: Float): Boolean {
        return px >= x && px < x + width &&
                py >= y && py < y + height
    }

    override fun resolveCollision(other: RectHitbox): Vector2 {
        val dx = (x + width/2) - (other.x + other.width/2)
        val dy = (y + height/2) - (other.y + other.height/2)

        val overlapX = width/2 + other.width/2 - abs(dx)
        val overlapY = height/2 + other.height/2 - abs(dy)

        return if (overlapX < overlapY) {
            Vector2(if (dx > 0) overlapX else -overlapX, 0f)
        } else {
            Vector2(0f, if (dy > 0) overlapY else -overlapY)
        }
    }

    override fun resolveCollision(other: CircleHitbox): Vector2 {
        // find closest point on rect
        val closestX = other.x.coerceIn(x, x + width)
        val closestY = other.y.coerceIn(y, y + height)

        val dx = other.x - closestX
        val dy = other.y - closestY
        val dist = sqrt(dx * dx + dy * dy)

        if (dist == 0f) {
            // circle inside rect - push to nearest edge
            val toLeft = other.x - x
            val toRight = (x + width) - other.x
            val toTop = other.y - y
            val toBottom = (y + height) - other.y

            val minDist = minOf(toLeft, toRight, toTop, toBottom)
            return when (minDist) {
                toLeft -> Vector2(-(other.radius + toLeft), 0f)
                toRight -> Vector2(other.radius + toRight, 0f)
                toTop -> Vector2(0f, -(other.radius + toTop))
                else -> Vector2(0f, other.radius + toBottom)
            }
        }

        val overlap = other.radius - dist
        val nx = dx / dist
        val ny = dy / dist

        return Vector2(-nx * overlap, -ny * overlap)
    }

    fun left(): Float = x
    fun right(): Float = x + width
    fun top(): Float = y
    fun bottom(): Float = y + height

    override fun centerX(): Float = x + width / 2
    override fun centerY(): Float = y + height / 2
}