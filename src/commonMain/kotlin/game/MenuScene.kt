package game

import com.soywiz.korge.html.Html
import com.soywiz.korge.input.onClick
import com.soywiz.korge.newui.UISkin
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.text
import com.soywiz.korim.bitmap.slice
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import ui.centerHorizontallyBy
import ui.uiButton

class MenuScene : Scene() {

    override suspend fun Container.sceneInit() {
        text("Sea battle"/*"Морской бой"*/, 72.0, Colors.BLACK, RobotoFont72) {
            centerHorizontallyBy(root)
            y = 160.0
        }

        val skin = UISkin(
            resourcesVfs["button_default.png"].readBitmap().slice(),
            resourcesVfs["button_hover.png"].readBitmap().slice(),
            resourcesVfs["button_down.png"].readBitmap().slice(),
            font = Html.FontFace.Bitmap(RobotoFont32)
        )

        val compBtn = uiButton(380, 70, "New game with computer"/*"Новая игра с компьютером"*/, skin) {
            centerHorizontallyBy(root)
            y = 310.0
        }

        val networkBtn = uiButton(380, 70, "New game through network"/*"Новая игра по сети"*/, skin) {
            centerHorizontallyBy(root)
            y = 440.0
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
}
