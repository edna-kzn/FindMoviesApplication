package com.kazarian.android.findmoviesapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FilmRecyclerAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    ArrayList<String> fname, fjenre, fyear, fimage;
    ArrayList<String> fimdbid;  //ImdbID


    public FilmRecyclerAdapter(ArrayList<String> fnames,
                               ArrayList<String> fyears,
                               ArrayList<String> fjenres,
                               ArrayList<String> fimages,
                               ArrayList<String> fimdbids){
        this.fname = fnames;
        this.fyear = fyears;
        this.fjenre = fjenres;
        this.fimage = fimages;
        this.fimdbid = fimdbids;

    }

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
        holder.txtfgenre.setText("Genre: " + fjenre.get(position).toString());
        holder.txtFYear.setText("Year: " + fyear.get(position).toString());
        Picasso.get().load(fimage.get(position)).fit().into(holder.imgfimage);

        //if you click on Movie name on recycler view item then
        //we send that movies's Id to RecyclerItemDetailActivity to show
        holder.txtfname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String currentItemId = fimdbid.get(position).toString();

                Intent intent = new Intent(v.getContext(), RecyclerItemDetailActivity.class);
                intent.putExtra("ifId", currentItemId);
                //intent.putExtra("ifgenre", CurrentItemGenre);
                v.getContext().startActivity(intent);

            }
        });


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
