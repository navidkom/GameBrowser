package ir.artapps.gamebrowser.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import ir.artapps.gamebrowser.R
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class CustomToolbar : Toolbar {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true)
//        init()
    }

    var name = ""
    set(value){
        field = value
        toolbarProfileName.setText(value)
    }
//    private fun init() {
//        toolbarProfileName
//    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
}