package ir.artapps.gamebrowser.ui.home.viewholder

import android.view.View
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.FavoriteRecyclerViewAdapter

class FavoriteLoadingViewHolder(v: View?) : FavoriteBaseViewHolder(v!!) {
    override fun onBind(game: Game, position: Int) {}
    override fun setClickListener(listener: FavoriteRecyclerViewAdapter.OnItemClickListener) {}
}