package com.antimatter.tasteful;

import android.animation.Animator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.tabs.TabLayout;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import app_utility.OnAsyncInterfaceListener;

public class MainActivity extends AppCompatActivity implements OnAsyncInterfaceListener {

    Stack<Integer> pageHistory;
    int currentPage;
    boolean saveToHistory;
//    private int nTotalPriceFood = 0;
//    private int nTotalItemsFood = 0;
//    private int nTotalPriceBeverage = 0;
//    private int nTotalItemsBeverage = 0;
    int finalPrice = 0;
    public static int finalPrices = 0;
    //int finalItems = 0;
    public static int nTotalItems = 0;

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    public static DataStorage dataStorage ;
    /*RecyclerView recyclerView;
    public MenuItemRVAdapter menuItemRVAdapter;
    TextView tvMenuItem, tvQuantity, tvPrice;*/
    TextView tvTotalItems, tvTotalPrice;
    private Button btnPlaceOrder;
    private FrameLayout llLotteParent;
    private LottieAnimationView lottieAnimationView;
    public static OnAsyncInterfaceListener onAsyncInterfaceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataStorage = new DataStorage();
        onAsyncInterfaceListener = this;
        pageHistory = new Stack<>();
        nTotalItems = 0;

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
        btnPlaceOrder = findViewById(R.id.btn_place_order);
        llLotteParent = findViewById(R.id.ll_animation_parent);
        lottieAnimationView = findViewById(R.id.lottie_view);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent in = new Intent(MainActivity.this, SocketService.class);
                startService(in);*/
                MyTask myTask = new MyTask();
                myTask.execute();

                llLotteParent.setVisibility(View.VISIBLE);
                lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                llLotteParent.setVisibility(View.GONE);
                                //Do something after 100ms
                            }
                        }, 2500);

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                lottieAnimationView.playAnimation();
            }
        });

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
    public void onResultReceived(String sMessage, int nCase, String sResult, LinkedHashMap<String, String> lhmFoodAndQuantity) {
        switch (sMessage) {
            case "UPDATE_ITEM_AND_PRICE":
                //MainActivity.lhmFoodAndQuantity = lhmFoodAndQuantity;
                //dataStorage.lhmFoodAndQuantity = lhmFoodAndQuantity;
                int Price = Integer.valueOf(sResult.split(",")[1]);
                //int items = Integer.valueOf(sResult.split(",")[0]);
                String sArithmetic = sResult.split(",")[2];
                //String sTotalItems = sResult.split(",")[0] + " items";
                //String sTotalPrice = getResources().getString(R.string.rupee) + sResult.split(",")[1];
                /*tvTotalItems.setText(sTotalItems);
                tvTotalPrice.setText(sTotalPrice);*/
                /*switch (nCase) {
                    case 1:
                        nTotalPriceFood = Integer.valueOf(sResult.split(",")[1]);
                        nTotalItemsFood = Integer.valueOf(sResult.split(",")[0]);
                        break;
                    case 2:
                        nTotalPriceBeverage = Integer.valueOf(sResult.split(",")[1]);
                        nTotalItemsBeverage = Integer.valueOf(sResult.split(",")[0]);
                        break;
                }*/

                if (sArithmetic.equals("+")) {
                    finalPrice = finalPrice + Price;
                    //finalItems = nTotalItemsFood + nTotalItemsBeverage;
                } else {
                    finalPrice = finalPrice - Price;
                    //finalItems = items;
                }
                tvTotalItems.setText(String.valueOf(nTotalItems));
                tvTotalPrice.setText(String.valueOf(finalPrice));
                finalPrices = finalPrice;
                break;
        }
    }

    private class MyTask extends AsyncTask<String, Integer, String> {

        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {

            // Do something like display a progress bar
        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {
            sendRequest();
            return null;
        }

        // This is called from background thread but runs in UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // Do things like update the progress bar
        }

        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Do things like hide the progress bar or change a TextView
        }
    }

    void sendRequest(){
        Socket socket;
        try {
            socket = new Socket("192.168.0.130",100);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            ArrayList<String> alFood = new ArrayList<>(dataStorage.lhmFoodAndQuantity.keySet());
            ArrayList<String> alQuantity = new ArrayList<>(dataStorage.lhmFoodAndQuantity.values());
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<alFood.size(); i++){
                sb.append(alFood.get(i)).append("-").append(alQuantity.get(i)).append(System.getProperty("line.separator"));
            }
            sb.append(System.getProperty("line.separator"));
            sb.append("Total").append("-").append(MainActivity.finalPrices);
            DOS.writeUTF(sb.toString());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class DataStorage{
        public LinkedHashMap<String, String> lhmFoodAndQuantity = new LinkedHashMap<>();
    }
}
