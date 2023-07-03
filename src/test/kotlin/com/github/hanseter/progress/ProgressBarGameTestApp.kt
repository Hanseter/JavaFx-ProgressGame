package com.github.hanseter.progress

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class ProgressBarGameTestApp : Application() {
    override fun start(stage: Stage) {
        val game = ProgressBarGame(600.0, 40.0)
        stage.scene = Scene(StackPane(game.canvas))
        stage.show()
    }
}

fun main() {
    Application.launch(ProgressBarGameTestApp::class.java)
}