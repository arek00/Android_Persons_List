package com.example.Android_Application.Activities;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.Android_Application.ActivitiesHelpers.MyActivityFieldsHelper;
import com.example.Android_Application.Adapters.DBPersonAdapter;
import com.example.Android_Application.Adapters.PersonAdapter;
import com.example.Android_Application.DB.DBConnector;
import com.example.Android_Application.DB.OpenHelper;
import com.example.Android_Application.Person.Person;
import com.example.Android_Application.R;

import java.util.ArrayList;

public class MyActivity extends Activity {

    private MyActivityFieldsHelper fieldsHelper = new MyActivityFieldsHelper();


    private OpenHelper dbHelper = new OpenHelper(this);
    private DBPersonAdapter dbAdapter;
    private DBConnector connector = new DBConnector(dbHelper);
    private DBPersonAdapter adapter;
    private Cursor cursor;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fieldsHelper.setEditName((EditText)findViewById(R.id.editName));
        fieldsHelper.setEditSurname((EditText) findViewById(R.id.editSurname));
        fieldsHelper.setEditPhoneNumber((EditText) findViewById(R.id.editPhoneNumber));
        fieldsHelper.setPersonList((ListView)findViewById(R.id.personList));

        cursor = connector.getDataFromDB();
        adapter = new DBPersonAdapter(this, cursor);

        fieldsHelper.getPersonList().setAdapter(adapter);
    }

    public void onSave(View v){
        String name = fieldsHelper.getEditName().getText().toString();
        String surname = fieldsHelper.getEditSurname().getText().toString();
        String phoneNumber = fieldsHelper.getEditPhoneNumber().getText().toString();

        Person person = new Person(name, surname, phoneNumber);

        connector.putDataToDB(person);
    }
}
