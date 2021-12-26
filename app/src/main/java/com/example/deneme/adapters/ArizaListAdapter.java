package com.example.deneme.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deneme.ArizaKapatActivity;
import com.example.deneme.R;
import com.example.deneme.entity.ArizaBildirim;

import java.util.List;

public class ArizaListAdapter extends RecyclerView.Adapter<ArizaListAdapter.ViewHolder> {

    private List<ArizaBildirim> arizaList;
    View.OnClickListener listener;
    public ArizaListAdapter(List<ArizaBildirim> arizaListAdapters, View.OnClickListener listener){
        this.arizaList = arizaListAdapters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ariza_listesi_tasarim, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       ArizaBildirim ab = arizaList.get(position);
        holder.getArizabildiren().setText(ab.getPersonelad());
        holder.getArizalistaciklama().setText(
                ab.getArizatanimi().length()>33 ? ab.getArizatanimi().substring(0,33)+"..."
                        : ab.getArizatanimi()
        );
        holder.getArizalistoncelik().setText(ab.getOncelik());
        holder.getArizalisttur().setText(ab.getArizabildirimsekli());
        holder.getArizalisttarihsaat().setText(ab.getTarih());
        holder.getArizalistbtncoz().setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return arizaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView arizabildiren;
        private TextView arizalisttur;
        private TextView arizalistoncelik;
        private TextView arizalistaciklama;
        private TextView arizalisttarihsaat;
        private Button arizalistbtncoz;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            arizabildiren = itemView.findViewById(R.id.arizalistbildiren);
            arizalisttarihsaat = itemView.findViewById(R.id.arizalisttarihsaat);
            arizalistaciklama = itemView.findViewById(R.id.arizalistaciklama);
            arizalistoncelik= itemView.findViewById(R.id.arizalistoncelik);
            arizalisttur = itemView.findViewById(R.id.arizalisttur);
            arizalistbtncoz = itemView.findViewById(R.id.arizalistbtncoz);
        }

        public Button getArizalistbtncoz() {
            return arizalistbtncoz;
        }

        public TextView getArizabildiren() {
            return arizabildiren;
        }

        public TextView getArizalisttur() {
            return arizalisttur;
        }

        public TextView getArizalistoncelik() {
            return arizalistoncelik;
        }

        public TextView getArizalistaciklama() {
            return arizalistaciklama;
        }

        public TextView getArizalisttarihsaat() {
            return arizalisttarihsaat;
        }
    }
}
