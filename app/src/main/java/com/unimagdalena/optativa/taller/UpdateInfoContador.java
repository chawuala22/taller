package com.unimagdalena.optativa.taller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.unimagdalena.optativa.taller.db.DBHelper;
import com.unimagdalena.optativa.taller.model.InfoContador;

public class UpdateInfoContador extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText edBarrio, edDireccion, edValor;
    Spinner sptipo;
    String tipo = "";
    long tipoId=0;
    CardView btnUpdate;
    int id;
    long idActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_contador);

        InfoContador infoContador = (InfoContador) getIntent().getExtras().getSerializable("INFO_CONTADOR");
        id = infoContador.getId();
        idActual = infoContador.getIdSpTipo();
        edBarrio = findViewById(R.id.barrio);
        edDireccion = findViewById(R.id.direccion);
        edValor = findViewById(R.id.valor);

        btnUpdate = findViewById(R.id.btnUpdate);
        sptipo = findViewById(R.id.TContadorUpdate);
        spinner();
        edBarrio.setText(infoContador.getBarrio());
        edDireccion.setText(infoContador.getDireccion());
        edValor.setText(String.valueOf(infoContador.getValor()));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                Intent intent = new Intent(getApplicationContext(), Formulario.class);
                startActivity(intent);
            }
        });
    }

    private void spinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptipo.setAdapter(adapter);
        sptipo.setOnItemSelectedListener(this);
    }

    public void update() {
        String barrio = edBarrio.getText().toString();
        String direccion = edDireccion.getText().toString();
        Integer valor = Integer.parseInt(edValor.getText().toString());

        InfoContador infoContador = new InfoContador(id, barrio, direccion, valor, tipo, tipoId);

        DBHelper dbHelper = new DBHelper(this);

        int result = dbHelper.updateInfoContador(infoContador);

        if (result > 0) {
            Toast.makeText(this, "Actualizado con éxito", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Failed" + result, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        tipo= parent.getItemAtPosition(position).toString();
        tipoId = parent.getSelectedItemId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}