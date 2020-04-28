package ir.artapps.gamebrowser.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.main.viewholder.BaseViewHolder
import ir.artapps.gamebrowser.ui.main.viewholder.LoadingViewHolder
import ir.artapps.gamebrowser.ui.main.viewholder.VenueRecyclerViewViewHolder

/**
 * Created by navid
 */
class MainRecyclerViewAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    var loading = false
    set(value) {
        field = value
        if (value) {
            notifyItemInserted(items.size)
        } else {
            notifyItemRemoved(items.size)
        }
    }

    private val VIEW_TYPE_VENUE = 0
    private val VIEW_TYPE_LOADING = 1
    var items: List<Game> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_VENUE -> VenueRecyclerViewViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.venues_recycler_view_view_holder,
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

    fun setVenues(venues: List<Game>) {
        items = venues
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, i: Int) {
        var venue: Game? = null
        if (getItemViewType(i) == VIEW_TYPE_VENUE) {
            venue = items[i]
        }
        venue?.let {  baseViewHolder.onBind(venue , i) }
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
        } else VIEW_TYPE_VENUE
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}