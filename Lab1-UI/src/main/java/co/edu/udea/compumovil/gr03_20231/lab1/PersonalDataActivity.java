package co.edu.udea.compumovil.gr03_20231.lab1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import co.edu.udea.compumovil.gr03_20231.lab1.dto.PersonalInformationDto;

public class PersonalDataActivity extends AppCompatActivity {
    private static final String MY_TAG = "LAB1";

    private Date fechaSelected;
    private Button btnCambiarDatePicker;
    private Spinner spinner;
    private String gradoEscolaridadSelected;
    private EditText nombresTxt;
    private EditText apellidosTxt;
    private RadioGroup sexoRadioGroup;
    private String sexoSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        initBinding();
        initEvents();
    }

    public void initBinding() {
        btnCambiarDatePicker = findViewById(R.id.btnCambiar);
        spinner = findViewById(R.id.spinnerGradoEscolaridad);
        nombresTxt = findViewById(R.id.nombresEditText);
        apellidosTxt = findViewById(R.id.apellidosEditText);
        sexoRadioGroup = findViewById(R.id.sexoRadioGroup);
        this.setTitle(R.string.titulo_informacion_personal_activity);
    }

    public void initEvents() {
        onSelectSpinner();
        onCheckedChangeSexo();
    }

    public void onSelectSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grados_escolaridad, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gradoEscolaridadSelected = spinner.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onOnclickDatePicker(View views) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                PersonalDataActivity.this,
                (view, year1, month1, dayOfMonth) -> {
                    Calendar fechaCalendario = Calendar.getInstance();
                    fechaCalendario.set(year1, month1, dayOfMonth);
                    fechaSelected = fechaCalendario.getTime();
                },
                year, month, day);
        datePickerDialog.show();
    }

    public void onCheckedChangeSexo() {
        sexoRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            View radioButton = radioGroup.findViewById(i);
            sexoSelected = ((MaterialRadioButton) radioButton).getText().toString();
        });
    }

    private PersonalInformationDto buildInformacionPersonal() {
        PersonalInformationDto personalInformationDto = new PersonalInformationDto();
        personalInformationDto.setNombres(nombresTxt.getText().toString());
        personalInformationDto.setApellidos(apellidosTxt.getText().toString());
        personalInformationDto.setSexo(sexoSelected);
        personalInformationDto.setFechaNacimiento(fechaSelected);
        personalInformationDto.setGradoEscolaridad(gradoEscolaridadSelected);
        return personalInformationDto;
    }

    public void onClickAbrirActivityInformacionContacto(View view) {
        PersonalInformationDto personalInformationDto = buildInformacionPersonal();
        Boolean valid = validated(personalInformationDto);
        formatoCampos(personalInformationDto);
        if (valid) {
            Log.i("Informacion Personal", "Informacion Personal");
            Log.i("Informacion Personal", "Nombres:" + personalInformationDto.getNombres());
            Log.i("Informacion Personal", "Apellidos:" + personalInformationDto.getApellidos());
            Log.i("Informacion Personal", "Sexo:" + personalInformationDto.getSexo());
            Log.i("Informacion Personal", "Fecha de Nacimiento:" + convertToLocalDateViaInstant(personalInformationDto.getFechaNacimiento()).toString());
            Log.i("Informacion Personal", "Grado Escolaridad:" + personalInformationDto.getGradoEscolaridad());
            Intent i = new Intent(PersonalDataActivity.this, ContactDataActivity.class);
            startActivity(i);
        }
    }

    public Boolean validated(PersonalInformationDto personalInformationDto) {
        Boolean valid = true;
        if (Objects.isNull(personalInformationDto.getNombres()) || personalInformationDto.getNombres().isEmpty()) {
            Toast.makeText(this, "El campo Nombres no puede quedar vacio", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if (Objects.isNull(personalInformationDto.getApellidos()) || personalInformationDto.getApellidos().isEmpty()) {
            Toast.makeText(this, "El campo Apellidos no puede quedar vacio", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if (Objects.isNull(personalInformationDto.getFechaNacimiento()) || personalInformationDto.getFechaNacimiento().toString().isEmpty()) {
            Toast.makeText(this, "El campo Fecha de Nacimiento no puede quedar vacio", Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;
    }

    public void formatoCampos(PersonalInformationDto personalInformationDto) {
        if (personalInformationDto.getGradoEscolaridad().equals("Grado de escolaridad")) {
            personalInformationDto.setGradoEscolaridad("");
        }
        if (Objects.isNull(personalInformationDto.getSexo())) {
            personalInformationDto.setSexo("");
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}