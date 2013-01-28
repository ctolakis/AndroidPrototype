package com.webviewprototype.database.testactivities;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.webviewprototype.R;
import com.webviewprototype.database.DBContract;
import com.webviewprototype.database.DBHelper;

public class TestActivity extends Activity {
	
	private static final String log_tag = "TestActivity";

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databasetest);
    }
    
    public void ping(View view) {
    	(Toast.makeText(this, "Pong", Toast.LENGTH_SHORT)).show();
    	add();
    	read();
    }
    
    private void read() {
    	Log.d(log_tag, "getting Database");
    	SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
    	
    	Log.d(log_tag, "querying Database");
    	String query = "SELECT * FROM " + DBContract.FormTable.TABLE_NAME;
    	Cursor cursor = db.rawQuery(query, null);
    	Log.d(log_tag, "reading entries");
    	Log.d(log_tag, "Cursor length: " + Integer.toString(cursor.getCount()));
    	if (cursor.getCount() > 0) {
	    	cursor.moveToFirst();
	    	do {
	    		Long form_id = cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.FormTable._ID));
	    		Log.i(log_tag, Long.toString(form_id));
	    	} while (cursor.moveToNext());
    	}
    }
    
    private void add() {
    	Log.d(log_tag, "getting Database");
    	SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
    	
    	Log.d(log_tag, "adding to Database");
    	ContentValues values = new ContentValues();
    	values.put(DBContract.FormTable.COLUMN_NAME_FORM_ID, 3);
    	values.put(DBContract.FormTable.COLUMN_NAME_FORM_TYPE_ID, 3);
    	values.put(DBContract.FormTable.COLUMN_NAME_URL, "www.google.co.uk");
    	
    	long newRowId = db.insert(DBContract.FormTable.TABLE_NAME, null, values);
    	Log.i(log_tag, "insert successful, newRowId: " + Long.toString(newRowId));
    }

}
