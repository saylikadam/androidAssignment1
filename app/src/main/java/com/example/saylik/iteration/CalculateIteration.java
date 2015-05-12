package com.example.saylik.iteration;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import datastorage.DataProcessor;
import datastorage.DatabaseCreator;
import estimation.EstimateProject;


public class CalculateIteration extends Activity {

    private static final int CALCULATE_ITERATION_WITH_BUFFER = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_iteration);
    }

    public void calculateIteration(View view) {
        Intent showNoOfIterationIntent = new Intent(CalculateIteration.this, ShowIterations.class);
        int iterations = getNumberOfIteration();
        showNoOfIterationIntent.putExtra("iteration", iterations);
        startActivityForResult(showNoOfIterationIntent, CALCULATE_ITERATION_WITH_BUFFER);
    }

    public int getNumberOfIteration() {
        EditText edPoints = (EditText) findViewById(R.id.enter_point);
        EditText edVelocity = (EditText) findViewById(R.id.enter_velocity);

        Integer point = Integer.parseInt(String.valueOf(edPoints.getText()));
        Integer velocity = Integer.parseInt(String.valueOf(edVelocity.getText()));

        EstimateProject estimateProject = new EstimateProject();
        int numberOfIteration = estimateProject.getNumberOfIteration(point.intValue(), velocity.intValue());
        putEstimationIntoDatabase(point.intValue(), velocity.intValue(), numberOfIteration);
        return numberOfIteration;
    }

    private void putEstimationIntoDatabase(int point, int velocity, int numberOfIteration) {
        DatabaseCreator databaseCreator = new DatabaseCreator(this);
        DataProcessor dataProcessor = new DataProcessor(databaseCreator);
        dataProcessor.putData(point, velocity, numberOfIteration);
    }

    public void clickPhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent chooser = Intent.createChooser(takePictureIntent,getString(R.string.chooser_title));
        startActivityForResult(chooser, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CALCULATE_ITERATION_WITH_BUFFER) {
                String newText = data.getStringExtra("iterationWithBuffer");
                TextView buffer = (TextView) findViewById(R.id.show_buffer);
                buffer.setText(newText);
            }
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extra = data.getExtras();
                Bitmap image = (Bitmap) extra.get("data");
                ImageView imageView = (ImageView) findViewById(R.id.picture_container);
                imageView.setImageBitmap(image);
            }
        }
    }


}
