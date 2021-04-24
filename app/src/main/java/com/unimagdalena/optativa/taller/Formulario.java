package com.unimagdalena.optativa.taller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unimagdalena.optativa.taller.adapter.InfoContadorAdapter;
import com.unimagdalena.optativa.taller.db.DBHelper;
import com.unimagdalena.optativa.taller.model.InfoContador;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formulario extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView searchView;
    RecyclerView recyclerView;
    InfoContadorAdapter infoContadorAdapter;
    ArrayList<InfoContador> infoContadores;
    FloatingActionButton btnnext;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        btnnext= findViewById(R.id.btn_new);
        recyclerView = findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.filterbar);

        searchView.setOnQueryTextListener(this);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper(this);

        infoContadores = dbHelper.getInfoContadores();

        infoContadorAdapter = new InfoContadorAdapter(infoContadores, this);
        recyclerView.setAdapter(infoContadorAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<InfoContador> contenido= new ArrayList<>();
        for(InfoContador infoContador: infoContadores){
            String direccion = infoContador.getDireccion().toLowerCase();
            String tipo = infoContador.getNameSpTipo().toLowerCase();
            if(direccion.contains(newText)|| tipo.contains(newText)){
            contenido.add(infoContador);
            }
        }
        infoContadorAdapter.find(contenido);
        return false;
    }
}