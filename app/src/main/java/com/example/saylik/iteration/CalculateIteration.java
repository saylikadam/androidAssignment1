package com.example.saylik.iteration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class CalculateIteration extends Activity {

    private static final int STATIC_INTEGER_VALUE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_iteration);

    }

    public void calculateIteration(View view){
        Intent showIteration = new Intent(CalculateIteration.this, ShowIterations.class);
        int iterations = calculateIterations();
        showIteration.putExtra("iteration", iterations+"");
        startActivityForResult(showIteration, STATIC_INTEGER_VALUE);
    }

    public int calculateIterations(){
        EditText points = (EditText)findViewById(R.id.enter_point);
        EditText velocity = (EditText)findViewById(R.id.enter_velocity);
        Integer intPoint = Integer.parseInt(String.valueOf(points.getText()));
        Integer intVelocity = Integer.parseInt(String.valueOf(velocity.getText()));
        int iteration = intPoint.intValue()/intVelocity.intValue();
        return iteration;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == STATIC_INTEGER_VALUE){
            if(resultCode == Activity.RESULT_OK){
                String newText = data.getStringExtra("iterationWithBuffer");
                TextView buffer = (TextView)findViewById(R.id.show_buffer);
                buffer.setText(newText);
            }
        }

    }
}
