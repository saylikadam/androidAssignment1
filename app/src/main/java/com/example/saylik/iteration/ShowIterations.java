package com.example.saylik.iteration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ShowIterations extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_iterations);
        setIteration();
    }

    private void setIteration() {
        TextView textView = (TextView)findViewById(R.id.show_iterations);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("iteration"));
    }

    private int calculateBuffer() {
        TextView iterationValue = (TextView)findViewById(R.id.show_iterations);
        int iterations = iterationValue.getInputType();
        EditText bufferValue = (EditText)findViewById(R.id.show_buffer);
        int buffer = bufferValue.getInputType();
        int bufferWithIter = iterations+buffer;
        return bufferWithIter;
    }

    public void calcIterWithBuff(View view){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("iterationWithBuffer",calculateBuffer()+"");
        setResult(Activity.RESULT_OK,resultIntent);
        finish();
    }
}
