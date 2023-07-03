package com.github.hanseter.progress

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

class KeyState {

    private val keysDown = HashSet<KeyCode>()
    val heldKeys: Set<KeyCode>
        get() = keysDown

    private val keyDown = EventHandler<KeyEvent> {
        keysDown+=it.code
    }
    private val keyUp = EventHandler<KeyEvent> {
        keysDown-=it.code
    }

    fun register(scene: Scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyDown)
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyUp)
    }

    fun unregister(scene: Scene) {
        scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyDown)
        scene.removeEventHandler(KeyEvent.KEY_RELEASED, keyUp)
    }
}