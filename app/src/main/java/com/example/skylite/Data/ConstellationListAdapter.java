package com.example.skylite.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.skylite.R;
import com.example.skylite.Services.ServiceBase;

import java.util.List;

public class ConstellationListAdapter extends RecyclerView.Adapter<ConstellationListAdapter.ConstellationViewHolder> {

    class ConstellationViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private ConstellationViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Constellation> constellations; // Cached copy of words

    public ConstellationListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ConstellationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ConstellationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConstellationViewHolder holder, int position) {
        if (constellations != null) {
            Constellation current = constellations.get(position);
            holder.wordItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Name");
        }
    }

    public void setConstellations(List<Constellation> constellations){
        this.constellations = constellations;
        ServiceBase.constellationService().populateList(constellations);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (constellations != null)
            return constellations.size();
        else return 0;
    }
}

