package com.source.truecallerapp

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnack(view: View, id: Int) =
    Snackbar.make(view, id, Snackbar.LENGTH_LONG).show()