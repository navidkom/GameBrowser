package ir.artapps.gamebrowser.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.home.viewholder.BaseViewHolder
import ir.artapps.gamebrowser.ui.home.viewholder.LoadingViewHolder
import ir.artapps.gamebrowser.ui.home.viewholder.RecyclerViewViewHolder

/**
 * Created by navid
 */
class HomeRecyclerViewAdapter : RecyclerView.Adapter<BaseViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_GAME -> RecyclerViewViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_view_view_holder,
                    parent,
                    false
                )
            )
            else -> LoadingViewHolder(
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

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, i: Int) {
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