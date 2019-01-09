package com.antimatter.tasteful;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import app_utility.OnAsyncInterfaceListener;

public class MainActivity extends AppCompatActivity implements OnAsyncInterfaceListener {

    RecyclerView recyclerView;
    public MenuItemRVAdapter menuItemRVAdapter;
    TextView tvMenuItem, tvQuantity, tvPrice;
    TextView tvTotalItems, tvTotalPrice;
    public static OnAsyncInterfaceListener onAsyncInterfaceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onAsyncInterfaceListener = this;

        recyclerView = findViewById(R.id.rv_menu_item_list);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/quesha.ttf");
        tvMenuItem = findViewById(R.id.tv_menu_item_header);
        tvQuantity = findViewById(R.id.tv_quantity_header);
        tvPrice = findViewById(R.id.tv_price_header);

        tvTotalItems = findViewById(R.id.tv_total_items);
        tvTotalPrice = findViewById(R.id.tv_total_price);

        tvMenuItem.setTypeface(font);
        tvQuantity.setTypeface(font);
        tvPrice.setTypeface(font);
        tvMenuItem.setTextSize(26);
        tvQuantity.setTextSize(26);
        tvPrice.setTextSize(26);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        ArrayList<String> alFoodItems = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_items)));
        ArrayList<String> alFoodPrice = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_price)));
        menuItemRVAdapter = new MenuItemRVAdapter(MainActivity.this, recyclerView, alFoodItems, alFoodPrice);
        recyclerView.setAdapter(menuItemRVAdapter);
        //TableRow trHeading = (TableRow) getLayoutInflater().inflate(R.layout.table_row_heading, null);
    }

    @Override
    public void onResultReceived(String sMessage, int nCase, String sResult) {
        switch (sMessage){
            case "UPDATE_ITEM_AND_PRICE":
                String sTotalItems = sResult.split(",")[0] + " items";
                String sTotalPrice = getResources().getString(R.string.rupee) + sResult.split(",")[1];
                tvTotalItems.setText(sTotalItems);
                tvTotalPrice.setText(sTotalPrice);
                break;
        }
    }
}
