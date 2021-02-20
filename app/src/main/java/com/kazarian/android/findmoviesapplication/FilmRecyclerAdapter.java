package com.kazarian.android.findmoviesapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilmRecyclerAdapter extends RecyclerView.Adapter<FilmViewHolder> {
    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        FilmViewHolder holder = new FilmViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        //vaghti data ra az internet khandim va dar list gozashtim bad in ghesmat neveshte shavad
    }

    @Override
    public int getItemCount() {
        //tedad film haye peyda shode dar search ya darvaghe saze listi ke natije search name film por shode
        return 0;
    }
}
