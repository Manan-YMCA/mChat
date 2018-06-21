package com.manan.mchat.UI.MainChatPage;


import android.arch.lifecycle.Observer;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manan.mchat.DatabaseController.Contact;
import com.manan.mchat.DatabaseController.ContactsViewModel;
import com.manan.mchat.R;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    List<Contact> mContacts = new ArrayList<>();
    private List<Contact> alContacts;
    private int ADAPTER_SET = 0;
    private ContactsViewModel mContactsViewModel;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ContactListAdapter mContactListAdapter;
    private View framentView;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        framentView = inflater.inflate(R.layout.fragment_contacts, container, false);


        mContacts = new ArrayList<>();

        mRecyclerView = framentView.findViewById(R.id.contact_list_rv);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(new ContactListAdapter(getActivity(), mContacts));
        mContactsViewModel = new ContactsViewModel(getActivity().getApplication());
        mContactsViewModel.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                mContacts = contacts;
                if (ADAPTER_SET == 0) {
                    mContactListAdapter = new ContactListAdapter(getApplicationContext(), mContacts);
                    mRecyclerView.setAdapter(mContactListAdapter);
                    ADAPTER_SET = 1;
                } else {
                    mContactListAdapter.setContacts(mContacts);
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                getContactsIntoList();
            }
        }).run();

        return framentView;
    }

    public void getContactsIntoList() {

        ContentResolver cr = getActivity().getApplication().getContentResolver();
        List<Contact> alContacts = new ArrayList<Contact>();
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_URI};
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, sortOrder);
        String name, phonenumber;
        Contact mContact;
        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phonenumber = phonenumber.replaceAll(" ", "");
            if (phonenumber.length() < 10) {
                continue;
            }
            if (phonenumber.length() > 10) {
                phonenumber = phonenumber.substring(phonenumber.length() - 10);
            }
            mContact = new Contact(name, phonenumber);
            mContactsViewModel.insert(mContact);

        }

        cursor.close();

//        mContacts=alContacts;

    }


}
