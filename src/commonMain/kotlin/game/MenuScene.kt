package game

import com.soywiz.korge.html.*
import com.soywiz.korge.input.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import ui.*

class MenuScene(private val initMode: (GameMode) -> Unit) : Scene() {

    override suspend fun Container.sceneInit() {
        text("Sea battle"/*"Морской бой"*/, 72.0, Colors.BLACK, RobotoFont72) {
            centerHorizontallyBy(root)
            y = 160.0
        }

        defaultUIFont = Html.FontFace.Bitmap(RobotoFont32)

        defaultUISkin = UISkin(
            normal = resourcesVfs["button_default.png"].readBitmap().slice(),
            over = resourcesVfs["button_hover.png"].readBitmap().slice(),
            down = resourcesVfs["button_down.png"].readBitmap().slice()
        )

        val compBtn = textButton(380, 70, "New game with computer"/*"Новая игра с компьютером"*/) {
            centerHorizontallyBy(root)
            y = 310.0
        }

        val networkBtn = textButton(380, 70, "New game through network"/*"Новая игра по сети"*/) {
            centerHorizontallyBy(root)
            y = 440.0
        }

        compBtn.onClick {
            initMode(GameMode.COMPUTER)
            sceneContainer.changeTo<NewFieldScene>()
        }
        networkBtn.onClick {
            initMode(GameMode.NETWORK)
            sceneContainer.changeTo<NewFieldScene>()
        }
    }
}
