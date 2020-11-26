package com.example.apprate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText edtDate;
    EditText edtTime;
    Button rateBtn;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        edtDate = (EditText) findViewById(R.id.editTextDate);
        edtTime = (EditText) findViewById(R.id.editTextTime);
        rateBtn = (Button) findViewById(R.id.buttonRate);
        // adding validation to edit text using regex
        awesomeValidation.addValidation(this, R.id.editTextOwner, "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", R.string.your_name_err);
        awesomeValidation.addValidation(this, R.id.editTextPhone, "(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))\\s*[)]?[-\\s\\.]?[(]?[0-9]{1,3}[)]?([-\\s\\.]?[0-9]{3})([-\\s\\.]?[0-9]{3,4})", R.string.phone_err);
        awesomeValidation.addValidation(this, R.id.editTextRes_address, "^\\d+\\s[A-z]+\\s[A-z]+", R.string.res_address_err);
        awesomeValidation.addValidation(this, R.id.editTextRes_name, "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", R.string.res_name_err);
        awesomeValidation.addValidation(this, R.id.editTextDate, "(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d", R.string.date_err);
        awesomeValidation.addValidation(this, R.id.editTextTime, "^([01]?\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", R.string.time_err);
        awesomeValidation.addValidation(this, R.id.editTextPrice, "^\\$?([0-9]{1,3},([0-9]{3},)*[0-9]{3}|[0-9]+)(.[0-9][0-9])?$", R.string.price_err);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    GetDate();
            }
        });
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                GetTime();
            }
        });
        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFormRated();
            }
        });
    }
    private  void GetDate(){ // show calendar
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));

            }
        }, year, month, day);
        datePickerDialog.show();
    }
    private void GetTime(){ // show clock
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0, 0, 0, i, i1);
                edtTime.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
    private void submitFormRated(){ //
        if (awesomeValidation.validate()){
            Toast.makeText(this, "Validattion Successfull", Toast.LENGTH_LONG).show();
        }
    }
}