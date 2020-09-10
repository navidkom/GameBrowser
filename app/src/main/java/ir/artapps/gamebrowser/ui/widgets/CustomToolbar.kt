package ir.artapps.gamebrowser.ui.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import ir.artapps.gamebrowser.R

class CustomToolbar : Toolbar {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true)
//        init()
    }

    var signIn: Boolean = false
//        set(value) {
//            field = value
//            if (value) {
//                profileParent.visibility = View.VISIBLE
//                signinBtn.visibility = View.GONE
//            } else {
//                profileParent.visibility = View.GONE
//                signinBtn.visibility = View.VISIBLE
//            }
//        }

    var name = ""
//        set(value) {
//            field = value
//            toolbarProfileName.setText(value)
//        }

    var image: Drawable? = null
//        set(value) {
//            field = value
//            toolbarProfileImage.setImageDrawable(  value )
//        }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
}