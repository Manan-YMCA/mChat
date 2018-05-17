package com.manan.mchat.activities;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import Model.Contact;
import Adapter.ContactListAdapter;
import com.manan.mchat.R;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    private ArrayList<Contact> mContacts;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        mContacts=new ArrayList<>();
        mRecyclerView=(RecyclerView)findViewById(R.id.contact_list_rv);
        mLinearLayoutManager=new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        getContactsIntoArrayList();

    }
    public void getContactsIntoArrayList(){


        ContentResolver cr = this.getContentResolver();
        ArrayList<Contact> alContacts = new ArrayList<Contact>();
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER ,ContactsContract.CommonDataKinds.Phone.PHOTO_URI};
        String sortOrder= ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,null, null, sortOrder);
        String name,phonenumber;
        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phonenumber=phonenumber.replaceAll(" ","");
            if(phonenumber.length()<10){
                continue;
            }
            if (phonenumber.length()>10){
                phonenumber=phonenumber.substring(phonenumber.length()-10);
            }

            alContacts.add(new Contact(name,phonenumber));
        }

        cursor.close();

        mContacts=alContacts;

        mRecyclerView.setAdapter(new ContactListAdapter(this,mContacts));

    }
}
