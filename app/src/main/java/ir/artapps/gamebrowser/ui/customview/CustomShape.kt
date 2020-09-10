package ir.artapps.gamebrowser.ui.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.ImageView
import ir.artapps.gamebrowser.R


/**
 *   Created by Navid Komijani
 *   on 06,September,2020
 */

class CustomShape: androidx.appcompat.widget.AppCompatImageView {

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

    var bitmap: Bitmap? =  null
    var bitmap1: Bitmap =  BitmapFactory.decodeResource(context.resources, R.drawable.dshape)
    private fun init(context: Context) {


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        // paint.setColor(Color.CYAN);
        paint.color = Color.CYAN;
        canvas?.drawBitmap(getClip(getBitmapResource()!!)!!, 0F, 0F, paint)
    }

    private fun getBitmapResource(): Bitmap? {
        return if (bitmap == null) {
            val drawable = drawable ?: return null
            if (width == 0 || height == 0) {
                null
            } else (drawable as BitmapDrawable).bitmap
        } else {
            bitmap
        }
    }


    private fun getClip(bitmap: Bitmap): Bitmap? {
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(
            0, 0, width,
            height
        )
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawBitmap(bitmap1, rect, rect, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}