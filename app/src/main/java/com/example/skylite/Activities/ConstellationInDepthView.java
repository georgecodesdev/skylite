package com.example.skylite.Activities;


//import android.support.v7.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.skylite.MainActivity;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.example.skylite.R;
import java.util.ArrayList;
import java.util.List;

public class ConstellationInDepthView extends AppCompatActivity  {
    List<ModelConstellationInfo> modelConstellationInfo = MainActivity.modelConstellationInfo;

    private List<ModelConstellationList> constellationList = new ArrayList<>();
    public TextView name, description,longdesc;
    public ImageView imageView;
    private ArrayList<ModelConstellationInfo> modelConstellationList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("intVariableName", 0);
        setContentView(R.layout.fragment_constellation_info);
        ModelConstellationInfo temp= modelConstellationInfo.get(intValue);
        name = (TextView) findViewById(R.id.constellationTitle);
        description =  (TextView) findViewById(R.id.constellationDescription);
        longdesc = (TextView) findViewById(R.id.constellationDescriptionlong);
        imageView = (ImageView) findViewById(R.id.constellationImage);

        //Log.d("myTag", "This is my message="+temp.getTitle());
        name.setText(temp.getTitle());
        description.setText(temp.getDescriptionShort());
        longdesc.setText(temp.getDescriptionLong());
        imageView.setImageResource(getResources().getIdentifier(temp.getImageName(), "drawable", getPackageName()));

    }






}
