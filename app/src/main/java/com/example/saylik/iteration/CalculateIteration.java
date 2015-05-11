package com.example.saylik.iteration;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import datastorage.DataProcessor;
import datastorage.DatabaseCreationContract;
import datastorage.DatabaseCreator;
import estimation.EstimateIteration;


public class CalculateIteration extends Activity {

    private static final int CALCULATE_ITERATION_WITH_BUFFER = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_iteration);
    }

    public void calculateIteration(View view){
        Intent showIteration = new Intent(CalculateIteration.this, ShowIterations.class);
        int iterations = calculateIterations();
        showIteration.putExtra("iteration", iterations);
        startActivityForResult(showIteration, CALCULATE_ITERATION_WITH_BUFFER);
    }

    public int calculateIterations(){
        EditText edPoints = (EditText)findViewById(R.id.enter_point);
        EditText edVelocity = (EditText)findViewById(R.id.enter_velocity);

        Integer point = Integer.parseInt(String.valueOf(edPoints.getText()));
        Integer velocity = Integer.parseInt(String.valueOf(edVelocity.getText()));

        EstimateIteration estimateIteration = new EstimateIteration();
        int numberOfIteration = estimateIteration.getIteration(point.intValue(), velocity.intValue());
        putEstimationIntoDatabase(point.intValue(),velocity.intValue(),numberOfIteration);
        return numberOfIteration;
    }

    private void putEstimationIntoDatabase(int point,int velocity,int numberOfIteration){
        DatabaseCreator databaseCreator = new DatabaseCreator(this);
        DataProcessor dataProcessor = new DataProcessor(databaseCreator);
        dataProcessor.putData(point, velocity, numberOfIteration);
    }

    public void clickPhoto(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CALCULATE_ITERATION_WITH_BUFFER) {
                String newText = data.getStringExtra("iterationWithBuffer");
                TextView buffer = (TextView) findViewById(R.id.show_buffer);
                buffer.setText(newText);
            }
            if(requestCode == REQUEST_IMAGE_CAPTURE){
                Bundle extra = data.getExtras();
                Bitmap image = (Bitmap)extra.get("data");
                ImageView imageView = (ImageView)findViewById(R.id.picture_container);
                imageView.setImageBitmap(image);
            }
        }
    }


}
