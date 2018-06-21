package com.manan.mchat.DatabaseController;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.manan.mchat.Utilities.ConstantUtils;

@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract ContactDao mContactsDao();

    private static AppDatabase sContactsDatabase;

    public static AppDatabase getContactsDatabase(final Context context){
        if (sContactsDatabase==null){
            synchronized (Contact.class){
                if (sContactsDatabase==null){
                    sContactsDatabase= Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, ConstantUtils.CONSTACTS_DATABASE)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sContactsDatabase;
    }
}
