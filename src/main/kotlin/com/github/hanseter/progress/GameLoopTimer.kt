package com.github.hanseter.progress

import javafx.animation.AnimationTimer

class GameLoopTimer(private val map: Map) : AnimationTimer() {
    private var lastTime = 0L
    override fun handle(now: Long) {
        val delta = now - lastTime
        val secondFraction = delta / 1e9
        lastTime = now
        nextFrame(secondFraction)
    }

    private fun nextFrame(secondFraction: Double) {
        map.advance(secondFraction)
        map.render()
    }
}