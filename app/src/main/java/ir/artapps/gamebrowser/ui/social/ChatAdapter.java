package ir.artapps.gamebrowser.ui.social;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.artapps.gamebrowser.R;
import ir.artapps.gamebrowser.entities.chat.Message;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<Message> items;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(List<Message> items) {
        this.items = items;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        if (viewType == 0) {
            return new ChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_other, parent, false));
        } else {
            return new ChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_mine, parent, false));
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Message model = items.get( position );
        holder.detail.setText(model.getMessage());
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).getMine()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView detail;
        public TextView time;

        public ChatViewHolder(View v) {
            super(v);
            detail = v.findViewById(R.id.conversation_detail);
            time = v.findViewById(R.id.conversation_timestamp);
        }
    }
}
