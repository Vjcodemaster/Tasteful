package com.antimatter.tasteful;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TableRow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public MenuItemRVAdapter menuItemRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_menu_item_list);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        ArrayList<String> alFoodItems = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_items)));
        menuItemRVAdapter = new MenuItemRVAdapter(MainActivity.this, recyclerView, alFoodItems);
        recyclerView.setAdapter(menuItemRVAdapter);
        //TableRow trHeading = (TableRow) getLayoutInflater().inflate(R.layout.table_row_heading, null);
    }
}
