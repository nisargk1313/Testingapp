package com.example.rathin.testing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class StockandSales extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private static final String[] paths = {"Lenovo", "Dell", "HP 3"};
    private Button btBackLap,btAdd;
    private EditText etModelnum,etStock,etPrice;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockand_sales);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(StockandSales.this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btBackLap=(Button)findViewById(R.id.btBackLap);
        spinner.setAdapter(adapter);
        btBackLap.setOnClickListener(this);
        etModelnum=(EditText)findViewById(R.id.etModelnum);
        etPrice=(EditText)findViewById(R.id.etPrice);
        etStock=(EditText)findViewById(R.id.etStock);
        btAdd=(Button)findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog1 = new ProgressDialog(this);





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                    {
                        

                    }
                        break;
                    case 1:
                    {


                    }
                    break;
                    case 2:
                    {

                    }
                    break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void AddStockDetails() {
        progressDialog1.setMessage("Adding Your Data.");
        progressDialog1.show();
        String modelnu = etModelnum.getText().toString().trim();
        String stock = etStock.getText().toString().trim();
        String price = etPrice.getText().toString().trim();
        // String name= etName.getText().toString().trim();
        // String number=etMobile.getText().toString().trim();


        AddStock addStock = new AddStock(modelnu, stock, price);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(addStock);
        progressDialog1.dismiss();
    }
    @Override
    public void onClick(View v) {
        if(v== btBackLap)
        {
            Intent Ref = new Intent(StockandSales.this,SalesStockNav.class);
            startActivity(Ref);
        }
        if(v==btAdd)
        {
           AddStockDetails();

        }
    }
}