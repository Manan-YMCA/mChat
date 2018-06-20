package com.manan.mchat.DatabaseController;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Suneja's on 10-06-2018.
 */

public class ContactsViewModel extends AndroidViewModel {

    private ContactsRepository mContactsRepository;
    private LiveData<List<Contact>> mContacts;
    private List<Contact> mAllContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        mContactsRepository = new ContactsRepository(application);
        mContacts = mContactsRepository.getContacts();
        mAllContacts = mContactsRepository.getAllContacts();
    }

    public LiveData<List<Contact>> getContacts() {
        return mContacts;
    }

    public List<Contact> getAllContacts() {
        return mAllContacts;
    }

    public void insert(Contact contact) {
        mContactsRepository.insert(contact);
    }
}
