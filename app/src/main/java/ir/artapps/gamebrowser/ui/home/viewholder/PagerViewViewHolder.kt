package ir.artapps.gamebrowser.ui.home.viewholder

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.customview.SquareImageView
import ir.artapps.gamebrowser.ui.home.HomeRecyclerViewAdapter
import ir.artapps.gamebrowser.ui.home.HomeViewPagerAdapter


class PagerViewViewHolder(val v: View) : RecyclerView.ViewHolder(v),
    View.OnClickListener {
    var imageView: ImageView
    var frameView: CardView
    var listener: HomeViewPagerAdapter.OnItemClickListener? = null
    fun onBind(model: Game, i: Int) {
        imageView.setImageBitmap(null)

        model.color?.let {
            frameView.setCardBackgroundColor(ContextCompat.getColor(v.context, it))
        }
        Glide.with(v).load(model.metadata?.thumbnail).into(imageView)
    }


    fun setClickListener(listener: HomeViewPagerAdapter.OnItemClickListener) {
        this.listener = listener
    }

    override fun onClick(v: View) {
        listener?.onViewPagerItemClick(v, adapterPosition)
    }

    init {
        v.setOnClickListener(this)
        imageView =
            v.findViewById(R.id.recycler_item_image_view)
        frameView = v.findViewById(R.id.parent_card_view)
    }
}