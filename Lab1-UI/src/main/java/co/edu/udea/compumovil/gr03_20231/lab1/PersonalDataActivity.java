package co.edu.udea.compumovil.gr03_20231.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class PersonalDataActivity extends AppCompatActivity {
    private static final String MY_TAG = "LAB1";
    private Date fechaSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        setListeners();
    }

    public void setListeners() {
        Button btnCambiarDatePicker = findViewById(R.id.btnCambiar);
        btnCambiarDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        PersonalDataActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar fechaCalendario = Calendar.getInstance();
                                fechaCalendario.set(year, month, dayOfMonth);
                                fechaSeleccionada = fechaCalendario.getTime();
                                Log.i(MY_TAG, "Fecha seleccionada = "+fechaSeleccionada);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        Log.i(MY_TAG, "Fecha seleccionada = "+fechaSeleccionada);


    }
}