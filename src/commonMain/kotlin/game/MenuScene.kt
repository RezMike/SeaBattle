package game

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import ui.Button
import ui.button
import ui.centerHorizontallyBy

class MenuScene : Scene() {

    override suspend fun Container.sceneInit() {
        text("Sea battle"/*"Морской бой"*/, 72.0, Colors.BLACK, RobotoFont72) {
            centerHorizontallyBy(root)
            y = 160.0
        }

        val compBtn = greyButton("New game with computer"/*"Новая игра с компьютером"*/) {
            padding = 20.0
            centerHorizontallyBy(root)
            y = 310.0
        }

        val networkBtn = greyButton("New game through network"/*"Новая игра по сети"*/) {
            padding = 20.0
            centerHorizontallyBy(root)
            y = 440.0
        }

        if (compBtn.width > networkBtn.width) {
            networkBtn.x = compBtn.x
            networkBtn.width = compBtn.width
        } else if (compBtn.width < networkBtn.width) {
            compBtn.x = networkBtn.x
            compBtn.width = networkBtn.width
        }

        compBtn.onClick {
            //TODO: change scene
            networkBtn.enabled = !networkBtn.enabled
        }
        networkBtn.onClick {
            //TODO: change scene
            compBtn.enabled = !compBtn.enabled
        }
    }

    private fun Container.greyButton(text: String, init: Button.() -> Unit) = button(text) {
        init.invoke(this)
        strokeThickness = 1.0
        roundingRadius = 2.0

        default {
            background = RGBA(225, 225, 225)
            strokeColor = RGBA(173, 173, 173)
        }
        onOver {
            background = RGBA(229, 241, 251)
            strokeColor = RGBA(0, 120, 215)
        }
        onDown {
            background = RGBA(204, 228, 247)
            strokeColor = RGBA(0, 120, 215)
        }
    }
}
