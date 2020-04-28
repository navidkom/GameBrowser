package ir.artapps.gamebrowser.ui.main.viewholder

import android.view.View
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.main.MainRecyclerViewAdapter

class LoadingViewHolder(v: View?) : BaseViewHolder(v!!) {
    override fun onBind(venue: Game, position: Int) {}
    override fun setClickListener(listener: MainRecyclerViewAdapter.OnItemClickListener) {}
}