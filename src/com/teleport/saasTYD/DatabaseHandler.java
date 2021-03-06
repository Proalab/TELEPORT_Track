package com.teleport.saasTYD;

// http://www.vogella.com/tutorials/AndroidSQLite/article.html#databasetutorial
//http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/


import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "InquiryManager";

 
    // Contacts table name
    private static final String TABLE_Inquiry = "inquiry";
 
    // Inquiry Table Columns names
    private static final String KEY_ID = "id";
    public static final String KEY_inquiry_id = "inquiry_id";
    public static final String KEY_inquiry_time = "inquiry_time";
    //
    public static final String KEY_inquiry_cost = "inquiry_cost";
    public static final String KEY_sender_adress = "sender_adress";
    public static final String KEY_receiver_fio = "receiver_fio";
    public static final String KEY_receiver_adress = "receiver_adress";
    public static final String KEY_receiver_phone = "receiver_phone";
    
    
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Inquiry + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_inquiry_id + " TEXT,"
                + KEY_inquiry_time + " TEXT,"
                + KEY_inquiry_cost + " TEXT,"
                + KEY_sender_adress + " TEXT,"
                + KEY_receiver_fio + " TEXT,"
                + KEY_receiver_adress + " TEXT,"
                + KEY_receiver_phone + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
    	Log.w(DatabaseHandler.class.getName(),
    	        "Upgrading database from version " + oldVersion + " to "
    	            + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Inquiry);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new inquiry
    void addContact(SQLInquiry contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_inquiry_id, contact.getInquiryID()); // Contact Name
        values.put(KEY_inquiry_time, contact.getInquiryTime());
        values.put(KEY_inquiry_cost, contact.getinquiry_cost());
        values.put(KEY_sender_adress, contact.getsender_adress());
        values.put(KEY_receiver_fio, contact.getreceiver_fio());
        values.put(KEY_receiver_adress, contact.getreceiver_adress());
        values.put(KEY_receiver_phone, contact.getreceiver_phone());// Contact Phone
 
        // Inserting Row
        db.insert(TABLE_Inquiry, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single inquiry
    SQLInquiry getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_Inquiry, new String[] { KEY_ID,
        		KEY_inquiry_id, KEY_inquiry_time, KEY_inquiry_cost, KEY_sender_adress, KEY_receiver_fio, KEY_receiver_adress, KEY_receiver_phone }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        SQLInquiry contact = new SQLInquiry(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
        // return contact
        return contact;
    }
    
    // Getting single inquiry ID // work but with bugs - ���������� �� ���������� �������� �� ���� ����
    String getinqID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_Inquiry, new String[] { KEY_ID,
        		KEY_inquiry_id, KEY_inquiry_time, KEY_inquiry_cost, KEY_sender_adress, KEY_receiver_fio, KEY_receiver_adress, KEY_receiver_phone }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        String iid = cursor.getString(1);
        Log.d("iid in db handler", iid + "");
        // return contact
        return iid;
    }
     
    // Getting All Inquiry
    public List<SQLInquiry> getAllContacts() {
        List<SQLInquiry> contactList = new ArrayList<SQLInquiry>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Inquiry;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	SQLInquiry contact = new SQLInquiry();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setInquiryID(cursor.getString(1));
                contact.setInquiryTime(cursor.getString(2));
                contact.setinquiry_cost(cursor.getString(3));
                contact.setsender_adress(cursor.getString(4));
                contact.setreceiver_fio(cursor.getString(5));
                contact.setreceiver_adress(cursor.getString(6));
                contact.setreceiver_phone(cursor.getString(7));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
       
 
    // Updating single inquiry
    public int updateContact(SQLInquiry contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_inquiry_id, contact.getInquiryID());
        values.put(KEY_inquiry_time, contact.getInquiryTime());
        values.put(KEY_inquiry_cost, contact.getinquiry_cost());
        values.put(KEY_sender_adress, contact.getsender_adress());
        values.put(KEY_receiver_fio, contact.getreceiver_fio());
        values.put(KEY_receiver_adress, contact.getreceiver_adress());
        values.put(KEY_receiver_phone, contact.getreceiver_phone());
 
        // updating row
        return db.update(TABLE_Inquiry, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }
 
    // Deleting single inquiry
    public void deleteContact(SQLInquiry contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Inquiry, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
 
 
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Inquiry;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}