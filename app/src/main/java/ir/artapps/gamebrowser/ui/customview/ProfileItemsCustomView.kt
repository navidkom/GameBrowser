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
class ProfileItemsCustomView : LinearLayout {
    private var nameTextView: TextView? = null
    private var valueTextView: TextView? = null
    private var imageView: ImageView? = null

    var name: String? = null
        set(value) {
            field = value
            nameTextView?.text = value
        }

    var value: String? = null
        set(value) {
            field = value
            valueTextView?.text = value
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
        name: String?,
        value: String?,
        drawable: Drawable?
    ) : super(context) {
        init(context)
        this.name = name
        this.value = value
        this.image = drawable
    }

    private fun init(context: Context) {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.profile_item_custom_view, this)
        nameTextView = findViewById(R.id.name)
        valueTextView = findViewById(R.id.value)
//        imageView = findViewById(R.id.venue_detail_custom_view_image_view)
    }
}