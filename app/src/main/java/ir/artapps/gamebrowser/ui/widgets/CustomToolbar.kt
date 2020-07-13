package ir.artapps.gamebrowser.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import ir.artapps.gamebrowser.R

class CustomToolbar: Toolbar {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true)
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
}