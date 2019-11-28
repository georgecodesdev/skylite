package com.example.skylite.Data;

import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.R;
import com.squareup.picasso.Picasso;

public class ConstellationListAdapter extends RecyclerView.Adapter<ConstellationListAdapter.ConstellationListViewHolder> {

    private List<ModelConstellationInfo> ConstellationList;


    public class ConstellationListViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description,longdesc;
        public ImageView imageView;
        public  RelativeLayout relativeLayout;

        public ConstellationListViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.constellationTitle);
            description = (TextView) view.findViewById(R.id.constellationDescription);
            longdesc = (TextView) view.findViewById(R.id.constellationDescriptionlong);
            imageView =(ImageView) view.findViewById(R.id.constellationImage);

        }
    }


    public ConstellationListAdapter(List<ModelConstellationInfo> ConstellationList) {

        this.ConstellationList = ConstellationList;

    }

    @Override
    public ConstellationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_constellation_info_list_item, parent, false);

        return new ConstellationListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ConstellationListViewHolder holder, int position) {
        ModelConstellationInfo constellation = ConstellationList.get(position);
        holder.name.setText(constellation.getId());
        holder.description.setText(constellation.getDescriptionShort());
        holder.longdesc.setText(constellation.getDescriptionLong());
        // holder.relativeLayout.setB
        Picasso.get().load(constellation.getImage()).into(holder.imageView);
        //holder.imageView.setImageResource(constellation.getImage());
    }

    @Override
    public int getItemCount() {
        return ConstellationList.size();
    }
}

