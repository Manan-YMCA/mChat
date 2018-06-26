package com.manan.mchat.UI.MainChatPage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manan.mchat.DatabaseController.Contact;
import com.manan.mchat.Model.Chatlist;
import com.manan.mchat.R;

import java.util.ArrayList;
import java.util.List;

public class ChatListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ChatListAdapter mChatListAdapter;
    private View fragmentView;
    private LinearLayoutManager cLinearLayoutManager;
    private List<Chatlist> chatlist ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_chat, container, false);
        mRecyclerView=fragmentView.findViewById(R.id.ChatList_recyclerview);
        cLinearLayoutManager = new LinearLayoutManager(getActivity());
        cLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(cLinearLayoutManager);
        chatlist=new ArrayList<>();

        for(int i=0;i<=10;i++)
        {
            Chatlist cc=new Chatlist(
                    "Rishabh",
                    "App puri hone ke bad party krenge"

            );
            chatlist.add(cc);
            Log.d("added","add ho gya");

        }

        mRecyclerView.setAdapter(new ChatListAdapter(chatlist,getActivity()));

        return fragmentView;

    }
}
