package com.example.skylite.Data;

import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.skylite.Activities.ConstellationInDepthView;
import com.example.skylite.Model.ModelConstellationList;
import com.squareup.picasso.Picasso;
import com.example.skylite.R;

/**
 * Kelsey Osos
 * This is the adapter for the Constellation Wiki RecyclerView
 */
public class ConstellationListAdapter extends RecyclerView.Adapter<ConstellationListAdapter.ConstellationListViewHolder> {

    private List<ModelConstellationList> ConstellationList;
    private Context context;

    public class ConstellationListViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, longdesc;
        public ImageView imageView;
        public CardView cv;
        public RelativeLayout relativeLayout;

        public ConstellationListViewHolder(View view) {
            super(view);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            name = (TextView) view.findViewById(R.id.constellationTitle);
            description = (TextView) view.findViewById(R.id.constellationDescription);
            //longdesc = (TextView) view.findViewById(R.id.constellationDescriptionlong);
            imageView = (ImageView) view.findViewById(R.id.constellationImage);

        }
    }


    public ConstellationListAdapter(List<ModelConstellationList> ConstellationList) {

        this.ConstellationList = ConstellationList;

    }

    @Override
    public ConstellationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_constellation_info_list_item, parent, false);
        context = parent.getContext();
        return new ConstellationListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ConstellationListViewHolder holder, int position) {

        ModelConstellationList constellation = ConstellationList.get(position);
        holder.name.setText(constellation.getTitle());
        holder.description.setText(constellation.getDescriptionShort());

        int resID = holder.imageView.getResources().getIdentifier(constellation.getImageName(), "drawable", context.getPackageName());
        Picasso.get().load(resID).fit().into(holder.imageView);
        //Picasso.get().load(resID).into(holder.imageView);
        //Log.d("myTag", "This is my message="+constellation.getImageName());
        holder.cv.setOnClickListener(v -> { //lambda

            Intent intent = new Intent(v.getContext(), ConstellationInDepthView.class);
            intent.putExtra("intVariableName", position);
            v.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return ConstellationList.size();
    }
}

