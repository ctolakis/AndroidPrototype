package com.webviewprototype.database.testactivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.webviewprototype.R;

public class TestActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databasetest);
    }
    
    public void ping(View view) {
    	(Toast.makeText(this, "Pong", Toast.LENGTH_SHORT)).show();
    }

}
