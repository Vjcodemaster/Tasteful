package com.antimatter.tasteful;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuItemRVAdapter extends RecyclerView.Adapter<MenuItemRVAdapter.MenuItemTabHolder> {

    Context context;
    private RecyclerView recyclerView;
    ArrayList<String> alFoodItems;
    ArrayList<String> alFoodPrice;
    //HashSet<Integer> hsTotalItems = new HashSet<>();
    private int nTotalItems = 0;
    private int nTotalPrice = 0;

    MenuItemRVAdapter(Context context, RecyclerView recyclerView, ArrayList alFoodItems, ArrayList<String> alFoodPrice) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.alFoodItems = alFoodItems;
        this.alFoodPrice = alFoodPrice;
    }

    @NonNull
    @Override
    public MenuItemTabHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_food_bevarages, parent, false);

        return new MenuItemTabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuItemTabHolder holder, final int position) {
        holder.tvMenuItem.setText(alFoodItems.get(position));
        holder.tvItemPrice.setText(alFoodPrice.get(position));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.valueOf(holder.tvQuantity.getText().toString());
                n++;
                if (n == 1) {
                    holder.tvTotalItemPrice.setVisibility(View.VISIBLE);
                    nTotalItems++;

                    //hsTotalItems.add(holder.getAdapterPosition());
                }
                String sItemPrice = alFoodPrice.get(position).substring(1);
                int totalPrice = Integer.valueOf(sItemPrice) * n;
                holder.tvTotalItemPrice.setText(String.valueOf(totalPrice));
                holder.tvQuantity.setText(String.valueOf(n));
                nTotalPrice = nTotalPrice + Integer.valueOf(sItemPrice);
                String sResult = String.valueOf(nTotalItems) + "," + String.valueOf(nTotalPrice);
                MainActivity.onAsyncInterfaceListener.onResultReceived("UPDATE_ITEM_AND_PRICE", 1, sResult);
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.valueOf(holder.tvQuantity.getText().toString());
                n--;
                if (n == -1)
                    Toast.makeText(context, "Lets be positive :)", Toast.LENGTH_SHORT).show();
                else if (n == 0) {
                    holder.tvTotalItemPrice.setVisibility(View.INVISIBLE);
                    //hsTotalItems.remove(holder.getAdapterPosition());
                    nTotalItems--;
                }
                if (n >= 0) {
                    String sItemPrice = alFoodPrice.get(position).substring(1);
                    int totalPrice = Integer.valueOf(holder.tvTotalItemPrice.getText().toString()) - Integer.valueOf(sItemPrice);
                    holder.tvTotalItemPrice.setText(String.valueOf(totalPrice));
                    nTotalPrice = nTotalPrice - Integer.valueOf(sItemPrice);
                    //holder.tvQuantity.setVisibility(View.VISIBLE);
                    String sResult = String.valueOf(nTotalItems) + "," + String.valueOf(nTotalPrice);
                    holder.tvQuantity.setText(String.valueOf(n));
                    MainActivity.onAsyncInterfaceListener.onResultReceived("UPDATE_ITEM_AND_PRICE", 1, sResult);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return alFoodItems.size(); //alBeaconInfo.size()
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class MenuItemTabHolder extends RecyclerView.ViewHolder {
        TextView tvMenuItem;
        TextView tvQuantity;
        //TextView tvSlNo;
        TextView tvTotalItemPrice;
        TextView tvItemPrice;
        Button btnPlus;
        Button btnMinus;

        MenuItemTabHolder(View itemView) {
            super(itemView);
            tvMenuItem = itemView.findViewById(R.id.tv_menu_item);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvItemPrice = itemView.findViewById(R.id.tv_item_price);
            tvTotalItemPrice = itemView.findViewById(R.id.tv_price_header);
            //tvSlNo = itemView.findViewById(R.id.tv_sl_no);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);
        }
    }

}
