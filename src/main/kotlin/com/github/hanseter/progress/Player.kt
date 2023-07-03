package com.github.hanseter.progress

import javafx.animation.Interpolator
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import kotlin.math.absoluteValue

class Player(
    private val x: Double,
    private var y: Double,
    private val size: Double,
    private val keyState: KeyState
) {

    private val floor = y
    private var speed = 0.0
    private var velocity = 0.0
    val minX: Double
        get() = x
    val minY: Double
        get() = y
    val maxX: Double
        get() = x + size
    val maxY: Double
        get() = y + size

    fun advance(secondFraction: Double) {
        if (KeyCode.UP in keyState.heldKeys) {
            if (velocity == 0.0) {
                velocity = 1.0
            }
        }
        jump(secondFraction)
    }

    private fun jump(secondFraction: Double) {
        velocity -= secondFraction / if (KeyCode.UP in keyState.heldKeys) 4 else 2
        if (velocity <= 0) {
            y = floor
            speed = 0.0
            velocity = 0.0
            return
        }
        y = floor * (0.5 - Interpolator.EASE_BOTH.interpolate(0.0, 1.0, velocity)).absoluteValue * 2


    }

    fun render(context: GraphicsContext) {
        context.fill = Color.BLACK
        context.fillRoundRect(x, y, size, size, 20.0, 20.0)
    }

    fun collidesWith(it: Obstacle): Boolean {
        return !(maxX < it.minX || it.maxX < minX || maxY < it.minY || it.maxY < minY)
    }
}