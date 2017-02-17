package com.example.rathin.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class landing_page extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSales;
    private CardView cvAttendance,cvSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        cvAttendance = (CardView) findViewById(R.id.cvAttendance);
        cvSales=(CardView)findViewById(R.id.cvSales);
        cvSales.setOnClickListener(this);
        cvAttendance.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==cvAttendance)
        {
            Intent i = new Intent(landing_page.this,tabbed_attendance.class);
            startActivity(i);

        }

        if(v== cvSales)
        {
            Intent isales = new Intent(landing_page.this,SalesStockNav.class);
            startActivity(isales);
        }
    }
}
