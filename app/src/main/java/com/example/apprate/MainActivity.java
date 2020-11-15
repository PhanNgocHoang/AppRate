package com.example.apprate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtDate;
    EditText edtTime;
    Spinner service_rate;
    Spinner food_rate;
    Spinner clean_rate;
    Spinner res_type;
    EditText owner;
    EditText owner_phone;
    EditText res_address;
    EditText res_name;
    EditText notes;
    Button rateBtn;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        edtDate = (EditText) findViewById(R.id.editTextDate);
        edtTime = (EditText) findViewById(R.id.editTextTime);
        service_rate = (Spinner) findViewById(R.id.spinnerService_rate);
        food_rate = (Spinner) findViewById(R.id.spinnerFood_rate);
        clean_rate = (Spinner) findViewById(R.id.spinnerClean_rate);
        res_type = (Spinner) findViewById(R.id.spinnerRes_type);
        owner = (EditText) findViewById(R.id.editTextOwner);
        owner_phone = (EditText) findViewById(R.id.editTextPhone);
        res_address = (EditText) findViewById(R.id.editTextRes_address);
        res_name = (EditText) findViewById(R.id.editTextRes_name);
        notes = (EditText) findViewById(R.id.editTextNotes);
        rateBtn = (Button) findViewById(R.id.buttonRate);



        // adding validation to edit text
        awesomeValidation.addValidation(this, R.id.editTextOwner, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.your_name_err);
        awesomeValidation.addValidation(this, R.id.editTextPhone, "^[0-9]{10}$", R.string.phone_err);
        awesomeValidation.addValidation(this, R.id.editTextRes_address, "^\\d+\\s[A-z]+\\s[A-z]+", R.string.res_address_err);
        awesomeValidation.addValidation(this, R.id.editTextRes_name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.res_name_err);
        awesomeValidation.addValidation(this, R.id.editTextDate, "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$", R.string.date_err);
        awesomeValidation.addValidation(this, R.id.editTextTime, "^([01]?\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", R.string.time_err);


        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    selectDate();
            }
        });
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                selectTime();
            }
        });
        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }
    private  void selectDate(){
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));

            }
        }, year, month, day);
        datePickerDialog.show();
    }
    private void selectTime(){
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
    private void submitForm(){
        if (awesomeValidation.validate()){
            Toast.makeText(this, "Validattion Successfull", Toast.LENGTH_LONG).show();
        }
    }
}