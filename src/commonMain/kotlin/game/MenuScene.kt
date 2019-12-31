package game

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import ui.button
import ui.centerHorizontallyBy

class MenuScene : Scene() {

    override suspend fun Container.sceneInit() {
        text("Sea battle"/*"Морской бой"*/, 72.0, Colors.BLACK, RobotoFont72) {
            centerHorizontallyBy(root)
            y = 160.0
        }

        val compBtn = button("New game with computer"/*"Новая игра с компьютером"*/) {
            padding = 20
            centerHorizontallyBy(root)
            y = 310.0
        }

        button("New game through network"/*"Новая игра по сети"*/) {
            padding = 20
            outerWidth = compBtn.width
            centerHorizontallyBy(root)
            y = 440.0
        }
    }
}
