package co.edu.udea.compumovil.gr03_20231.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import co.edu.udea.compumovil.gr03_20231.lab1.dto.InformacionContactoDto;

public class ContactDataActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompletePais;
    private AutoCompleteTextView autoCompleteCiudad;
    private EditText telefonoText;
    private EditText correoTxt;
    private EditText direccionTxt;
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
        InformacionContactoDto.setTelefono(Integer.parseInt(telefonoText.getText().toString()));
        InformacionContactoDto.setCorreo(correoTxt.getText().toString());
        InformacionContactoDto.setPais(autoCompletePais.getText().toString());
        InformacionContactoDto.setCiudad(autoCompleteCiudad.getText().toString());
        InformacionContactoDto.setDireccion(direccionTxt.getText().toString());
        return InformacionContactoDto;
    }

    public void onClickEnviarInformacionContacto(View view) {
        InformacionContactoDto informacionContactoDto = buildinformacionContacto();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}