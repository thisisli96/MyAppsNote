package com.example.myappsnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class EditNoted extends AppCompatActivity {

    TextView textchange;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_noted);

       // textchange = findViewById(R.id.textchange);
        editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String positionlist = bundle.getString("position");
       // textchange.setText(positionlist);
        editText.setText(positionlist);

        MainActivity.noted.add(editText.toString());
        //MainActivity.location.add(latLng);
        MainActivity.arrayAdapter.notifyDataSetChanged();

    }
}
