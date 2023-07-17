package com.vincentwijaya202102315.tugasakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button data_pemain;

    private ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<SlideModel>();

        slideModels.add(new SlideModel(R.drawable.raket, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.mendali, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sertifikat, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.piala, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        data_pemain=findViewById(R.id.btndatapemain);
        data_pemain.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DataPemain.class);
            startActivity(intent);
        });
    }
}