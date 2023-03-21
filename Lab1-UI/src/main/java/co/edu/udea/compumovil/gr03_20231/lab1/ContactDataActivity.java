package co.edu.udea.compumovil.gr03_20231.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import co.edu.udea.compumovil.gr03_20231.lab1.dto.InformacionContactoDto;
import co.edu.udea.compumovil.gr03_20231.lab1.dto.PersonalInformationDto;

public class ContactDataActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompletePais;
    private AutoCompleteTextView autoCompleteCiudad;
    private Button btnsiguientes;
    private Button btnVolver;
    private EditText telefono;
    private EditText direccion;
    private EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_data);

        initBinding();
        initEvents();
    }

    public void initBinding() {
        btnsiguientes = findViewById(R.id.btnsiguientes2);
        telefono = findViewById(R.id.editTelefono);
        correo = findViewById(R.id.editEmail);
        autoCompletePais = findViewById(R.id.autoCompletePais);
        autoCompleteCiudad = findViewById(R.id.autoCompleteCiudad);
        direccion = findViewById(R.id.editDireccion);
        this.setTitle(R.string.titulo_contact_information_activity);
    }

    public void initEvents() {
        buildAutocompleteCiudades();
        buildAutocompletePaises();
    }

    private void buildAutocompleteCiudades() {
        List<String> ciudadesColombia = Arrays.asList(getResources().getStringArray(R.array.ciudades_colombia));
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ciudadesColombia);
        autoCompleteCiudad.setAdapter(adaptador);
    }

    private void buildAutocompletePaises() {
        List<String> paisesColombia = Arrays.asList(getResources().getStringArray(R.array.paises));
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paisesColombia);
        autoCompletePais.setAdapter(adaptador);
    }

    private InformacionContactoDto buildinformacionContacto() {
        InformacionContactoDto InformacionContactoDto = new InformacionContactoDto();
        InformacionContactoDto.setTelefono(telefono.getText().toString());
        InformacionContactoDto.setCorreo(correo.getText().toString());
        InformacionContactoDto.setPais(autoCompletePais.getText().toString());
        InformacionContactoDto.setCiudad(autoCompleteCiudad.getText().toString());
        InformacionContactoDto.setDireccion(direccion.getText().toString());
        return InformacionContactoDto;
    }

    public void onClickEnviarInformacionContacto(View view) {
        InformacionContactoDto informacionContactoDto = buildinformacionContacto();
        Boolean valid=validated(informacionContactoDto);
        if(valid){
            Log.i("Informacion Personal", "Informacion de Contacto" );
            Log.i("Informacion Personal", "Telefono:"+informacionContactoDto.getTelefono() );
            Log.i("Informacion Personal", "Dirección:"+informacionContactoDto.getDireccion() );
            Log.i("Informacion Personal", "Email:"+informacionContactoDto.getCorreo());
            Log.i("Informacion Personal", "País:"+informacionContactoDto.getPais() );
            Log.i("Informacion Personal","Ciudad:"+ informacionContactoDto.getCiudad());

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    public Boolean validated(InformacionContactoDto informacionContactoDto){
        Boolean valid=true;
        if(Objects.isNull(informacionContactoDto.getTelefono()) || informacionContactoDto.getTelefono().isEmpty()){
            Toast.makeText(this, "El campo Telefono no puede quedar vacio", Toast.LENGTH_LONG).show();
            valid=false;
        }
        if(Objects.isNull(informacionContactoDto.getCorreo()) || informacionContactoDto.getCorreo().isEmpty()){
            Toast.makeText(this, "El campo Correo no puede quedar vacio", Toast.LENGTH_LONG).show();
            valid=false;
        }
        if(Objects.isNull(informacionContactoDto.getPais()) || informacionContactoDto.getPais().isEmpty()){
            Toast.makeText(this, "El campo Pais no puede quedar vacio", Toast.LENGTH_LONG).show();
            valid=false;
        }

        return valid;

    }
}