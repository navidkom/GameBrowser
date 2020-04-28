package ir.artapps.gamebrowser.ui.main.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.main.MainRecyclerViewAdapter
import ir.artapps.gamebrowser.util.DistanceUtil

class VenueRecyclerViewViewHolder(val v: View) : BaseViewHolder(v),
    View.OnClickListener {
    var name: TextView
    var distance: TextView
    var imageView: ImageView
    var listener: MainRecyclerViewAdapter.OnItemClickListener? = null
    override fun onBind(model: Game, i: Int) {
        name.text = model.name
//        distance.text = DistanceUtil.distanceToString( model.distance )
        imageView.setImageBitmap(null)
//        Glide.with(v).load(model.categories?.get(0)?.icon?.url).into(imageView)
    }

    override fun setClickListener(listener: MainRecyclerViewAdapter.OnItemClickListener) {
        this.listener = listener
    }

    override fun onClick(v: View) {
        listener?.onItemClick(v, adapterPosition)
    }

    init {
        v.setOnClickListener(this)
        name = v.findViewById(R.id.venue_recycler_item_name_text_view)
        distance = v.findViewById(R.id.venue_recycler_item_distance_text_view)
        imageView =
            v.findViewById(R.id.venue_recycler_item_category_image_view)
    }
}