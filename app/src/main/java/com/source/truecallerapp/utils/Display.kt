package com.source.truecallerapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make

object Display {

    fun showSnack(view: View, id: Int) = make(view, id, LENGTH_LONG).show()
    }
