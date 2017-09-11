package com.map.lindelani.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout hotelLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }
    public void addListenerOnButton(){

        final Context context=this;
        hotelLinear = (LinearLayout) findViewById(R.id.hotel);

        hotelLinear.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view){

                Intent intent = new Intent(context, Hotel.class);
                startActivity(intent);

            }

        });

        //button 2

    }
}
