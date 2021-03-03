package com.kazarian.android.findmoviesapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestViewHolder> {

    ArrayList<String> fname, fjenre, fyear, fimage;
    ArrayList<String> fimdbid;  //ImdbID
    String test1, test2, test3;


    public TestRecyclerAdapter(ArrayList<String> fnames,
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
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_recycler_item, parent, false);
        TestViewHolder holder = new TestViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        //vaghti data ra az internet khandim va dar list gozashtim bad in ghesmat neveshte shavad

        test2 = "janre action";
        test1 = fname.get(position).toString();

        test3 = fyear.get(position).toString();


        holder.txtfname.setText("Title: " + test1);
        holder.txtfgenre.setText("Type: " + test2);
        holder.txtFYear.setText("Year: " + test3);
        //Picasso.get().load(fimage.get(position)).fit().into(holder.imgfimage);

        //if you click on Movie name on recycler view item then
        //we send that movies's Id to RecyclerItemDetailActivity to show
        holder.txtfname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String currentItemId = fimdbid.get(position).toString();

                /*String CurrentItemName = fname.get(position).toString();
                String CurrentItemGenre = fjenre.get(position).toString();
                String CurrentItemYear = fyear.get(position).toString();
                String CurrentItemImage = fimage.get(position).toString();*/

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
