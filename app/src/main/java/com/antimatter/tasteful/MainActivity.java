package com.antimatter.tasteful;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Stack;

import app_utility.OnAsyncInterfaceListener;

public class MainActivity extends AppCompatActivity implements OnAsyncInterfaceListener {

    Stack<Integer> pageHistory;
    int currentPage;
    boolean saveToHistory;

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    /*RecyclerView recyclerView;
    public MenuItemRVAdapter menuItemRVAdapter;
    TextView tvMenuItem, tvQuantity, tvPrice;*/
    TextView tvTotalItems, tvTotalPrice;
    public static OnAsyncInterfaceListener onAsyncInterfaceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onAsyncInterfaceListener = this;
        pageHistory = new Stack<>();

        mViewPager = findViewById(R.id.viewpager_container);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (saveToHistory)
                    pageHistory.push(currentPage);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        saveToHistory = true;

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        /*recyclerView = findViewById(R.id.rv_menu_item_list);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/quesha.ttf");
        tvMenuItem = findViewById(R.id.tv_menu_item_header);
        tvQuantity = findViewById(R.id.tv_quantity_header);
        tvPrice = findViewById(R.id.tv_price_header);*/

        tvTotalItems = findViewById(R.id.tv_total_items);
        tvTotalPrice = findViewById(R.id.tv_total_price);

        /*tvMenuItem.setTypeface(font);
        tvQuantity.setTypeface(font);
        tvPrice.setTypeface(font);
        tvMenuItem.setTextSize(26);
        tvQuantity.setTextSize(26);
        tvPrice.setTextSize(26);*/

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        ArrayList<String> alFoodItems = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_items)));
        ArrayList<String> alFoodPrice = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_price)));
        menuItemRVAdapter = new MenuItemRVAdapter(MainActivity.this, recyclerView, alFoodItems, alFoodPrice);
        recyclerView.setAdapter(menuItemRVAdapter);*/
        //TableRow trHeading = (TableRow) getLayoutInflater().inflate(R.layout.table_row_heading, null);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), 2, 0);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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
