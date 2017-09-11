package com.map.lindelani.map;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Hotel extends AppCompatActivity {

    EditText mDateEditText;
    Calendar mCurrentDate;
    EditText mDateEditText2;
    Calendar mCurrentDate2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel);
        setTitle("Search Hotel");

        //START CALENDAR

        mDateEditText=(EditText)findViewById(R.id.txtDateEntered);
        mDateEditText2=(EditText)findViewById(R.id.txtDateEntered2);


        mDateEditText.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                mCurrentDate = Calendar.getInstance();
                int year = mCurrentDate.get(Calendar.YEAR);
                int month = mCurrentDate.get(Calendar.MONTH);
                int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(Hotel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectDay, int selectMonth, int selectYear) {
                        mDateEditText.setText(selectDay + "-" + selectMonth + "-" + selectYear);
                        mCurrentDate.set(selectYear, selectMonth, selectDay);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

        mDateEditText2.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                mCurrentDate2 = Calendar.getInstance();
                int year = mCurrentDate2.get(Calendar.YEAR);
                int month = mCurrentDate2.get(Calendar.MONTH);
                int day = mCurrentDate2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(Hotel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectDay, int selectMonth, int selectYear) {
                        mDateEditText2.setText(selectDay + "-" + selectMonth + "-" + selectYear);
                        mCurrentDate2.set(selectYear, selectMonth, selectDay);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });
    }

    public void openMap(View v){
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);
    }


}
