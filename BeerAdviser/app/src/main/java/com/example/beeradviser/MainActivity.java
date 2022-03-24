package com.example.beeradviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private Button findBeer;
    private TextView tvBeerBrand;
    private Spinner spinnerBeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        findBeer = findViewById(R.id.button);
        tvBeerBrand = findViewById(R.id.brands);
        spinnerBeer = findViewById(R.id.beer_color);

        findBeer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String color = spinnerBeer.getSelectedItem().toString();
                List<String> beerList = getBeers(color);
                String beer = "";

                for (int i = 0; i<beerList.size(); i++){
                    beer = beer + "\n" + beerList.get(i).toString();
                }
                tvBeerBrand.setText(beer);
            }
        });
    }
    public static List<String> getBeers(String color){
        List<String> listOfBeer = new ArrayList<>();
        switch (color){
            case "Light":
                listOfBeer.add("Jail Pale Ale");
                listOfBeer.add("Lager Liter");
                break;
            case "Amber":
                listOfBeer.add("Jack Amber");
                listOfBeer.add("Red Moose");
                break;
            case "Brown":
                listOfBeer.add("Brown Bear Beer");
                listOfBeer.add("Bock Brownie");
                break;
            default:
                listOfBeer.add("Gout Stout");
                listOfBeer.add("Dark Daniel");
                break;
        }
        return listOfBeer;
    }


}