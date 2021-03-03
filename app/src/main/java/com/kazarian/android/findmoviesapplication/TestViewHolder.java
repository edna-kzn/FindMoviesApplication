package com.kazarian.android.findmoviesapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestViewHolder extends RecyclerView.ViewHolder {

    ImageView imgfimage;
    TextView txtfname;
    TextView txtfgenre;
    TextView txtFYear;
    LinearLayout llitemlayout;



    public TestViewHolder(@NonNull View itemView) {
        super(itemView);

        //imgfimage = itemView.findViewById(R.id.);
        txtfname = itemView.findViewById(R.id.txtFName2);
        txtfgenre = itemView.findViewById(R.id.txtFGenre2);
        txtFYear = itemView.findViewById(R.id.txtFYear2);
        //llitemlayout = itemView.findViewById(R.id.llItemlayout);

    }


}
