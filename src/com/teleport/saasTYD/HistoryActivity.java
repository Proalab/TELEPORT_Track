package com.teleport.saasTYD;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
public class HistoryActivity extends Activity {

	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_history);

        {          
            {
//////////////////////////////////////////////////////////////////////////////////////////////////////////
            	   DatabaseHandler db = new DatabaseHandler(this);
                   
                   /**
                    * CRUD Operations
                    * */
                   // Inserting Contacts
              //     Log.d("Insert: ", "Inserting .."); 
                   // �������� ���������, ����� �� ���������� ���� ���������
              //     db.addContact(new SQLInquiry("Ravi2", "9100000000"));        
              //     db.addContact(new SQLInquiry("Srinivas2", "9199999999"));
              //     db.addContact(new SQLInquiry("Tommy2", "9522222222"));
              //     db.addContact(new SQLInquiry("Karthik2", "9533333333"));
                    
                   // Reading all contacts
                   Log.d("Reading: ", "Reading all contacts.."); 
                   List<SQLInquiry> contacts = db.getAllContacts();       
                    
                   for (SQLInquiry cn : contacts) {
                   //    String log = "Id: "+cn.getID()+" ,Name: " + cn.getInquiryID() + " ,Phone: " + cn.getInquiryTime();
                	   String log = "Id: "+ cn.getID() + " ,inquiryidforuser from SQL: " + cn.getInquiryID() + " ,inquirytime from SQL: " + cn.getInquiryTime() + " ,getInquirycost from SQL: " + cn.getinquiry_cost();
                           // Writing Contacts to log
                	   Log.d("Name: ", log);
                   Log.d("inquiryidforuser_SQL: ", cn.getInquiryID() + "");
                   Log.d("inquirytime_SQL: ", cn.getInquiryTime() + "");
                  Log.d("Inquirycost_SQL: ", cn.getinquiry_cost() + "");
                   Log.d("sender_adress_SQL: ", cn.getsender_adress() + "");
                   Log.d("receiver_fio_SQL: ", cn.getreceiver_fio() + "");
                   Log.d("receiver_adress_SQL: ", cn.getreceiver_adress() + "");
                   Log.d("receiver_phone_SQL: ", cn.getreceiver_phone() + "");
               }
//////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
          };
       
    }

    public void onClickTeleportwww(View v){
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teleport-ds.com/"));
			startActivity(browserIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    // ��������� ������� ������� ����
 	@Override
 	public boolean onMenuItemSelected(int featureId, MenuItem item) {
 		switch (item.getItemId()) {
 		case R.id.menu_about: {
 			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teleport-ds.com/"));
 			startActivity(browserIntent);
 			break;
 		}
 	

// 		case R.id.menu_exit: {
// 			onDestroy();  
// 			break;
// 		}		
 		}
 		return super.onMenuItemSelected(featureId, item);
 	}   
    
//      @Override
//      public void onDestroy() {
//         super.onDestroy();
//         System.exit(0);
//      }
  
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
} 
	
	
} 