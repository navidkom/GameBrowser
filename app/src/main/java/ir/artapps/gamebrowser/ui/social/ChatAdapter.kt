package ir.artapps.gamebrowser.ui.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.chat.Message
import ir.artapps.gamebrowser.ui.social.ChatAdapter.ChatViewHolder

class ChatAdapter // Provide a suitable constructor (depends on the kind of dataset)
    (private val items: List<Message?>) :
    RecyclerView.Adapter<ChatViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        // create a new view
        return if (viewType == 0) {
            ChatViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_chat_other, parent, false)
            )
        } else {
            ChatViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_chat_mine, parent, false)
            )
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val model = items[position]
        holder.detail.text = model?.message?.message
        holder.name.text = model?.message?.participant?.name
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position]?.isMine!!) 1 else 0
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ChatViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var detail: TextView
        var time: TextView
        var name: TextView

        init {
            detail = v.findViewById(R.id.conversation_detail)
            name = v.findViewById(R.id.conversation_name)
            time = v.findViewById(R.id.conversation_timestamp)
        }
    }
}
