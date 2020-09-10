package ir.artapps.gamebrowser.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.viewholder.PagerViewViewHolder

/**
 * Created by navid
 */
class HomeViewPagerAdapter : RecyclerView.Adapter<PagerViewViewHolder>() {
    var loading = false
        set(value) {
            field = value
            if (value) {
                notifyItemInserted(items.size)
            } else {
                notifyItemRemoved(items.size)
            }
        }

    var items: List<Game> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pager_view_view_holder,
            parent,
            false
        )
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return PagerViewViewHolder(view)
    }

    var itemClickListener: OnItemClickListener? = null

    fun setGames(games: List<Game>) {
        items = games
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (loading) {
            items.size + 1
        } else items.size
    }


    interface OnItemClickListener {
        fun onViewPagerItemClick(view: View?, position: Int)
    }

    override fun onBindViewHolder(holder: PagerViewViewHolder, i: Int) {
        var game: Game? = items[i]
        game?.let { holder.onBind(game, i) }
        itemClickListener?.let { holder.setClickListener(it) }
    }
}