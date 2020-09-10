package ir.artapps.gamebrowser.ui.profile

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.profile.AvatarAdapter.Companion.avatarSelectLiveData

/**
 *   Created by Navid Komijani
 *   on 05,September,2020
 */
class AvatarAdapter : RecyclerView.Adapter<AvatarViewHolder>() {

    companion object{
        val avatarSelectLiveData = MutableLiveData<Int>()
    }

    var selectedItem: Int? = 0
    var list = listOf<Drawable>()
    var mlistener: AvatarAdapter.OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        return AvatarViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.avatar_view_holder,
                parent,
                false
            ), selectedItem
        ).apply {
            this.listener = mlistener
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}

class AvatarViewHolder( val v: View, val selectedItem: Int?) : RecyclerView.ViewHolder(v), View.OnClickListener {

    var listener: AvatarAdapter.OnItemClickListener? = null
    var imageView : ImageView? = null
    var tik : ImageView? = null
    fun onBind(drawable: Drawable) {
        imageView?.setImageDrawable(drawable)

        if( selectedItem == adapterPosition + 1) {
            tik?.visibility = View.VISIBLE
        } else {
            tik?.visibility = View.GONE
        }
    }


    init {
        v.setOnClickListener(this)
        imageView = v.findViewById(R.id.imageView)
        tik = v.findViewById(R.id.tik)

        avatarSelectLiveData.observeForever {
            if(it == adapterPosition) {
                tik?.visibility = View.VISIBLE
            } else {
                tik?.visibility = View.GONE
            }
        }
    }

    override fun onClick(v: View?) {
        listener?.onItemClick(v, adapterPosition)
        avatarSelectLiveData.value = adapterPosition
    }
}
