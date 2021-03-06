import com.soywiz.korge.gradle.*

buildscript {
	repositories {
		mavenLocal()
		maven { url = uri("https://dl.bintray.com/korlibs/korlibs") }
		maven { url = uri("https://plugins.gradle.org/m2/") }
		mavenCentral()
	}
	dependencies {
		classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:1.5.0d")
	}
}

apply(plugin = "korge")

korge {
	id = "io.github.rezmike"
	version = "0.0.1"
	name = "SeaBattle"
	icon = file("src/commonMain/resources/icon.png")
	fullscreen = true
	orientation = Orientation.LANDSCAPE
	gameCategory = GameCategory.STRATEGY
	jvmMainClassName = "game.MainKt"
}
