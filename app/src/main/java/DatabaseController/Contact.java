package DatabaseController;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import Utilities.ConstantUtils;


@Entity(tableName = ConstantUtils.CONTACTS_TABLE)
public class Contact {

    private String mName;

    @PrimaryKey
    @NonNull
    private String mNumber;

    public Contact(String name, @NonNull String number) {
        mName = name;
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @NonNull
    public String getNumber() {
        return mNumber;
    }

    public void setNumber(@NonNull String number) {
        mNumber = number;
    }
}