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
 * This class is an adapter for the recycler view created in ActivityConstellationInfo n it will take the list and
 * place the apropriate data in the correct location on the view and there will also be a lister on each card
 * which will allow users to get more information about a constellation on a card
 * */
public class ConstellationListAdapter extends RecyclerView.Adapter<ConstellationListAdapter.ConstellationListViewHolder> {

    private List<ModelConstellationList> ConstellationList;
    private Context context;

    //constructs a view whiten the recycle view
    public class ConstellationListViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, longdesc;
        public ImageView imageView;
        public CardView cv;
        public RelativeLayout relativeLayout;

        public ConstellationListViewHolder(View view) {
            super(view);
            cv = itemView.findViewById(R.id.card_view);
            name = view.findViewById(R.id.constellationTitle);
            description = view.findViewById(R.id.constellationDescription);
            imageView = view.findViewById(R.id.constellationImage);

        }
    }


    public ConstellationListAdapter(List<ModelConstellationList> ConstellationList) {

        this.ConstellationList = ConstellationList;

    }
    
    //will create our view holder which is construct above
    @Override
    public ConstellationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_constellation_info_list_item, parent, false);
        context = parent.getContext();
        return new ConstellationListViewHolder(itemView);
    }

   /*will bind a view assigned to by holder and will also store the position of the view
    holder so using the onclick listener, when a view holder is clicked, i can pass that position to different activity's*/
    @Override
    public void onBindViewHolder(ConstellationListViewHolder holder, int position) {

        ModelConstellationList constellation = ConstellationList.get(position);
        holder.name.setText(constellation.getTitle());
        holder.description.setText(constellation.getDescriptionShort());

        int resID = holder.imageView.getResources().getIdentifier(constellation.getImageName(), "drawable", context.getPackageName());
        Picasso.get().load(resID).fit().into(holder.imageView);
        holder.cv.setOnClickListener(v -> { //lambda

            Intent intent = new Intent(v.getContext(), ConstellationInDepthView.class);
            intent.putExtra("intVariableName", position);
            v.getContext().startActivity(intent);

        });
    }
    //keeps count of the amount of card views added to the recycler view
    @Override
    public int getItemCount() {
        return ConstellationList.size();
    }
}

