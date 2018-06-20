package com.manan.mchat.UI.ContactListPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manan.mchat.DatabaseController.Contact;
import com.manan.mchat.R;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactHolder> {
    private Context mContext;
    private List<Contact> mContacts;

    public ContactListAdapter(Context context, List<Contact> contacts) {
        mContext = context;
        mContacts = contacts;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);

        return new ContactHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        holder.bind(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    protected class ContactHolder extends RecyclerView.ViewHolder{
        private TextView contactName,contactNumber;
        public ContactHolder(View v) {
            super(v);
            contactName=v.findViewById(R.id.contact_name);
            contactNumber=v.findViewById(R.id.contact_number);
        }
        void bind(Contact contact){
            contactName.setText(contact.getName());
            contactNumber.setText(contact.getNumber());
        }
    }
    public void setContacts(List<Contact> contacts){
        mContacts=contacts;
        notifyDataSetChanged();
    }
}
