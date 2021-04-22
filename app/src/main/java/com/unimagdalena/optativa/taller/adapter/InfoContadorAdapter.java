package com.unimagdalena.optativa.taller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.unimagdalena.optativa.taller.R;
import com.unimagdalena.optativa.taller.model.InfoContador;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InfoContadorAdapter extends RecyclerView.Adapter<InfoContadorAdapter.InfoContadorVH> {

    ArrayList<InfoContador> infoContadores;
    Context context;

    public InfoContadorAdapter(ArrayList<InfoContador> infoContadores, Context context) {
        this.infoContadores = infoContadores;
        this.context = context;
    }

    @NonNull
    @Override
    public InfoContadorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_info_contador, parent, false);
        InfoContadorVH ivh = new InfoContadorVH(view);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoContadorVH holder, int position) {
        InfoContador infoContador = infoContadores.get(position);
        holder.tvBarrio.setText(infoContador.getBarrio());
        holder.tvDireccion.setText(infoContador.getDireccion());
        holder.tvValor.setText(String.valueOf(infoContador.getValor()));

        DateFormat dateF = DateFormat.getDateTimeInstance();
        String data = dateF.format(new Date(infoContador.getFecha_creacion()));
        holder.tvFechaCreacion.setText(data);

        holder.cardUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, infoContador.getBarrio() + "Se va actualizar", Toast.LENGTH_LONG);
            }
        });

        holder.cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, infoContador.getBarrio() + "Se va eliminar", Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoContadores.size();
    }

    class InfoContadorVH extends RecyclerView.ViewHolder {

        TextView tvBarrio, tvDireccion, tvValor, tvFechaCreacion;
        CardView cardUpdate, cardDelete;

        public InfoContadorVH(@NonNull View v) {
            super(v);

            tvBarrio = v.findViewById(R.id.tvBarrio);
            tvDireccion = v.findViewById(R.id.tvDireccion);
            tvValor = v.findViewById(R.id.tvValor);
            tvFechaCreacion = v.findViewById(R.id.tvFechaCreacion);

            cardUpdate = v.findViewById(R.id.cardUpdate);
            cardDelete = v.findViewById(R.id.cardDelete);
        }
    }
}
