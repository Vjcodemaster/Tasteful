package com.antimatter.tasteful;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.LinkedHashMap;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app_utility.OnAsyncInterfaceListener;

public class LoginActivity extends AppCompatActivity implements OnAsyncInterfaceListener {
    TextView tvNumber;
    RecyclerView recyclerView;
    Button btnSignIn;

    TextInputLayout etID;
    public TableNumberRVAdapter tableNumberRVAdapter;
    public static OnAsyncInterfaceListener onAsyncInterfaceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onAsyncInterfaceListener = this;
        recyclerView = findViewById(R.id.rv_number);
        tvNumber = findViewById(R.id.tv_number);
        btnSignIn = findViewById(R.id.btn_sign_in);
        etID = findViewById(R.id.et_id);

        LinearLayoutManager mLinearLayoutManager = new GridLayoutManager(LoginActivity.this, 5);
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sEmpID = Objects.requireNonNull(etID.getEditText()).getText().toString();
                String sTableNumber = tvNumber.getText().toString();
                if(validateFields(sEmpID, sTableNumber)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("EMP_ID", sEmpID);
                    intent.putExtra("TABLE_NUMBER", sTableNumber);
                    startActivity(intent);
                    //finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Please enter all the info", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tableNumberRVAdapter = new TableNumberRVAdapter(LoginActivity.this, recyclerView);
        recyclerView.setAdapter(tableNumberRVAdapter);

    }

    private boolean validateFields(String sID, String sTableNumber){
        if(sID.length() < 5){
            return false;
        }
        return sTableNumber.length() >= 1;
    }

    @Override
    public void onResultReceived(String sMessage, int nCase, String sResult, LinkedHashMap<String, String> lhmFoodAndQuantity) {
        switch (sMessage){
            case "NUMBER_RECEIVED":
                tvNumber.setVisibility(View.VISIBLE);
                tvNumber.setText(sResult);
                break;
        }
    }
}
