package DatabaseController;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.Context;
import DatabaseController.Contact;
import java.util.ArrayList;
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
