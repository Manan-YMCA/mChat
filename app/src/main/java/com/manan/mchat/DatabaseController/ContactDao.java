package com.manan.mchat.DatabaseController;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface ContactDao {
    @Insert(onConflict = IGNORE)
    void insert(Contact contact);

    @Query("Select * from contacts_table  order by mName")
    public LiveData<List<Contact>> getContacts();

    @Query("Select * from contacts_table  order by mName ")
    public List<Contact> getAllContacts();
}
