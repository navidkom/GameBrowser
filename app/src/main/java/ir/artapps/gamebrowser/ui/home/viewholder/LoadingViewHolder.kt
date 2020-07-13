package ir.artapps.gamebrowser.ui.home.viewholder

import android.view.View
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.HomeRecyclerViewAdapter

class LoadingViewHolder(v: View?) : BaseViewHolder(v!!) {
    override fun onBind(game: Game, position: Int) {}
    override fun setClickListener(listener: HomeRecyclerViewAdapter.OnItemClickListener) {}
}