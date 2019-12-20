package com.source.truecallerapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Display {

    fun showSnack(view: View, id: Int){
        Snackbar.make(view, id, Snackbar.LENGTH_LONG).show()
    }
}