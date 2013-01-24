package com.webviewprototype;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	final String NAME_MAIN="";
	final String PASSWORD_MAIN = "";
	int counter =0;
	public static final String EXTRA_MESSAGE ="com.webviewprototype.MESSAGE";
	public static final String PASSWORD_MESSAGE = "com.webviewprototype.PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }  
    
    public void submitMessage(View view) {
    	Intent intent = new Intent(this, CharityActivity.class);
    	EditText edit = (EditText) findViewById(R.id.user_name);
    	EditText edit2 = (EditText) findViewById(R.id.password);
    	TextView text = (TextView) findViewById(R.id.error);
    	String name = edit.getText().toString();
    	String pass = edit2.getText().toString();
    	if (!name.equals(NAME_MAIN)) {
    		text.setText("Wrong username, please try again");
    		text.setVisibility(View.VISIBLE);
    	}else if(!pass.equals(PASSWORD_MAIN)){
    		text.setText("Wrong password, please try again");
    		text.setVisibility(View.VISIBLE);
    	}else{
    		intent.putExtra(EXTRA_MESSAGE, edit.getText().toString());
        	startActivity(intent);
    	}
    	
    	
 
    	
    
    }
    
   /* 
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText edit = (EditText) findViewById(R.id.edit_message);	
    	String message = edit.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    */
}
