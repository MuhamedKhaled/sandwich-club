package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView name = findViewById(R.id.name_tv);
        TextView origin = findViewById(R.id.origin_tv);
        TextView known = findViewById(R.id.also_known_tv);
        TextView ingredients = findViewById(R.id.ingredients_tv);
        TextView description = findViewById(R.id.description_tv);


        name.setText(sandwich.getMainName());
        origin.setText(sandwich.getPlaceOfOrigin());
         if(sandwich.getAlsoKnownAs()!=null)
         known.setText(concatenateString(sandwich.getAlsoKnownAs()));
        if(sandwich.getIngredients()!=null)
         ingredients.setText(concatenateString(sandwich.getIngredients()));
        description.setText(sandwich.getDescription());

    }


    private String concatenateString(List<String>s){
        String temp="";
        Log.d("number", Integer.toString(s.size()));
        for(int i = 0 ; i <s.size() ; i++){
            temp = temp + s.get(i);
            if(i != s.size()-1)
                temp+= " , ";
        }
        return temp;
    }
}
