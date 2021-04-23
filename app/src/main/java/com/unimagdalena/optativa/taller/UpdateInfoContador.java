package com.unimagdalena.optativa.taller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unimagdalena.optativa.taller.db.DBHelper;
import com.unimagdalena.optativa.taller.model.InfoContador;

public class UpdateInfoContador extends AppCompatActivity {
    EditText edBarrio, edDireccion, edValor;
    Button btnUpdate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_contador);

        InfoContador infoContador = (InfoContador) getIntent().getExtras().getSerializable("INFO_CONTADOR");
        id = infoContador.getId();
        edBarrio = findViewById(R.id.barrio);
        edDireccion = findViewById(R.id.direccion);
        edValor = findViewById(R.id.valor);

        btnUpdate = findViewById(R.id.btnUpdate);

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

    public void update() {
        String barrio = edBarrio.getText().toString();
        String direccion = edDireccion.getText().toString();
        Integer valor = Integer.parseInt(edValor.getText().toString());

        InfoContador infoContador = new InfoContador(id, barrio, direccion, valor);

        DBHelper dbHelper = new DBHelper(this);

        int result = dbHelper.updateInfoContador(infoContador);

        if (result > 0) {
            Toast.makeText(this, "Update" + result, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Failed" + result, Toast.LENGTH_LONG).show();
        }

    }
}