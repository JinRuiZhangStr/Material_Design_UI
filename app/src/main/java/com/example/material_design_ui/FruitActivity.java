package com.example.material_design_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by 张金瑞 on 2017/11/16.
 */

public class FruitActivity extends AppCompatActivity {

    private static final String FURIT_NAME = "fruit_name";
    private static final String FURIT_IMAGE ="fruit_image";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        Intent intent = getIntent();

        String fruit_name = intent.getStringExtra(FURIT_NAME);
        String fruit_image = intent.getStringExtra(FURIT_IMAGE);


        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        ImageView furitImage = findViewById(R.id.fruit_image_view);
        TextView fruitContentText = findViewById(R.id.fruit_content_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(fruit_name);
        Glide.with(this).load(fruit_image).into(furitImage);
        fruitContentText.setText(generateFruitContent(fruit_name));
    }

    private String generateFruitContent(String fruitname){
        StringBuilder sb = new StringBuilder();
        for (int i= 0 ;i<500;i++){
            sb.append(fruitname);
        }

        return sb.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :

                finish();
                break;
        }

        return true;


    }
}
