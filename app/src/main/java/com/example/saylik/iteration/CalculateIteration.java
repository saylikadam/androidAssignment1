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
import datastorage.EstimationReader;
import datastorage.EstimationReaderContract;

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
        EditText points = (EditText)findViewById(R.id.enter_point);
        EditText velocity = (EditText)findViewById(R.id.enter_velocity);
        Integer intPoint = Integer.parseInt(String.valueOf(points.getText()));
        Integer intVelocity = Integer.parseInt(String.valueOf(velocity.getText()));
        int iteration = intPoint.intValue()/intVelocity.intValue();
        putEstimationIntoDatabase(intPoint.intValue(),intVelocity.intValue(),iteration);
        return iteration;
    }

    private void putEstimationIntoDatabase(int point,int velocity,int iteration){
        EstimationReader estimationReader = new EstimationReader(this);
        SQLiteDatabase db = estimationReader.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EstimationReaderContract.IterationEntry.POINT,point);
        values.put(EstimationReaderContract.IterationEntry.PROJECT_VELOCITY,velocity);
        values.put(EstimationReaderContract.IterationEntry.ITERATION,iteration);
        db.insert(EstimationReaderContract.IterationEntry.TABLE_ITERATION,null,values);
        db.close();
    }

    public void clickPhoto(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
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
