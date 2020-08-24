package ir.artapps.gamebrowser.ui.home.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.FavoriteRecyclerViewAdapter

abstract class FavoriteBaseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(game: Game, position: Int)
    abstract fun setClickListener(listener: FavoriteRecyclerViewAdapter.OnItemClickListener)
}