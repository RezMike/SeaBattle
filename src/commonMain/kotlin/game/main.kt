package game

import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.BitmapFont
import com.soywiz.korim.font.readBitmapFont
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.SizeInt

lateinit var RobotoFont32: BitmapFont
lateinit var RobotoFont72: BitmapFont

suspend fun main() = Korge(Korge.Config(module = MainModule, fullscreen = true))

object MainModule : Module() {

    override val mainScene = MenuScene::class
    override val bgcolor = RGBA(238, 238, 238)
    override val size = SizeInt(1280, 720)
    override val icon = "icon.png"
    override val fullscreen = true

    override suspend fun AsyncInjector.configure() {
        RobotoFont32 = resourcesVfs["roboto_32.fnt"].readBitmapFont()
        RobotoFont72 = resourcesVfs["roboto_72.fnt"].readBitmapFont()
        mapPrototype { MenuScene() }
        mapPrototype { NewFieldScene() }
    }
}
