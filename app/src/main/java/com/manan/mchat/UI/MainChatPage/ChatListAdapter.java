package com.manan.mchat.UI.MainChatPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manan.mchat.Model.Chatlist;
import com.manan.mchat.R;
import com.manan.mchat.Utilities.GetRoundedImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.chatholder> {

   private List<Chatlist> chatlist;
   private Context mcontext;

    public ChatListAdapter(List<Chatlist> chatlist, Context mcontext) {
        this.chatlist = chatlist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ChatListAdapter.chatholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);

        return  new chatholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.chatholder holder, int position) {
   Chatlist cl=chatlist.get(position);
        holder.image_name.setText(cl.getCname());
        holder.msg.setText(cl.getCmessage());

        Picasso.with(mcontext).load(mcontext.getResources().getColor(R.color.LightBlue)).centerCrop().transform((Transformation) new GetRoundedImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return chatlist.size();
    }

    public class chatholder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView image_name;
        TextView msg;

        public chatholder(View itemView) {
            super(itemView);


            image=itemView.findViewById(R.id.image);
                        image_name=itemView.findViewById(R.id.image_name);
                        msg=itemView.findViewById(R.id.msg);
        }

    }
}
