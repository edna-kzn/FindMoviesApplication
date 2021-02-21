package com.kazarian.android.findmoviesapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmRecyclerAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    ArrayList<String> fname, fjenre, fyear, fimage;
    ArrayList<Double> fimdbid;
    private RecyclerViewClickInterface mRecyclerViewClickInterface;


    public FilmRecyclerAdapter(ArrayList<String> fnames,
                               ArrayList<String> fyears,
                               ArrayList<String> fjenres,
                               ArrayList<String> fimages/*,
                               RecyclerViewClickInterface recyclerViewClickInterface*/){
        this.fname = fnames;
        this.fyear = fyears;
        this.fjenre = fjenres;
        this.fimage = fimages;
        //this.mRecyclerViewClickInterface = recyclerViewClickInterface;

    }

   /* public FilmRecyclerAdapter(ArrayList<String> fnames, ArrayList<String> fyears, ArrayList<String> fjenres, ArrayList<String> fimages, RecyclerViewClickInterface recyclerViewClickInterface, JsonHttpResponseHandler jsonHttpResponseHandler) {

    }
*/
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
        holder.txtfname.setText("Title: " + fname.get(position).toString());
        holder.txtfgenre.setText("Type: " + fjenre.get(position).toString());
        holder.txtFYear.setText("Year: " + fyear.get(position).toString());
        Picasso.get().load(fimage.get(position)).fit().into(holder.imgfimage);

    }

    @Override
    public int getItemCount() {
        //tedad film haye peyda shode dar search ya darvaghe saze listi ke az natije search name film por shode
        int i = 0;
        if (fname != null)
            i = fname.size();
        return i;
    }
}
