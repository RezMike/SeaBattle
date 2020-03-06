package ui

import com.soywiz.korge.view.View

fun View.centerHorizontallyBy(view: View) {
    x = view.width / 2 - width / 2
}

fun View.centerVerticallyBy(view: View) {
    y = view.height / 2 - height / 2
}
