package com.example.myappsnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> noted = new ArrayList<String>();
    static ArrayAdapter arrayAdapter;
    ListView listView ;

    public boolean onCreateOptionsMenu(Menu menu){ // menambahkan menu setting di halaman utama

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);


        switch (item.getItemId()){
            case R.id.settings:
                //Log.i("item selected", "settings");
                Intent gonext = new Intent(getApplicationContext(), AddNote.class);
                startActivity(gonext);
                //return true;
            default:
                return  false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        noted.add("Add a new place");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, noted);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, noted.toString(), Toast.LENGTH_SHORT).show(); // to see list array yang di klik
                Intent intent = new Intent (getApplicationContext(), EditNoted.class);
                intent.putExtra("position",noted.get(position));
                startActivity(intent);
            }
        });

    } // on create

}
