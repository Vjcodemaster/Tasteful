package com.antimatter.tasteful;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import app_utility.OnAsyncInterfaceListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link app_utility.OnAsyncInterfaceListener} interface
 * to handle interaction events.
 * Use the {@link BevaragesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BevaragesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    RecyclerView recyclerView;
    public MenuItemRVAdapter menuItemRVAdapter;
    TextView tvMenuItem, tvQuantity, tvPrice;

    private String mParam1;
    private String mParam2;

    private OnAsyncInterfaceListener mListener;

    public BevaragesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BevaragesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BevaragesFragment newInstance(String param1, String param2) {
        BevaragesFragment fragment = new BevaragesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bevarages, container, false);
        recyclerView = view.findViewById(R.id.rv_menu_item_list);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/quesha.ttf");
        tvMenuItem = view.findViewById(R.id.tv_menu_item_header);
        tvQuantity = view.findViewById(R.id.tv_quantity_header);
        tvPrice = view.findViewById(R.id.tv_price_header);

        tvMenuItem.setTypeface(font);
        tvQuantity.setTypeface(font);
        tvPrice.setTypeface(font);
        tvMenuItem.setTextSize(26);
        tvQuantity.setTextSize(26);
        tvPrice.setTextSize(26);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        ArrayList<String> alBeverageItems = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.beverage_items)));
        ArrayList<String> alBeveragePrice = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.beverage_price)));
        menuItemRVAdapter = new MenuItemRVAdapter(getActivity(), recyclerView, alBeverageItems, alBeveragePrice, 2, MainActivity.dataStorage);
        recyclerView.setAdapter(menuItemRVAdapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAsyncInterfaceListener) {
            mListener = (OnAsyncInterfaceListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
