package com.cmpe496.ekrem.e_commerce;

import android.provider.BaseColumns;

/**
 * Created by ekrem on 08/05/2017.
 */

public class FeedReaderContract {

    private FeedReaderContract() {}

    /* Inner class that defines the table contents for users */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_NAME = "name";



        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";

    }

    /* Inner class that defines the table contents for products */
    public static class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_YEAR = "year";
        public static final String COLUMN_LENGTH = "length";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_MATERIAL = "material";
        public static final String COLUMN_PRICE = "price";




        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_YEAR + " TEXT, " +
                COLUMN_LENGTH + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_MATERIAL + " TEXT, " +
                COLUMN_PRICE + " TEXT)";

    }

}
