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

    MenuItemRVAdapter(Context context, RecyclerView recyclerView, ArrayList alFoodItems) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.alFoodItems = alFoodItems;
    }


    @NonNull
    @Override
    public MenuItemTabHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu_item, parent, false);

        return new MenuItemTabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuItemTabHolder holder, final int position) {
        holder.tvMenuItem.setText(alFoodItems.get(position));
        holder.tvSlNo.setText(String.valueOf(position+1));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.valueOf(holder.tvQuantity.getText().toString());
                if(n==10){
                    Toast.makeText(context, "Maximum of 10 is allowed per item", Toast.LENGTH_SHORT).show();
                } else {
                    n++;
                    holder.tvQuantity.setText(String.valueOf(n));
                }
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.valueOf(holder.tvQuantity.getText().toString());
                if(n==0){
                    Toast.makeText(context, "Lets be positive :)", Toast.LENGTH_SHORT).show();
                } else {
                    n--;
                    holder.tvQuantity.setText(String.valueOf(n));
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

    static class  MenuItemTabHolder extends RecyclerView.ViewHolder {
        //CircleImageView civDP;
        //private LinearLayout llParentExpand;
        //private ExpandableLayout expandableLayout;
        //Button btnEdit, btnRemove;

        //ImageView ivCircularDp;
        TextView tvMenuItem;
        TextView tvQuantity;
        TextView tvSlNo;
        Button btnPlus;
        Button btnMinus;

        MenuItemTabHolder(View itemView) {
            super(itemView);
            tvMenuItem = itemView.findViewById(R.id.tv_menu_item);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvSlNo = itemView.findViewById(R.id.tv_sl_no);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            //civDP = itemView.findViewById(R.id.civ_dp);
        }
    }

}
