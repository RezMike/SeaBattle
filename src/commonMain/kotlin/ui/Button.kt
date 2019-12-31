package ui

import com.soywiz.korge.input.mouse
import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.Text
import com.soywiz.korge.view.addTo
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d.Color
import com.soywiz.korim.vector.Context2d.StrokeInfo
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.roundRect
import game.RobotoFont32

fun Container.button(text: String = "", init: Button.() -> Unit = {}): Button {
    return Button(text).addTo(this).apply(init).build()
}

class Button(text: String) : Container() {
    var textSize = 32.0
    var textColor = Colors.RED
    var textFont = RobotoFont32
    var background = RGBA(225, 225, 225)
    var strokeColor = RGBA(173, 173, 173)

    private val textField = Text(text, textSize, textColor, textFont).apply { filtering = false }
    private val g = Graphics()

    var paddingTop = 8
    var paddingBottom = 8
    var paddingLeft = 10
    var paddingRight = 10

    var padding = 8
        set(value) {
            field = value
            paddingTop = value
            paddingBottom = value
            paddingLeft = value
            paddingRight = value
        }

    var outerWidth: Double? = null
    var outerHeight: Double? = null

    private val compWidth: Double
        get() = textField.textBounds.width + paddingLeft + paddingRight
    private val compHeight: Double
        get() = textField.textBounds.height + paddingTop + paddingBottom

    override fun getLocalBoundsInternal(out: Rectangle) {
        out.apply { width = compWidth; height = compHeight }
    }

    var enabledButton = true
        set(value) {
            field = value
            updateState()
        }

    private var overButton = false
        set(value) {
            field = value
            updateState()
        }

    fun updateState() {
        when {
            !enabledButton -> alpha = 1.0
            overButton -> alpha = 1.0
            else -> alpha = 1.0
        }
    }

    init {
        getLocalBounds()
    }

    fun build(): Button {
        x += paddingLeft
        y += paddingRight
        outerWidth?.let {
            paddingLeft = (it / 2 - textField.textBounds.width / 2).toInt()
        }
        outerHeight?.let {
            paddingTop = (it / 2 - textField.textBounds.height / 2).toInt()
        }
        g.apply {
            fillStroke(Color(background), Color(strokeColor), StrokeInfo(thickness = 2.0)) {
                roundRect(
                    textField.textBounds.x - paddingLeft,
                    textField.textBounds.y - paddingTop,
                    if (outerWidth == null) compWidth else outerWidth!!,
                    if (outerHeight == null) compHeight else outerHeight!!,
                    2.0,
                    2.0
                )
            }
        }
        //this += solidRect(bounds.width, bounds.height, Colors.TRANSPARENT_BLACK)
        this += g.apply {
            mouseEnabled = true
        }
        this += textField

        mouse {
            over { overButton = true }
            out { overButton = false }
        }
        onClick {
            stage?.views?.launchImmediately {
                //if (enabledButton) handler()
            }
        }
        updateState()
        return this
    }
}
