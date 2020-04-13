package io.volkov.edgeless

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun AlertDialog.overrideSystemInsets(
    listener: MutableLiveData<VerticalInset>
) {
    window?.decorView?.overrideSystemInsets(listener)
    window?.navigationBarColor = Color.TRANSPARENT
    window?.statusBarColor = Color.TRANSPARENT
}

fun Activity.overrideSystemInsets(
    listener: MutableLiveData<VerticalInset>
) {
    window.decorView.overrideSystemInsets(listener)
    window.navigationBarColor = Color.TRANSPARENT
    window.statusBarColor = Color.TRANSPARENT
}

private fun View.overrideSystemInsets(listener: MutableLiveData<VerticalInset>) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->

        val desiredBottomInset = calculateDesiredBottomInset(
            this,
            insets.systemWindowInsetTop,
            insets.systemWindowInsetBottom,
            listener
        )

        ViewCompat.onApplyWindowInsets(
            this,
            insets.replaceSystemWindowInsets(0, 0, 0, desiredBottomInset)
        )
    }
}

private fun calculateDesiredBottomInset(
    view: View,
    topInset: Int,
    bottomInset: Int,
    listener: MutableLiveData<VerticalInset>
): Int {
    val hasKeyboard = view.isKeyboardAppeared(bottomInset)
    val desiredBottomInset = if (hasKeyboard) bottomInset else 0
    listener.postValue(VerticalInset(topInset, if (hasKeyboard) 0 else bottomInset))
    return desiredBottomInset
}

private fun View.isKeyboardAppeared(bottomInset: Int) =
    bottomInset / resources.displayMetrics.heightPixels.toDouble() > .25


fun <T> LifecycleOwner.observeLiveDataSafe(
    liveData: LiveData<T>,
    observeAction: (T) -> Unit
) {
    liveData.observe(this, Observer {
        it?.let {
            observeAction(it)
        }
    })
}

fun View.setVerticalMargin(marginTop: Int = 0, marginBottom: Int = 0) {
    val _layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    _layoutParams.setMargins(0, marginTop, 0, marginBottom)
    this.layoutParams = _layoutParams
}

fun View.addVerticalMargin(marginTop: Int = 0, marginBottom: Int = 0) {
    val _layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    _layoutParams.setMargins(
        0,
        marginTop + _layoutParams.topMargin,
        0,
        marginBottom + _layoutParams.bottomMargin
    )
    this.layoutParams = _layoutParams
}