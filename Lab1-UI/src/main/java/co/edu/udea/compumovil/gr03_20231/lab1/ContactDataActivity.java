package co.edu.udea.compumovil.gr03_20231.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import co.edu.udea.compumovil.gr03_20231.lab1.dto.InformacionContactoDto;
import co.edu.udea.compumovil.gr03_20231.lab1.dto.PersonalInformationDto;

public class ContactDataActivity extends AppCompatActivity {

    AutoCompleteTextView editpais;
    AutoCompleteTextView editciudad;

    private EditText telefonoText;
    private EditText correoTxt;
    private EditText paisTxt;
    private EditText CiudadTxt;
    private EditText direccionTxt;

    String[] paises = {
            "Argentina","Bolivia","Brasil","Chile","Colombia","Costa Rica","Cuba","Ecuador","El Salvador","Guayana Francesa",
            "Granada","Guatemala","Guayana","Haití","Honduras","Jamaica","México","Nicaragua","Paraguay","Panamá",
            "Perú","Puerto Rico","República Dominicana","Surinam","Uruguay","Venezuela"
    };
    String[] ciudades = {
            "São Paulo","Buenos Aires","Bogotá","Río de Janeiro","Lima","Santiago de Chile","Caracas","Belo Horizonte","Monterrey","Guadalajara","Ciudad de Guatemala","Brasilia",
            "Porto Alegre","Fortaleza","Santo Domingo","Recife","Medellín","Salvador de Bahía","Curitiba","La Habana","Maracaibo","Guayaquil","Puerto Príncipe","Cali","Campinas",
            "Puebla","San José","Tegucigalpa","Asunción","Quito","Manaos","Goiania","San José de Los Campos","Belén","Toluca","Valencia","San Juan","Santa Cruz","San Pedro Sula",
            "San Salvador","Tijuana","Sorocaba","Barranquilla","La Paz","Managua","Victoria","León","Córdoba","Santos","Montevideo","Barquisimeto","Arroyo Negro","Panamá","San Luis",
            "Natal","Quéretaro","Maracay","Ciudad Juárez","Piracicaba","Torreón","Joinville","Bucaramanga","Cochabamba","Santiago de los Caballeros","Cartagena de Indias","Mérida",
            "Maceió","Juan Persona","San Luis de Potosí","Rosario","Teresina","Florianópolis","Kingston","Arequipa","Aguascalientes","Trujillo","Londrina","Valparaíso","Cúcuta",
            "Mexicali","Cuiabá","Concepción","Saltillo","Cuernavaca","Culiacán",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_data);

        editpais = (AutoCompleteTextView) findViewById(R.id.EditPais);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,paises);

        editpais.setThreshold(2);

        editciudad = (AutoCompleteTextView) findViewById(R.id.editCiudad);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,ciudades);

        editciudad.setThreshold(2);
    }

    private InformacionContactoDto buildinformacionContacto(){
        InformacionContactoDto InformacionContactoDto = new InformacionContactoDto();
        InformacionContactoDto.settelefono(Integer.parseInt(telefonoText.getText().toString()));
        InformacionContactoDto.setCorreo(correoTxt.getText().toString());
        InformacionContactoDto.setPais(paisTxt.getText().toString());
        InformacionContactoDto.setCiudad(paisTxt.getText().toString());
        InformacionContactoDto.setDireccion(direccionTxt.getText().toString());
        return InformacionContactoDto;
    }

    public void onClickEnviarInformacionContacto(View view){
        InformacionContactoDto informacionContactoDto = buildinformacionContacto();
    }


    public void onClickAbrirActivityPersonalData(View view) {
        Intent i = new Intent(ContactDataActivity.this, PersonalDataActivity.class);
        startActivity(i);
    }
}