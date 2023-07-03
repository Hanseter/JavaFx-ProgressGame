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
    val minX: Double
        get() = x
    val minY: Double
        get() = y
    val maxX: Double
        get() = x + size
    val maxY: Double
        get() = y + size

    init {
        y = y / 2
    }

    fun advance(secondFraction: Double) {
        fly(secondFraction)
    }

    private fun fly(secondFraction: Double) {
        speed += if (KeyCode.UP in keyState.heldKeys) -secondFraction / 5
        else secondFraction / 5
        speed = speed.coerceIn(-0.15, 0.15)
        y += speed
    }

    fun render(context: GraphicsContext) {
        context.fill = Color.BLACK
        context.fillRoundRect(x, y, size, size, 20.0, 20.0)
    }

    fun collidesWith(it: Obstacle): Boolean {
        return !(maxX < it.minX || it.maxX < minX || maxY < it.minY || it.maxY < minY)
    }

    fun collidesWithBorder() = y < 0.0 || y > floor

    fun reset() {
        y = floor / 2
        speed = 0.0
    }
}