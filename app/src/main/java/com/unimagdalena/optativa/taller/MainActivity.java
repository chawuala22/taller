package com.unimagdalena.optativa.taller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText etbarrio,etdireccion,etvalor;
    Spinner sptipo;
    TextView tvfevha;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizador();
        spinner();
        btnsave.setOnClickListener(this);
    }

    private void initalizador() {
        etbarrio =findViewById(R.id.barrio);
        etdireccion=findViewById(R.id.direccion);
        etvalor=findViewById(R.id.valor);
        sptipo=findViewById(R.id.TContador);
        tvfevha=findViewById(R.id.hora);
        btnsave=findViewById(R.id.btnsaveinfo);

    }

    private void spinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptipo.setAdapter(adapter);
        sptipo.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getApplicationContext(),Formulario.class);
        startActivity(intent);


    }
}