package ir.artapps.gamebrowser.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.viewholder.FavoriteBaseViewHolder
import ir.artapps.gamebrowser.ui.home.viewholder.FavoriteLoadingViewHolder
import ir.artapps.gamebrowser.ui.home.viewholder.FavoriteRecyclerViewViewHolder

/**
 * Created by navid
 */
class FavoriteRecyclerViewAdapter : RecyclerView.Adapter<FavoriteBaseViewHolder>() {
    var loading = false
    set(value) {
        field = value
        if (value) {
            notifyItemInserted(items.size)
        } else {
            notifyItemRemoved(items.size)
        }
    }

    private val VIEW_TYPE_GAME = 0
    private val VIEW_TYPE_LOADING = 1
    var items: List<Game> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_GAME -> FavoriteRecyclerViewViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_view_view_holder,
                    parent,
                    false
                )
            )
            else -> FavoriteLoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.loading_view_holder,
                    parent,
                    false
                )
            )
        }
    }

    var itemClickListener: OnItemClickListener? = null

    fun setGames(games: List<Game>) {
        items = games
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(baseViewHolder: FavoriteBaseViewHolder, i: Int) {
        var game: Game? = null
        if (getItemViewType(i) == VIEW_TYPE_GAME) {
            game = items[i]
        }
        game?.let {  baseViewHolder.onBind(game , i) }
        itemClickListener?.let { baseViewHolder.setClickListener(it) }
    }

    override fun getItemCount(): Int {
        return if (loading) {
            items.size + 1
        } else items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size) {
            VIEW_TYPE_LOADING
        } else VIEW_TYPE_GAME
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}