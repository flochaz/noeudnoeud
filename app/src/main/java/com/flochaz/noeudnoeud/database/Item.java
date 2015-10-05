package com.flochaz.noeudnoeud.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by flochaz on 10/3/15.
 */
public class Item  implements BaseColumns {


    public static final String TABLE_NAME = "Item";
    // Naming the id column with an underscore is good to be consistent
    // with other Android things. This is ALWAYS needed
    public static final String COL_ID = BaseColumns._ID;
    // These fields can be anything you want.
    public static final String COL_NAME = "name";
    //public static final String COL_IMAGE = "image";
    //public static final String COL_QRCODE = "QRCODE";
    //public static final String COL_RECORDING = "RECORDING";


    // For database projection so order is consistent
    public static final String[] FIELDS = { COL_ID, COL_NAME };

    /*
     * The SQL code that creates a Table for storing Persons in.
     * Note that the last row does NOT end in a comma like the others.
     * This is a common source of error.
     */
    public static final  String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY,"
            + COL_NAME + " TEXT NOT NULL DEFAULT''"
            + ")";

    // Fields corresponding to database columns
    public long id = -1;
    public String name = "";

    /**
     * No need to do anything, fields are already set to default values above
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Convert information from the database into a Person object.
     */
    public Item(final Cursor cursor) {
        // Indices expected to match order in FIELDS!
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
    }

    /**
     * Return the fields in a ContentValues object, suitable for insertion
     * into the database.
     */
    public ContentValues getContent() {
        final ContentValues values = new ContentValues();
        // Note that ID is NOT included here
        values.put(COL_NAME, name);

        return values;
    }



}
