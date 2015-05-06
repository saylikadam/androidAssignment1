package com.example.saylik.iteration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class calculateIteration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_iteration);
        Button calculateIteration = (Button)findViewById(R.id.calculate_iteration);
        calculateIteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showIteration = new Intent(calculateIteration.this, ShowIterations.class);
                int iterations = calculateIterations();
                showIteration.putExtra("iteration", iterations+"");
                startActivity(showIteration);
            }
        });
    }

    public int calculateIterations(){
        EditText points = (EditText)findViewById(R.id.enter_point);
        EditText velocity = (EditText)findViewById(R.id.enter_velocity);
        Integer intPoint = Integer.parseInt(String.valueOf(points.getText()));
        Integer intVelocity = Integer.parseInt(String.valueOf(velocity.getText()));
        int iteration = intPoint.intValue()/intVelocity.intValue();
        return iteration;
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_calculate_iteration, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
