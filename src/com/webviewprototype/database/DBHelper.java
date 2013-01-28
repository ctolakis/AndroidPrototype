package com.webviewprototype.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CharityApp.db";
    
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = 
    		"CREATE TABLE IF NOT EXISTS " + DBContract.FormTable.TABLE_NAME + " (" +
    		DBContract.FormTable._ID + " INTEGER PRIMARY KEY," +
    		DBContract.FormTable.COLUMN_NAME_FORM_ID + " INTEGER UNIQUE," +
    		DBContract.FormTable.COLUMN_NAME_FORM_TYPE_ID + " INTEGER UNIQUE," +
    		DBContract.FormTable.COLUMN_NAME_FORM_NAME + TEXT_TYPE + COMMA_SEP +
    		DBContract.COLUMN_NAME_DATE_CREATED + TEXT_TYPE + COMMA_SEP +
    		DBContract.FormTable.COLUMN_NAME_URL + TEXT_TYPE + 
    		" )";
    private static final String SQL_DELETE_ENTRIES = 
    		"DROP TABLE IF EXISTS " + DBContract.FormTable.TABLE_NAME;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
	}

}
