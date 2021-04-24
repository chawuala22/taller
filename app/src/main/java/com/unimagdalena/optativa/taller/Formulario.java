package com.unimagdalena.optativa.taller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unimagdalena.optativa.taller.adapter.InfoContadorAdapter;
import com.unimagdalena.optativa.taller.db.DBHelper;
import com.unimagdalena.optativa.taller.model.InfoContador;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Formulario extends AppCompatActivity {

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
        searchView= findViewById(R.id.filterbar);
        loadSuggestList();
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

    private void loadSuggestList(){


    }
}