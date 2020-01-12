package com.example.myappsnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class EditNoted extends AppCompatActivity {

    TextView textchange;
    int textwanttoedit;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_noted);

       // textchange = findViewById(R.id.textchange);
        editText = findViewById(R.id.editText);

        //menampilkan pertama
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        final String positionlist = bundle.getString("position");
//        final int textwanttoedit = intent.getIntExtra("position", -1);
//       // textchange.setText(positionlist);
//        editText.setText(positionlist);
        // MainActivity.noted.add(editText.toString()); // untuk menampilkan tulisan yang ingin di edit

        // menampilkan yang kedua

        Intent intent = getIntent();
     textwanttoedit = intent.getIntExtra("position", -1);
        if (textwanttoedit != -1){ // untuk edit
            editText.setText(MainActivity.noted.get(textwanttoedit));
        } else { // untuk tambah
            MainActivity.noted.add("");
            textwanttoedit = MainActivity.noted.size()-1;

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivity.noted.set(textwanttoedit, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myappsnote", Context.MODE_PRIVATE);
                HashSet <String> set = new HashSet<>(MainActivity.noted);
                sharedPreferences.edit().putStringSet("noted", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






    }
}
