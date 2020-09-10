package ir.artapps.gamebrowser.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.FavoriteRecyclerViewAdapter

class FavoriteRecyclerViewViewHolder(val v: View) : FavoriteBaseViewHolder(v),
    View.OnClickListener {
    var imageView: ImageView
    var listener: FavoriteRecyclerViewAdapter.OnItemClickListener? = null
    override fun onBind(model: Game, i: Int) {

        imageView.setImageBitmap(null)
        Glide.with(v).load(model.metadata?.thumbnail).into(imageView)
    }

    override fun setClickListener(listener: FavoriteRecyclerViewAdapter.OnItemClickListener) {
        this.listener = listener
    }

    override fun onClick(v: View) {
        listener?.onItemClick(v, adapterPosition)
    }

    init {
        v.setOnClickListener(this)
        imageView =
            v.findViewById(R.id.recycler_item_image_view)
    }
}