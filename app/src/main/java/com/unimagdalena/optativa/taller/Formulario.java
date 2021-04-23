package com.unimagdalena.optativa.taller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.unimagdalena.optativa.taller.adapter.InfoContadorAdapter;
import com.unimagdalena.optativa.taller.db.DBHelper;
import com.unimagdalena.optativa.taller.model.InfoContador;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Formulario extends AppCompatActivity {

    TextView tvTotal;
    RecyclerView recyclerView;
    InfoContadorAdapter infoContadorAdapter;
    ArrayList<InfoContador> infoContadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        recyclerView = findViewById(R.id.recyclerView);
        tvTotal = findViewById(R.id.tvTotal);

        DBHelper dbHelper = new DBHelper(this);

        infoContadores = dbHelper.getInfoContadores();
        tvTotal.setText("Total de contadores con informacion" + infoContadores.size());

        infoContadorAdapter = new InfoContadorAdapter(infoContadores, this);
        recyclerView.setAdapter(infoContadorAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}