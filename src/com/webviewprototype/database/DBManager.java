package com.webviewprototype.database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper implements DB {
	
	private static final String LOG_TAG = "~DBManager";
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA = ", ";
	
	private SQLiteDatabase db;
	
	public DBManager(Context context) {
		super(context, DBContract.DATABASE_NAME, null, DBContract.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS " +
						DBContract.FormTable.TABLE_NAME + " (" +
						DBContract.FormTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						DBContract.FormTable.COLUMN_NAME_FORM_ID + " INTEGER UNIQUE, " +
						DBContract.FormTable.COLUMN_NAME_FORM_TYPE_ID + " INTEGER, " +
						DBContract.FormTable.COLUMN_NAME_FORM_NAME + TEXT_TYPE + COMMA +
						DBContract.FormTable.COLUMN_NAME_DATE_CREATED + TEXT_TYPE + COMMA +
						DBContract.FormTable.COLUMN_NAME_URL + TEXT_TYPE + COMMA +
						DBContract.FormTable.COLUMN_NAME_IS_ACTIVE + " INTEGER" +
						" )"
				);
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS " +
						DBContract.FormFields.TABLE_NAME + " (" +
						DBContract.FormFields._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						DBContract.FormFields.COLUMN_NAME_FIELD_ID + " INTEGER UNIQUE, " +
						DBContract.FormFields.COLUMN_NAME_FORM_ID + " INTEGER, " +
						DBContract.FormFields.COLUMN_NAME_FIELD_LABEL + TEXT_TYPE + COMMA +
						DBContract.FormFields.COLUMN_NAME_FIELD_TYPE_ID + " INTEGER, " +
						DBContract.FormFields.COLUMN_NAME_X_COORDINATE + " REAL, " +
						DBContract.FormFields.COLUMN_NAME_Y_COORDINATE + " REAL, " +
						DBContract.FormFields.COLUMN_NAME_IS_REQUIRED + " INTEGER, " +
						DBContract.FormFields.COLUMN_NAME_DEFAULT_VALUE + " INTEGER, " +
						DBContract.FormFields.COLUMN_NAME_MIN_VALUE + " REAL, " +
						DBContract.FormFields.COLUMN_NAME_MAX_VALUE + " REAL, " +
						DBContract.FormFields.COLUMN_NAME_USER_ID + " INTEGER, " +
						DBContract.FormFields.COLUMN_NAME_DATE_CREATED + TEXT_TYPE + COMMA +
						DBContract.FormFields.COLUMN_NAME_IS_ACTIVE + " INTEGER" +
						" )"
				);
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS " +
						DBContract.FieldType.TABLE_NAME + " (" +
						DBContract.FieldType._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						DBContract.FieldType.COLUMN_NAME_FIELD_TYPE_ID + " INTEGER UNIQUE, " +
						DBContract.FieldType.COLUMN_NAME_FIELD_TYPE + TEXT_TYPE + COMMA +
						DBContract.FieldType.COLUMN_NAME_FIELD_DATA_TYPE + TEXT_TYPE + COMMA +
						DBContract.FieldType.COLUMN_NAME_FIELD_DESCRIPTION + TEXT_TYPE + 
						" )"
				);
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS " +
						DBContract.FilledForm.TABLE_NAME + " (" +
						DBContract.FilledForm.COLUMN_NAME_FILLED_FORM_ID + " INTEGER UNIQUE, " +
						DBContract.FilledForm.COLUMN_NAME_FIELD_ID + " INTEGER, " +
						DBContract.FilledForm.COLUMN_NAME_VALUE + TEXT_TYPE + COMMA +
						DBContract.FilledForm.COLUMN_NAME_USER_ID + " INTEGER, " +
						DBContract.FilledForm.COLUMN_NAME_RECORD_ID + " INTEGER, " +
						DBContract.FilledForm.COLUMN_NAME_IS_ACTIVE + " INTEGER" +
						" )"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DBContract.FormTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DBContract.FormFields.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DBContract.FieldType.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DBContract.FilledForm.TABLE_NAME);
		onCreate(db);
	}
	
	public long createForm(Integer formID, Integer formTypeID, 
			String formName, String URL, Boolean isActive) {
		long rowID = -1;
		final ContentValues values = new ContentValues();
		values.put(DBContract.FormTable.COLUMN_NAME_FORM_ID, formID);
		values.put(DBContract.FormTable.COLUMN_NAME_FORM_TYPE_ID, formTypeID);
		values.put(DBContract.FormTable.COLUMN_NAME_FORM_NAME, formName);
		values.put(DBContract.FormTable.COLUMN_NAME_DATE_CREATED, new Date().toString());
		values.put(DBContract.FormTable.COLUMN_NAME_URL, URL);
		if (isActive)
			values.put(DBContract.FormTable.COLUMN_NAME_IS_ACTIVE, 1);
		else
			values.put(DBContract.FormTable.COLUMN_NAME_IS_ACTIVE, 0);
		
		try {
			db = this.getWritableDatabase();
			db.beginTransaction();
			rowID = db.insert(DBContract.FormTable.TABLE_NAME, null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
		return rowID;
	}
	
	public long createFormField(Integer fieldId, Integer formID,
			String fieldLabel, Integer fieldTypeID, Float xCoord, 
			Float yCoord, Boolean isRequired, Boolean defaultValue,
			Float minValue, Float maxValue, Integer userID,
			Boolean isActive) {
		long rowID = -1;
		final ContentValues values = new ContentValues();
		values.put(DBContract.FormFields.COLUMN_NAME_FIELD_ID, fieldId);
		values.put(DBContract.FormFields.COLUMN_NAME_FORM_ID, formID);
		values.put(DBContract.FormFields.COLUMN_NAME_FIELD_LABEL, fieldLabel);
		values.put(DBContract.FormFields.COLUMN_NAME_FIELD_TYPE_ID, fieldTypeID);
		values.put(DBContract.FormFields.COLUMN_NAME_X_COORDINATE, xCoord);
		values.put(DBContract.FormFields.COLUMN_NAME_Y_COORDINATE, yCoord);
		if (isRequired)
			values.put(DBContract.FormFields.COLUMN_NAME_IS_REQUIRED, 1);
		else 
			values.put(DBContract.FormFields.COLUMN_NAME_IS_REQUIRED, 0);
		if (defaultValue)
			values.put(DBContract.FormFields.COLUMN_NAME_DEFAULT_VALUE, 1);
		else
			values.put(DBContract.FormFields.COLUMN_NAME_DEFAULT_VALUE, 0);
		values.put(DBContract.FormFields.COLUMN_NAME_MIN_VALUE, minValue);
		values.put(DBContract.FormFields.COLUMN_NAME_MAX_VALUE, maxValue);
		values.put(DBContract.FormFields.COLUMN_NAME_USER_ID, userID);
		values.put(DBContract.FormFields.COLUMN_NAME_DATE_CREATED, new Date().toString());
		if (isActive)
			values.put(DBContract.FormFields.COLUMN_NAME_IS_ACTIVE, 1);
		else
			values.put(DBContract.FormFields.COLUMN_NAME_IS_ACTIVE, 0);
		
		try {
			db = this.getWritableDatabase();
			db.beginTransaction();
			rowID = db.insert(DBContract.FormFields.TABLE_NAME, null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
		
		return rowID;
	}
	
	public long createFieldType(Integer fieldTypeID, String fieldType,
			String fieldDataType, String fieldDescription) {
		long rowID = -1;
		final ContentValues values = new ContentValues();
		values.put(DBContract.FieldType.COLUMN_NAME_FIELD_TYPE_ID, fieldTypeID);
		values.put(DBContract.FieldType.COLUMN_NAME_FIELD_TYPE, fieldType);
		values.put(DBContract.FieldType.COLUMN_NAME_FIELD_DATA_TYPE, fieldDataType);
		values.put(DBContract.FieldType.COLUMN_NAME_FIELD_DESCRIPTION, fieldDescription);
		
		try {
			db = this.getWritableDatabase();
			db.beginTransaction();
			rowID = db.insert(DBContract.FieldType.TABLE_NAME, null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
		
		return rowID;
	}
	
	public long createFilledForm(Integer filledFormID, Integer formID,
			Integer fieldID, String value, Integer userID, Integer recordID,
			Boolean isActive) {
		long rowID = -1;
		final ContentValues values = new ContentValues();
		values.put(DBContract.FilledForm.COLUMN_NAME_FILLED_FORM_ID, filledFormID);
		values.put(DBContract.FilledForm.COLUMN_NAME_FORM_ID, formID);
		values.put(DBContract.FilledForm.COLUMN_NAME_FIELD_ID, fieldID);
		values.put(DBContract.FilledForm.COLUMN_NAME_VALUE, value);
		values.put(DBContract.FilledForm.COLUMN_NAME_USER_ID, userID);
		values.put(DBContract.FilledForm.COLUMN_NAME_RECORD_ID, recordID);
		if (isActive)
			values.put(DBContract.FilledForm.COLUMN_NAME_IS_ACTIVE, 1);
		else
			values.put(DBContract.FilledForm.COLUMN_NAME_IS_ACTIVE, 0);
		
		try {
			db = this.getWritableDatabase();
			db.beginTransaction();
			rowID = db.insert(DBContract.FilledForm.TABLE_NAME, null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
		
		return rowID;
	}
	
	public Map<String, String> getForm(long rowID) {
		HashMap<String, String> entry = new HashMap<String, String>();
		Cursor cursor = null;
		Log.i(LOG_TAG, Long.toString(rowID));
		try {
			db = this.getReadableDatabase();
			db.beginTransaction();
			String query = "SELECT * FROM " + DBContract.FormTable.TABLE_NAME +
			 		" LIMIT 1 OFFSET " + Long.toString(rowID - 1);
			Log.i(LOG_TAG, query);
			cursor = db.rawQuery(query, null);
			db.setTransactionSuccessful();
			
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				entry.put(cursor.getColumnName(1), Integer.toString(cursor.getInt(1)));
				entry.put(cursor.getColumnName(2), Integer.toString(cursor.getInt(2)));
				entry.put(cursor.getColumnName(3), cursor.getString(3));
				entry.put(cursor.getColumnName(5), cursor.getString(5));
			}
		} finally {
			db.endTransaction();
			db.close();
		}
		
		return entry;
	}
	
	public Map<String, String> getFormField(long rowID) {
		HashMap<String, String> entry = new HashMap<String, String>();
		Cursor cursor = null;
		try {
			db = this.getReadableDatabase();
			db.beginTransaction();
			String query = "SELECT * FROM " + DBContract.FormFields.TABLE_NAME +
					" LIMIT 1 OFFSET " + Long.toString(rowID - 1);
			cursor = db.rawQuery(query, null);
			db.setTransactionSuccessful();
			
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				entry.put(cursor.getColumnName(1), Integer.toString(cursor.getInt(1)));
				entry.put(cursor.getColumnName(2), Integer.toString(cursor.getInt(2)));
				entry.put(cursor.getColumnName(3), cursor.getString(3));
				entry.put(cursor.getColumnName(4), Integer.toString(cursor.getInt(4)));
				entry.put(cursor.getColumnName(5), Float.toString(cursor.getFloat(5)));
				entry.put(cursor.getColumnName(6), Float.toString(cursor.getFloat(6)));
				entry.put(cursor.getColumnName(9), Float.toString(cursor.getFloat(9)));
				entry.put(cursor.getColumnName(10), Float.toString(cursor.getFloat(10)));
				entry.put(cursor.getColumnName(11), Integer.toString(cursor.getInt(11)));
			}
		} finally {
			db.endTransaction();
			db.close();
		}
		
		return entry;
	}
	
	public Map<String, String> getFieldType(long rowID) {
		HashMap<String, String> entry = new HashMap<String, String>();
		Cursor cursor = null;
		try {
			db = this.getReadableDatabase();
			db.beginTransaction();
			String query = "SELECT * FROM " + DBContract.FieldType.TABLE_NAME +
					" LIMIT 1 OFFSET " + Long.toString(rowID - 1);
			cursor = db.rawQuery(query, null);
			db.setTransactionSuccessful();
			
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				entry.put(cursor.getColumnName(1), Integer.toString(cursor.getInt(1)));
				entry.put(cursor.getColumnName(2), cursor.getString(2));
				entry.put(cursor.getColumnName(3), cursor.getString(3));
				entry.put(cursor.getColumnName(4), cursor.getString(4));
			}
		} finally {
			db.endTransaction();
			db.close();
		}
		
		return entry;
	} 
	
	public Map<String, String> getFilledForm(long rowID) {
		HashMap<String, String> entry = new HashMap<String, String>();
		
		entry.put("I will do this", "Next");
		
		return entry;
	}
}
