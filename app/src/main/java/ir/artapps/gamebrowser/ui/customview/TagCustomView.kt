package ir.artapps.gamebrowser.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import ir.artapps.gamebrowser.R

/**
 * Created by navid
 */
class TagCustomView : CardView {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.tag_custom_view, this)

    }
}