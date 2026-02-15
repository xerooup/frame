package io.github.xerooup.frame2d.entity

import io.github.xerooup.frame2d.collision.CircleHitbox
import io.github.xerooup.frame2d.graphics.DrawContext
import io.github.xerooup.frame2d.collision.Hitbox
import io.github.xerooup.frame2d.collision.RectHitbox

abstract class Entity {
    var x: Float = 0f
    var y: Float = 0f
    var width: Float = 32f
    var height: Float = 32f
    var hitboxType: HitboxType = HitboxType.RECT

    open fun create() {}
    open fun update(delta: Float) {}
    open fun render(draw: DrawContext) {}

    open fun getBounds(): Hitbox = when (hitboxType) {
        HitboxType.RECT -> RectHitbox(x, y, width, height)
        HitboxType.CIRCLE -> CircleHitbox(x + width/2, y + height/2, width/2)
    }

    fun intersects(other: Entity): Boolean {
        val bounds = getBounds()
        val otherBounds = other.getBounds()

        return when (bounds) {
            is RectHitbox -> when (otherBounds) {
                is RectHitbox -> bounds.intersects(otherBounds)
                is CircleHitbox -> bounds.intersects(otherBounds)
            }
            is CircleHitbox -> when (otherBounds) {
                is RectHitbox -> bounds.intersects(otherBounds)
                is CircleHitbox -> bounds.intersects(otherBounds)
            }
        }
    }

    fun collidesAny(entities: List<Entity>): Entity? {
        for (entity in entities) {
            if (entity !== this && intersects(entity)) {
                return entity
            }
        }
        return null
    }

    fun collidesAny(hitboxes: List<Hitbox>): Hitbox? {
        val entityBounds = getBounds()
        for (hitbox in hitboxes) {
            val collides = when (entityBounds) {
                is RectHitbox -> when (hitbox) {
                    is RectHitbox -> entityBounds.intersects(hitbox)
                    is CircleHitbox -> entityBounds.intersects(hitbox)
                }
                is CircleHitbox -> when (hitbox) {
                    is RectHitbox -> entityBounds.intersects(hitbox)
                    is CircleHitbox -> entityBounds.intersects(hitbox)
                }
            }
            if (collides) return hitbox
        }
        return null
    }
}