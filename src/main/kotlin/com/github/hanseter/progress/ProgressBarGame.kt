package com.github.hanseter.progress

import javafx.scene.Scene
import javafx.scene.canvas.Canvas

class ProgressBarGame(width: Double, height: Double) {

    private val keyState = KeyState()

    val canvas = Canvas(width, height).apply {
        sceneProperty().addListener { _, oldScene, scene ->
            oldScene?.also { keyState.unregister(it) }
            scene?.also { keyState.register(it) }
        }
    }

    private val map = Map(width, height, canvas.graphicsContext2D, keyState)

    private val timer = GameLoopTimer(map).apply {
        start()
    }

}