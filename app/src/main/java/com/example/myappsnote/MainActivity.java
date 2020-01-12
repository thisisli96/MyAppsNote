package com.example.myappsnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> noted = new ArrayList<String>();
    static ArrayAdapter arrayAdapter;
    ListView listView ;

    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myappsnote", Context.MODE_PRIVATE);
        HashSet <String> set = (HashSet<String>) sharedPreferences.getStringSet("noted", null);

        if (set == null){
            noted.add("Add a new place");
        }else {
            noted = new ArrayList<>(set);
        }


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, noted);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemTodelete = position;
                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("DELETE !?").setMessage("Are you about delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                noted.remove(itemTodelete);
                                arrayAdapter.notifyDataSetChanged();

                                HashSet<String> set = new HashSet<>(MainActivity.noted);
                                sharedPreferences.edit().putStringSet("noted", set).apply();

                            }
                        }) .setNegativeButton("No", null).show();
                return true; // kalau false program mengeluarkan alert dan jg go to next activity.
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, noted.toString(), Toast.LENGTH_SHORT).show(); // to see list array yang di klik
                Intent intent = new Intent (getApplicationContext(), EditNoted.class);
                //intent.putExtra("position",noted.get(position)); menampilkan yang pertama
                intent.putExtra("position",position); // menampilkan kedua
                startActivity(intent);
            }
        });

    } // on create

    // MENU

    public boolean onCreateOptionsMenu(Menu menu){ // menambahkan menu setting di halaman utama

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note){
            Intent intent = new Intent(getApplicationContext(), EditNoted.class);
            startActivity(intent);
            return true;
        }
        return false;

//        switch (item.getItemId()){
//            case R.id.settings:
//
//                //Intent gonext = new Intent(getApplicationContext(), AddNote.class);
//                Intent gonext = new Intent(getApplicationContext(), EditText.class);
//
//                startActivity(gonext);
//                //return true;
//            default:
//                return  false;
//        }
    }
}
