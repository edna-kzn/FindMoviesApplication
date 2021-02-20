package com.kazarian.android.findmoviesapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class FilmViewHolder extends RecyclerView.ViewHolder {

    ImageView imgfimage;
    TextView txtfname;
    TextView txtfgenre;
    TextView txtFYear;

    public FilmViewHolder(@NonNull View itemView) {
        super(itemView);

        imgfimage = itemView.findViewById(R.id.imgFImage);
        txtfname = itemView.findViewById(R.id.txtFName);
        txtfgenre = itemView.findViewById(R.id.txtFGenre);
        txtFYear = itemView.findViewById(R.id.txtFYear);

    }
}
