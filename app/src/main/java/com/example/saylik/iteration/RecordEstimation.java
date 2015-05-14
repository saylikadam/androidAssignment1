package com.example.saylik.iteration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class RecordEstimation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_estimation);
    }

    public void estimateIteration(View view) {
        Intent intent = new Intent(RecordEstimation.this, CalculateIteration.class);
        startActivity(intent);
    }

}
