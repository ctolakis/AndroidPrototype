package com.webviewprototype.database.testactivities;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.webviewprototype.R;
import com.webviewprototype.database.DB;
import com.webviewprototype.database.DBManager;

public class TestActivity extends Activity {
	
	private static final String LOG_TAG = "~TestActivity";
	
	private DB db;
	private EditText textBox;

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databasetest);
        this.db = new DBManager(this);
        this.textBox = (EditText) findViewById(R.id.text);
    }
    
    public void createForm(View view) {
    	Map<String, String> result = null;
    	String printOut = "";
    	long rowID = db.createForm(9001, 1, "Standard", "this.is.spam.cc", false);
    	Log.i(LOG_TAG, Long.toString(rowID));
    	if (rowID != -1)
    		result = db.getForm(rowID);
    	
    	if (result != null) {
    		for (String str : result.keySet()) {
    			printOut = printOut.concat(str).concat(": ").concat(result.get(str).concat("\n"));
    		}
    	}
    	
    	textBox.setText(printOut);
    }
    
    public void createFormField(View view) {
    	Map<String, String> result = null;
    	String printOut = "";
    	long rowID = db.createFormField(8, 1, "Testing", 1, (float) 2, (float) 3, true, false, (float) 1, (float) 2, 1, true);
    	Log.i(LOG_TAG, Long.toString(rowID));
    	if (rowID != -1)
    		result = db.getFormField(rowID);
    	
    	if (result != null) {
    		for (String str : result.keySet()) {
    			printOut = printOut.concat(str).concat(": ").concat(result.get(str).concat("\n"));
    		}
    	}
    	
    	textBox.setText(printOut);
    }
    
    public void createFieldType(View view) {
    	Map<String, String> result = null;
    	String printOut = "";
    	long rowID = db.createFieldType(4, "Who", "Is", "IMOU--");
    	Log.i(LOG_TAG, Long.toString(rowID));
    	if (rowID != -1)
    		result = db.getFieldType(rowID);
    	
    	if (result != null) {
    		for (String str : result.keySet()) {
    			printOut = printOut.concat(str).concat(": ").concat(result.get(str).concat("\n"));
    		}
    	}
    	
    	textBox.setText(printOut);
    }
    
    public void createFilledForm(View view) {
    	
    }

}
