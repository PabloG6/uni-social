package com.example.owotechnologies.tweeper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by OWO Technologies on 11/12/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_RECEIVE_MESSAGE = 32342;
    ArrayList<Message> messages = new ArrayList<>();
    public static final int ITEM_SEND_MESSAGE = 348383;
    public void addSentMessage(Message messageToSend) {
        messages.add(messageToSend);
        notifyDataSetChanged();
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {

        public MessageHolder(View itemView) {
            super(itemView);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageHolder messageHolder = null;
        if(viewType == ITEM_SEND_MESSAGE)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.message_send, parent, false);
            messageHolder = new MessageHolder(view);
        } else {

            View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.message_send, parent, false);
            messageHolder = new MessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).getSender())
        {
            return ITEM_SEND_MESSAGE;
        } else {
            return ITEM_RECEIVE_MESSAGE;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView time;
        public ViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_id);
            time = itemView.findViewById(R.id.time_stamp);

        }
    }
}
