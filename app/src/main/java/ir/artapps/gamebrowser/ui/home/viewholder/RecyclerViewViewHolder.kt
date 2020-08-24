package ir.artapps.gamebrowser.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.FavoriteRecyclerViewAdapter
import ir.artapps.gamebrowser.ui.home.HomeRecyclerViewAdapter

class RecyclerViewViewHolder(val v: View) : BaseViewHolder(v),
    View.OnClickListener {
    var title: TextView
    var number: TextView
    var rating: RatingBar
    var imageView: ImageView
    var listener: HomeRecyclerViewAdapter.OnItemClickListener? = null
    override fun onBind(model: Game, i: Int) {
        title.text = model.name
        model.rate?.rate?.let {
            rating.rating = it
        }
        imageView.setImageBitmap(null)
        Glide.with(v).load(model.metadata?.thumbnail).into(imageView)

    }

    override fun setClickListener(listener: HomeRecyclerViewAdapter.OnItemClickListener) {
        this.listener = listener
    }

    override fun onClick(v: View) {
        listener?.onItemClick(v, adapterPosition)
    }

    init {
        v.setOnClickListener(this)
        title = v.findViewById(R.id.recycler_item_title_text_view)
        number = v.findViewById(R.id.recycler_item_number_text_view)
        imageView =
            v.findViewById(R.id.recycler_item_image_view)
        rating = v.findViewById(R.id.recycler_item_rating_bar)
    }
}