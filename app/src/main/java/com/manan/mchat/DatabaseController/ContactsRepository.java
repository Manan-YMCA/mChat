package com.manan.mchat.DatabaseController;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Suneja's on 10-06-2018.
 */

public class ContactsRepository {

    private ContactDao mContactsDao;

    private LiveData<List<Contact>> mContacts;
    private List<Contact> mAllContacts;

    public ContactsRepository(Application application) {
        AppDatabase db = AppDatabase.getContactsDatabase(application);
        mContactsDao = db.mContactsDao();
        mContacts = mContactsDao.getContacts();
    }

    public LiveData<List<Contact>> getContacts() {
        return mContacts;
    }

    public List<Contact> getAllContacts() {
        return mAllContacts;
    }

    public void insert(Contact contact) {
        new InsertAsyncTask(mContactsDao).execute(contact);
    }

    private static class InsertAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDao mContactsDao;

        public InsertAsyncTask(ContactDao contactsDao) {
            mContactsDao = contactsDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            mContactsDao.insert(contacts[0]);
            return null;
        }
    }

}
