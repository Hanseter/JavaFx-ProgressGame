package com.github.hanseter.progress

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class Obstacle(
    var speed: Double,
    x: Double,
    private val y: Double,
    private val width: Double,
    private val height: Double
) {

    var x = x
        private set
    val minX: Double
        get() = x
    val minY: Double
        get() = y
    val maxX: Double
        get() = x + width
    val maxY: Double
        get() = y + height

    fun advance(secondFraction: Double) {
        x -= speed * secondFraction
    }

    fun render(ctx: GraphicsContext) {
        ctx.fill = Color.BLACK
        ctx.fillRect(x, y, width, height)
    }

}