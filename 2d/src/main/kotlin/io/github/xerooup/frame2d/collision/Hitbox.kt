package io.github.xerooup.frame2d.collision

sealed interface Hitbox {
    var x: Float
    var y: Float

    fun intersects(other: RectHitbox): Boolean
    fun intersects(other: CircleHitbox): Boolean

    fun contains(px: Float, py: Float): Boolean

    fun resolveCollision(other: RectHitbox): Vector2
    fun resolveCollision(other: CircleHitbox): Vector2

    fun centerX(): Float
    fun centerY(): Float
}