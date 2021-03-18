package com.douglastaquary.busaoshare.androidApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.setVisible(visible: Boolean) = this.apply { visibility = if (visible) View.VISIBLE else View.GONE }