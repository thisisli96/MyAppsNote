package com.example.myappsnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    EditText addText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addText = findViewById(R.id.addText);
        String address = "";
        address += addText.toString();

        MainActivity.noted.add(address);
        //MainActivity.location.add(latLng);
        MainActivity.arrayAdapter.notifyDataSetChanged();

    }
}
