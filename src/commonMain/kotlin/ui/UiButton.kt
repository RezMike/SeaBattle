package ui

import com.soywiz.korge.html.Html
import com.soywiz.korge.input.mouse
import com.soywiz.korge.newui.UISkin
import com.soywiz.korge.newui.UIView
import com.soywiz.korge.newui.uiObservable
import com.soywiz.korge.render.RenderContext
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.text
import com.soywiz.korge.view.ninePatch
import com.soywiz.korge.view.setText
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Rectangle

inline fun Container.uiButton(
	width: Number,
	height: Number,
	label: String,
	skin: UISkin,
	block: UIButton.() -> Unit = {}
): UIButton = UIButton(width.toDouble(), height.toDouble(), label, skin).also { addChild(it) }.apply(block)

open class UIButton(
	width: Double,
	height: Double,
	label: String,
	skin: UISkin
) : UIView(width, height) {
	var forcePressed  by uiObservable(false) { updateState() }
	var skin: UISkin by uiObservable(skin) { updateState() }
	var label by uiObservable(label) { updateState() }
	var textColor by uiObservable(Colors.BLACK) { updateState() }
	var textSize by uiObservable(32) { updateState() }
	private val rect = ninePatch(skin.normal, width, height, 16.0 / 64.0, 16.0 / 64.0, (64.0 - 16.0) / 64.0, (64.0 - 16.0) / 64.0) {}
	private val text = text(label)
	private var bover by uiObservable(false) { updateState() }
	private var bpressing by uiObservable(false) { updateState() }

	//override var mouseEnabled = uiObservable(true) { updateState() }

	fun simulateHover() {
		bover = true
	}

	fun simulateOut() {
		bover = false
	}

	fun simulatePressing(value: Boolean) {
		bpressing = value
	}

	fun simulateDown() {
		bpressing = true
	}

	fun simulateUp() {
		bpressing = false
	}

	init {
		mouse {
			onOver {
				simulateHover()
			}
			onOut {
				simulateOut()
			}
			onDown {
				simulateDown()
			}
			onUpAnywhere {
				simulateUp()
			}
		}
		updateState()
	}

	private fun updateState() {
		when {
			bpressing || forcePressed -> {
				rect.tex = skin.down
			}
			bover -> {
				rect.tex = skin.hover
			}
			else -> {
				rect.tex = skin.normal
			}
		}
		text.format = Html.Format(
			face = skin.font,
			align = Html.Alignment.MIDDLE_CENTER,
			color = textColor,
			size = textSize
		)
		text.setTextBounds(Rectangle(0, 0, width, height))
		text.setText(label)
	}

	override fun updatedSize() {
		super.updatedSize()
		rect.width = width
		rect.height = height
		updateState()
	}

	override fun renderInternal(ctx: RenderContext) {
		super.renderInternal(ctx)
	}
}