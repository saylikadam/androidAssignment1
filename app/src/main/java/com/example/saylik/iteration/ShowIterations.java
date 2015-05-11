package com.example.saylik.iteration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        TextView textView = (TextView) findViewById(R.id.show_iterations);
        Intent intent = getIntent();
        int iterations = intent.getIntExtra("iteration", 0);
        textView.setText(String.valueOf(iterations));
    }

    private int calculateBuffer() {
        TextView iterationValue = (TextView) findViewById(R.id.show_iterations);
        Integer iterations = Integer.parseInt(iterationValue.getText().toString());
        EditText bufferValue = (EditText) findViewById(R.id.show_buffer);
        Integer buffer = Integer.parseInt(bufferValue.getText().toString());
        int bufferWithIter = iterations.intValue() + buffer.intValue();
        return bufferWithIter;
    }

    public void calcIterWithBuff(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("iterationWithBuffer", String.valueOf(calculateBuffer()));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
