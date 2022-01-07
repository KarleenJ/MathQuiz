package com.karleen.karleenfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.karleen.karleenfinalproject.model.Result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ListView listView;
    EditText etName;
    TextView tvTitle, tvUserScore;
    Button btnBack;

    String title, score, selectedFilter = "all";
    ArrayList<Result> listResults;
    ArrayAdapter<Result> arrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initialize();
        initializeListView();
    }

    private void initialize(){

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        etName = findViewById(R.id.etName);
        tvTitle = findViewById(R.id.tvTitle);
        tvUserScore = findViewById(R.id.tvUserScore);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        score = String.valueOf(getIntent().getIntExtra("scoreExtra", 0));
        tvUserScore.setText(score + "%");
    }

    private void initializeListView(){

        listView = findViewById(R.id.lvResults);

        //get array list from intent
        Bundle bundle = getIntent().getBundleExtra("intentExtra");
        Serializable bundledResultList = bundle.getSerializable("bundleExtra");
        listResults = (ArrayList<Result>) bundledResultList;

        // Define adapter
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listResults);

        // set adapter on array list
        listView.setAdapter(arrayAdapter);


    }

    //return name and score to main activity
    private void processBack() {

        title = etName.getText() + ": " + score + "%";

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", title);
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public void onClick(View view) {processBack();}

    private void filterListView(String filter){

        selectedFilter = filter;
        ArrayList<Result> filteredArrayList = new ArrayList<Result>();

        for(Result result : listResults){
            if(result.getResult().contains(filter)){
                filteredArrayList.add(result);

                arrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        filteredArrayList);
            }
            listView.setAdapter(arrayAdapter);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (position == 0){
            initializeListView();
        }
        if (position == 1){
           filterListView("Correct");
        }
        else if(position == 2){
            filterListView("Wrong");
        }
        else if (position == 3){
            Collections.reverse(listResults);
            arrayAdapter.notifyDataSetChanged();
        }
        else if (position == 4){
            Collections.sort(listResults);
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}