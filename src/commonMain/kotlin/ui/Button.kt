package ui

import com.soywiz.korge.html.Html
import com.soywiz.korge.input.*
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.Text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d.Color
import com.soywiz.korim.vector.Context2d.StrokeInfo
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.roundRect
import game.RobotoFont32
import kotlin.math.abs

fun Container.button(text: String = "", init: Button.() -> Unit = {}): Button {
    return Button(text).apply(init).addTo(this)
}

class Button(initText: String) {
    var x: Double
        get() = container.x
        set(value) {
            container.x = value
        }
    var y: Double
        get() = container.y
        set(value) {
            container.y = value
        }

    var width: Double
        get() = textField.textBounds.width + paddingLeft + paddingRight
        set(value) {
            val padding = abs(value - textField.textBounds.width) / 2
            paddingLeft = padding
            paddingRight = padding
            rebuild()
        }
    var height: Double
        get() = textField.textBounds.height + paddingTop + paddingBottom
        set(value) {
            val padding = abs(value - textField.textBounds.height) / 2
            paddingTop = padding
            paddingBottom = padding
            rebuild()
        }

    var visible: Boolean
        get() = container.visible
        set(value) {
            container.visible = value
        }
    var enabled = true
        set(value) {
            field = value
            if (value) defaultState?.invoke(this)
            else onDisableState?.invoke(this)
        }

    var text = initText
    var textSize = 32.0
    var textColor = Colors.BLACK
    var textFont = RobotoFont32
    var background = RGBA(225, 225, 225)
    var strokeColor = RGBA(173, 173, 173)
    var strokeThickness = 1.0
    var roundingRadius = 0.0

    private val textField = Text(text, textSize, textColor, textFont).apply { filtering = false }
    private val g = Graphics()

    var paddingTop = 8.0
    var paddingBottom = 8.0
    var paddingLeft = 10.0
    var paddingRight = 10.0

    var padding = 8.0
        set(value) {
            field = value
            paddingTop = value
            paddingBottom = value
            paddingLeft = value
            paddingRight = value
        }

    private val container = object : Container() {
        override fun getLocalBoundsInternal(out: Rectangle) {
            out.width = this@Button.width
            out.height = this@Button.height
        }
    }

    private var onClick: ((MouseEvents) -> Unit)? = null
    fun onClick(listener: (MouseEvents) -> Unit) = apply { onClick = listener }

    private var onDisableState: (Button.() -> Unit)? = null
    fun onDisable(listener: Button.() -> Unit) = apply { onDisableState = listener }

    private var onOverState: (Button.() -> Unit)? = null
    fun onOver(listener: Button.() -> Unit) = apply { onOverState = listener }

    private var onDownState: (Button.() -> Unit)? = null
    fun onDown(listener: Button.() -> Unit) = apply { onDownState = listener }

    private var defaultState: (Button.() -> Unit)? = null
    fun default(listener: Button.() -> Unit) = apply { defaultState = listener }

    private var isOver = false

    init {
        container += g
        container += textField
        container.onOver {
            if (enabled && onOverState != null) {
                isOver = true
                onOverState!!.invoke(this@Button)
                rebuild()
            }
        }
        container.onOut {
            isOver = false
            if (enabled && defaultState != null) {
                defaultState!!.invoke(this@Button)
                rebuild()
            }
        }
        container.onDown {
            if (enabled && onDownState != null) {
                onDownState!!.invoke(this@Button)
                rebuild()
            }
        }
        container.onUp {
            if (enabled) {
                if (isOver && onOverState != null) {
                    onOverState!!.invoke(this@Button)
                    rebuild()
                }
                if (!isOver && defaultState != null) {
                    defaultState!!.invoke(this@Button)
                    rebuild()
                }
            }
        }
        container.onClick { e ->
            if (enabled) onClick?.invoke(e)
        }
    }

    private fun rebuild(): Button {
        textField.text = text
        textField.format = Html.Format(
            color = textColor,
            face = Html.FontFace.Bitmap(textFont),
            size = textSize.toInt()
        )
        textField.x = paddingLeft
        textField.y = paddingTop
        g.apply {
            clear()
            fillStroke(Color(background), Color(strokeColor), StrokeInfo(thickness = strokeThickness)) {
                roundRect(0, 0, this@Button.width, this@Button.height, roundingRadius)
            }
        }
        return this
    }

    fun addTo(parent: Container) = apply {
        parent += container
        defaultState?.invoke(this)
        if (!enabled) onDisableState?.invoke(this)
        rebuild()
    }
}
