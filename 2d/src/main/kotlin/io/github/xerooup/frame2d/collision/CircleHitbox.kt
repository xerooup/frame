package io.github.xerooup.frame2d.collision

import kotlin.math.sqrt

data class CircleHitbox(
    override var x: Float,
    override var y: Float,
    var radius: Float
) : Hitbox {

    override fun intersects(other: RectHitbox): Boolean {
        return other.intersects(this)
    }

    override fun intersects(other: CircleHitbox): Boolean {
        val dx = x - other.x
        val dy = y - other.y
        val distSq = dx * dx + dy * dy
        val radiusSum = radius + other.radius
        return distSq < radiusSum * radiusSum
    }

    override fun contains(px: Float, py: Float): Boolean {
        val dx = px - x
        val dy = py - y
        return (dx * dx + dy * dy) < (radius * radius)
    }

    override fun resolveCollision(other: RectHitbox): Vector2 {
        val result = other.resolveCollision(this)
        return Vector2(-result.x, -result.y) // invert direction
    }

    override fun resolveCollision(other: CircleHitbox): Vector2 {
        val dx = x - other.x
        val dy = y - other.y
        val dist = sqrt(dx * dx + dy * dy)

        if (dist == 0f) return Vector2(radius + other.radius, 0f) // circles at same point

        val overlap = (radius + other.radius) - dist
        val nx = dx / dist
        val ny = dy / dist

        return Vector2(nx * overlap, ny * overlap)
    }

    override fun centerX(): Float = x
    override fun centerY(): Float = y
}