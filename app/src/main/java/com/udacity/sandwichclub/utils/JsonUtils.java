package com.udacity.sandwichclub.utils;

import android.content.Intent;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich_Data = new Sandwich();

        JSONObject sandwitch = new JSONObject(json);

        JSONObject sandwitch_name = sandwitch.getJSONObject("name");

        sandwich_Data.setMainName((String) sandwitch_name.get("mainName"));
        sandwich_Data.setAlsoKnownAs(parseJSONArray(sandwitch_name.getJSONArray("alsoKnownAs")));

        sandwich_Data.setPlaceOfOrigin(sandwitch.getString("placeOfOrigin"));
        sandwich_Data.setDescription(sandwitch.getString("description"));
        sandwich_Data.setImage(sandwitch.getString("image"));
       sandwich_Data.setIngredients(parseJSONArray(sandwitch.getJSONArray("ingredients")));
/*
        Log.d("parseSandwichJson: ", sandwich_Data.getMainName());
        Log.d("parseSandwichJson: ", sandwich_Data.getDescription());
        Log.d("parseSandwichJson: ", sandwich_Data.getPlaceOfOrigin());
        Log.d("parseSandwichJson: ", sandwich_Data.getImage());
*/


        return sandwich_Data;
    }

    private static List<String> parseJSONArray(JSONArray array) throws JSONException {
        List<String> Data = new ArrayList<>();

        if(array.length()==0)
            return null;

        for (int i = 0; i < array.length(); i++) {
                String actor = array.getString(i);
                Log.d("parseSandwichJson", actor);
                if(actor!=null)
                    Data.add(actor);
            }
        return Data;

    }

}
