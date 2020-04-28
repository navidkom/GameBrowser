package ir.artapps.gamebrowser.ui.main.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.main.MainRecyclerViewAdapter

abstract class BaseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(venue: Game, position: Int)
    abstract fun setClickListener(listener: MainRecyclerViewAdapter.OnItemClickListener)
}