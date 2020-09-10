package ir.artapps.gamebrowser.ui.home.viewholder

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.HomeRecyclerViewAdapter


class RecyclerViewViewHolder(val v: View) : BaseViewHolder(v),
    View.OnClickListener {
    var imageView: ImageView
    var frameView: ImageView
    var listener: HomeRecyclerViewAdapter.OnItemClickListener? = null
    override fun onBind(model: Game, i: Int) {
        imageView.setImageBitmap(null)

        model.color?.let {
            frameView.setColorFilter(
                ContextCompat.getColor(v.context, it),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

        Glide.with(v).load(model.metadata?.thumbnail)
//            .listener(object : RequestListener<Drawable?> {
//            override fun onLoadFailed(
//                e: GlideException?,
//                model: Any?,
//                target: Target<Drawable?>?,
//                isFirstResource: Boolean
//            ): Boolean {
//                frameView.setColorFilter(
//                    ContextCompat.getColor(v.context, R.color.blue_gray_300),
//                    android.graphics.PorterDuff.Mode.SRC_IN
//                )
//                return false
//            }
//
//            override fun onResourceReady(
//                resource: Drawable?,
//                model: Any?,
//                target: Target<Drawable?>?,
//                dataSource: DataSource?,
//                isFirstResource: Boolean
//            ): Boolean {
//                resource.let {
//                    Palette.generateAsync(drawableToBitmap(resource!!), PaletteAsyncListener {
//                        // Do something with colors...
//                        frameView.setColorFilter(
//                            it?.getDominantColor( ContextCompat.getColor(v.context,  R.color.orange))!!,
//                            android.graphics.PorterDuff.Mode.SRC_IN
//                        )
//                    })
//                }
//                return false
//            }
//
//        })
            .into(imageView)

    }


    override fun setClickListener(listener: HomeRecyclerViewAdapter.OnItemClickListener) {
        this.listener = listener
    }

    override fun onClick(v: View) {
        listener?.onRecyclerItemClick(v, adapterPosition)
    }

    init {
        v.setOnClickListener(this)
        imageView =
            v.findViewById(R.id.recycler_item_image_view)
        frameView = v.findViewById(R.id.recycler_item_frame_view)
    }

    fun getDominantColor(bitmap: Bitmap?): Int? {
//        val newBitmap = Bitmap.createScaledBitmap(bitmap!!, 1, 1, true)
        val color = bitmap?.getPixel(1, 1)
        return color
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable) {
            val bitmapDrawable = drawable
            if (bitmapDrawable.bitmap != null) {
                return bitmapDrawable.bitmap
            }
        }
        bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, bitmap.width, bitmap.height)
        drawable.draw(canvas)
        return bitmap
    }
}