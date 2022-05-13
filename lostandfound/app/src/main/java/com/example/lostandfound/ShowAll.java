package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lostandfound.data.DatabaseHelper;
import com.example.lostandfound.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ShowAll extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> itemArrayList; //list that will be displayed in the listview
    DatabaseHelper db;
    ListView itemsListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall);
        itemsListView = (ListView) findViewById(R.id.itemsListView);
        itemArrayList = new ArrayList<>();
        db = new DatabaseHelper(ShowAll.this);

        List<Item> itemList = db.fetchAll();

        for (Item item : itemList) {
            itemArrayList.add(item.getType() +
                    " " +
                    item.getItem_name());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemArrayList); // adapter determines the style and content
        itemsListView.setAdapter((adapter)); //appends adapter to the listview component

        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int itemId = i;
                Intent removeAdIntent = new Intent(ShowAll.this, ShowAll.class);
                removeAdIntent.putExtra("itemId", itemId);
                startActivity(removeAdIntent);
                finish();
            }
        });
    }
}
