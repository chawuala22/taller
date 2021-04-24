package com.unimagdalena.optativa.taller.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.unimagdalena.optativa.taller.R;
import com.unimagdalena.optativa.taller.UpdateInfoContador;
import com.unimagdalena.optativa.taller.db.DBHelper;
import com.unimagdalena.optativa.taller.model.InfoContador;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        final InfoContador infoContador = infoContadores.get(position);
        holder.tvId.setText(String.valueOf(infoContador.getId()));
        holder.tvBarrio.setText(infoContador.getBarrio());
        holder.tvDireccion.setText(infoContador.getDireccion());
        holder.tvValor.setText(String.valueOf(infoContador.getValor()));
        holder.tvContador.setText(infoContador.getNameSpTipo());

        DateFormat dateF = DateFormat.getDateTimeInstance();
        String data = dateF.format(new Date(infoContador.getFecha_creacion()));
        holder.tvFechaCreacion.setText(data);

        holder.cardUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, UpdateInfoContador.class);
                intent.putExtra("INFO_CONTADOR", infoContador);
                context.startActivity(intent);
            }
        });

        holder.cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Esta a punto de borrar!!!!!");
                builder.setMessage("¿Esta seguro que quiere borrar?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DBHelper dbHelper = new DBHelper(context);
                        int result = dbHelper.deleteInfoContador(infoContador.getId());

                        if(result>0){
                            Toast.makeText(context, "Borrado con éxito", Toast.LENGTH_SHORT).show();
                            infoContadores.remove(infoContador);
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return infoContadores.size();
    }

    public void find(List<InfoContador> contenido) {
        infoContadores= new ArrayList<>();
        infoContadores.addAll(contenido);
        notifyDataSetChanged();
    }

    class InfoContadorVH extends RecyclerView.ViewHolder {

        TextView tvBarrio, tvDireccion, tvValor, tvFechaCreacion, tvContador, tvId;
        CardView cardUpdate, cardDelete;

        public InfoContadorVH(@NonNull View v) {
            super(v);

            tvId = v.findViewById(R.id.tvId);
            tvBarrio = v.findViewById(R.id.tvBarrio);
            tvDireccion = v.findViewById(R.id.tvDireccion);
            tvValor = v.findViewById(R.id.tvValor);
            tvFechaCreacion = v.findViewById(R.id.tvFechaCreacion);
            tvContador = v.findViewById(R.id.tvContador);

            cardUpdate = v.findViewById(R.id.cardUpdate);
            cardDelete = v.findViewById(R.id.cardDelete);
        }
    }
}
