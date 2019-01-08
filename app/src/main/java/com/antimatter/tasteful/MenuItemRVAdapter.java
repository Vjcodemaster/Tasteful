package com.antimatter.tasteful;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuItemRVAdapter extends RecyclerView.Adapter<MenuItemRVAdapter.MenuItemTabHolder> {


    MenuItemRVAdapter(Context context, RecyclerView recyclerView) {
    }


    @NonNull
    @Override
    public MenuItemTabHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu_item, parent, false);

        return new MenuItemTabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemTabHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 0; //alBeaconInfo.size()
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

        MenuItemTabHolder(View itemView) {
            super(itemView);
            //civDP = itemView.findViewById(R.id.civ_dp);
        }
    }

}
