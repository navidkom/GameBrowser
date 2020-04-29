package ir.artapps.gamebrowser.ui.main.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.main.MainRecyclerViewAdapter
import ir.artapps.gamebrowser.util.DistanceUtil

class VenueRecyclerViewViewHolder(val v: View) : BaseViewHolder(v),
    View.OnClickListener {
    var title: TextView
    var number: TextView
    var rating: RatingBar
    var imageView: ImageView
    var listener: MainRecyclerViewAdapter.OnItemClickListener? = null
    override fun onBind(model: Game, i: Int) {
        title.text = model.name
        rating.rating = model.rate?.rate!!
        imageView.setImageBitmap(null)
        Glide.with(v).load(model.preview).into(imageView)
    }

    override fun setClickListener(listener: MainRecyclerViewAdapter.OnItemClickListener) {
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