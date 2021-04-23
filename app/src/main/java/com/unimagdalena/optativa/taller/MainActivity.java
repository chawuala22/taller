package com.unimagdalena.optativa.taller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.unimagdalena.optativa.taller.db.DBHelper;
import com.unimagdalena.optativa.taller.model.InfoContador;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText edBarrio, edDireccion, edValor;
    Spinner sptipo;
    TextView tvfevha;
    Button btnsave;
    String tipo = "";
    long tipoId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizador();
        spinner();
        btnsave.setOnClickListener(this);
    }

    private void initalizador() {
        edBarrio = findViewById(R.id.barrio);
        edDireccion = findViewById(R.id.direccion);
        edValor = findViewById(R.id.valor);
        sptipo = findViewById(R.id.TContador);
        tvfevha = findViewById(R.id.hora);
        btnsave = findViewById(R.id.btnsaveinfo);

    }

    private void save() {
        String barrio = edBarrio.getText().toString();
        String direccion = edDireccion.getText().toString();
        Integer valor = Integer.parseInt(edValor.getText().toString());
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        InfoContador infoContador = new InfoContador(barrio, direccion, valor);

        long result = dbHelper.addInfoContador(infoContador);

        if (result > 0) {
            Toast.makeText(this, "Saved" + result, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Failed" + result, Toast.LENGTH_LONG).show();
        }
    }

    private void spinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptipo.setAdapter(adapter);
        sptipo.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipo= parent.getItemAtPosition(position).toString();
        tipoId = parent.getSelectedItemId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        save();
        Intent intent = new Intent(getApplicationContext(), Formulario.class);
        startActivity(intent);
    }
}