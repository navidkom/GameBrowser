package ir.artapps.gamebrowser.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import ir.artapps.gamebrowser.R

/**
 * Created by navid
 */
class VenueDetailCustomView : LinearLayout {
    private var textView: TextView? = null
    private var imageView: ImageView? = null

    var text: String? = null
    set(value) {
        field = value
        textView?.text = value
    }

    var image: Drawable? = null
    set(value) {
        field = value
        imageView?.setImageDrawable(value)
    }

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

    constructor(
        context: Context,
        text: String?,
        drawable: Drawable?
    ) : super(context) {
        init(context)
        this.text = text
        this.image = drawable
    }

    private fun init(context: Context) {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.venue_detail_custom_view, this)
        textView = findViewById(R.id.venue_detail_custom_view_text_view)
        imageView = findViewById(R.id.venue_detail_custom_view_image_view)
    }
}