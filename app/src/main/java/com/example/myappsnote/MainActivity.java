package com.example.myappsnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> noted = new ArrayList<String>();
    static ArrayAdapter arrayAdapter;
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        noted.add("Add a new place");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, noted);
        listView.setAdapter(arrayAdapter);

    } // on create

}
