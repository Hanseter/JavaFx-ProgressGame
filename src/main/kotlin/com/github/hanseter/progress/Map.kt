package com.github.hanseter.progress

import javafx.scene.canvas.GraphicsContext
import java.util.LinkedList
import kotlin.random.Random

class Map(
    private val width: Double,
    private val height: Double,
    private val context: GraphicsContext,
    keyState: KeyState
) {

    private val player = Player(
        context.canvas.width / 10,
        context.canvas.height - height / 3,
        context.canvas.height / 3,
        keyState
    )
    private var currentSpeed = width / 6
    private var rng = -2.0
    private var obstacles: MutableList<Obstacle> = LinkedList<Obstacle>()

    fun advance(secondFraction: Double) {
        obstacles.forEach { it.advance(secondFraction) }
        player.advance(secondFraction)
        if (Random.nextDouble(5.0) < rng) {
            rng = -2.0
            obstacles.add(createObstacle())
        }
        rng += secondFraction
        if (obstacles.any { player.collidesWith(it) }) {
            reset()
        }
        while (obstacles.isNotEmpty() && obstacles.first().x < 0) {
            obstacles.removeFirst()
        }
    }

    private fun createObstacle(): Obstacle {
        val y = when (Random.nextInt(2)) {
            0 -> height *0.6
            else -> 0.0
        }
        return Obstacle(currentSpeed, width, y, width/100, height *0.4)
    }


    private fun reset() {
        obstacles.clear()
        rng = 5.0
    }

    fun render() {
        context.clearRect(0.0, 0.0, width, height)
        player.render(context)
        obstacles.forEach { it.render(context) }
    }
}