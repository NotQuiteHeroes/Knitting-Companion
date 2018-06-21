package com.eckstein.paige.knittingcompanion.Counters;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.R;
import com.eckstein.paige.knittingcompanion.Utilities.ShakeDetector;

import java.util.ArrayList;

import com.eckstein.paige.knittingcompanion.DatabaseHelpers.CounterDBHelper;

/**
 * Activity to view all current counters
 */
public class ViewCounterActivity extends BaseActivity {

    ArrayList<Counter> counters;
    LinearLayout main;

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        counters = new ArrayList<>();
        main = findViewById(R.id.mainLayout);
        CounterDBHelper db = new CounterDBHelper(this);
        counters = db.getAllCounters();

        for (Counter counter : counters) {
            updateUI(counter);
        }

        Button newCounter = new Button(this);
        newCounter.setText(getResources().getString(R.string.addCounter));
        newCounter.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        newCounter.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        newCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectData = new Intent(ViewCounterActivity.this, CreateCounterActivity.class);
                startActivityForResult(projectData, 1);
            }
        });

        main.addView(newCounter);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                handleShakeEvent();
            }
        });
    }

    /**
     * what to do when sensor detects shake
     * (clear all counters)
     */
    public void handleShakeEvent() {
        for (Counter counter : counters) {
            counter.reset();
        }
        clearViews();
        redraw();
    }

    /**
     * Add all counters to the UI
     * @param counter Counter: Individual Counter object to add to UI
     */
    public void updateUI(Counter counter) {
        final Counter finalCounter = counter;
        RelativeLayout rel = new RelativeLayout(this);

        TextView projectName = new TextView(this);
        projectName.setTextSize(20);
        projectName.setId(View.generateViewId());
        projectName.setText(counter.getProjectName());

        TextView counterName = new TextView(this);
        counterName.setTextSize(20);
        counterName.setId(View.generateViewId());
        counterName.setText(counter.getName());

        TextView ones = new TextView(this);
        ones.setTextSize(20);
        ones.setId(View.generateViewId());
        ones.setTypeface(getResources().getFont(R.font.mahoni));
        ones.setText(String.valueOf(counter.getOnes()));

        TextView tens = new TextView(this);
        tens.setTextSize(20);
        tens.setId(View.generateViewId());
        tens.setTypeface(getResources().getFont(R.font.mahoni));
        tens.setText(String.valueOf(counter.getTens()));

        TextView hundreds = new TextView(this);
        hundreds.setTextSize(20);
        hundreds.setId(View.generateViewId());
        hundreds.setTypeface(getResources().getFont(R.font.mahoni));
        hundreds.setText(String.valueOf(counter.getHundreds()));

        //button set up
        Button increment = new Button(this);
        increment.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        increment.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        increment.setTextSize(20);
        increment.setText("+");
        increment.setId(View.generateViewId());

        Button decrement = new Button(this);
        decrement.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        decrement.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        decrement.setTextSize(20);
        decrement.setText("-");
        decrement.setId(View.generateViewId());

        Button clear = new Button(this);
        clear.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        clear.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        clear.setTextSize(20);
        clear.setText(getResources().getString(R.string.clear));
        clear.setId(View.generateViewId());

        //param set up
        RelativeLayout.LayoutParams projectParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        projectParams.setMargins(15, 10, 10, 10);
        projectName.setLayoutParams(projectParams);

        RelativeLayout.LayoutParams counterParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        counterParams.addRule(RelativeLayout.BELOW, projectName.getId());
        counterParams.setMargins(15, 10, 10, 10);
        counterName.setLayoutParams(counterParams);

        RelativeLayout.LayoutParams incrementParams = new RelativeLayout.LayoutParams(100, 100);
        incrementParams.addRule(RelativeLayout.BELOW, counterName.getId());
        incrementParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        incrementParams.setMargins(15, 10, 10, 10);
        increment.setLayoutParams(incrementParams);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalCounter.increment();
                updateCounterDB(finalCounter);
                clearViews();
                redraw();
            }
        });

        RelativeLayout.LayoutParams hundredsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        hundredsParams.addRule(RelativeLayout.END_OF, increment.getId());
        hundredsParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        hundredsParams.setMargins(15, 10, 10, 10);
        hundreds.setLayoutParams(hundredsParams);

        RelativeLayout.LayoutParams tensParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tensParams.addRule(RelativeLayout.END_OF, hundreds.getId());
        tensParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        tensParams.setMargins(15, 10, 10, 10);
        tens.setLayoutParams(tensParams);

        RelativeLayout.LayoutParams onesParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        onesParams.addRule(RelativeLayout.END_OF, tens.getId());
        onesParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        onesParams.setMargins(15, 10, 10, 10);
        ones.setLayoutParams(onesParams);

        RelativeLayout.LayoutParams decrementParams = new RelativeLayout.LayoutParams(100, 100);
        decrementParams.addRule(RelativeLayout.END_OF, ones.getId());
        decrementParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        decrementParams.setMargins(15, 10, 10, 10);
        decrement.setLayoutParams(decrementParams);
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalCounter.decrement();
                updateCounterDB(finalCounter);
                clearViews();
                redraw();
            }
        });

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        buttonParams.setMargins(10, 10, 10, 10);
        clear.setLayoutParams(buttonParams);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCounter.reset();
                updateCounterDB(finalCounter);
                Intent intent = new Intent(ViewCounterActivity.this, ViewCounterActivity.class);
                clearViews();
                redraw();
            }
        });

        //draw line between each project
        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));

        //add text views and button to relative layout
        rel.addView(projectName);
        rel.addView(counterName);
        rel.addView(increment);
        rel.addView(hundreds);
        rel.addView(tens);
        rel.addView(ones);
        rel.addView(decrement);
        rel.addView(clear);
        //add relative layout to main linear layout
        main.addView(rel);
        main.addView(v);
    }

    /**
     * keep database up to date with latest counter values
     * @param counter Counter: Individual counter object to update in database
     */
    public void updateCounterDB(Counter counter) {
        CounterDBHelper db = new CounterDBHelper(this);
        String projectName, counterName, ones, tens, hundreds;

        projectName = counter.getProjectName();
        counterName = counter.getName();
        ones = String.valueOf(counter.getOnes());
        tens = String.valueOf(counter.getTens());
        hundreds = String.valueOf(counter.getHundreds());

        db.updateCounter(projectName, counterName, ones, tens, hundreds);
    }

    /**
     * Result from CreateCounterActivity
     * add newly created Counter object to UI
     * @param requestCode Activity requestCode (called with 1)
     * @param resultCode Activity resultCode(OK?)
     * @param data Bundle data returned from CreateCounterActivity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Counter counter = bundle.getParcelable("counter");
                    updateUI(counter);
                }
            }
        }
    }

    /**
     * remove all current views attached to main view
     */
    public void clearViews() {
        main.removeAllViews();
    }

    /**
     * redraw UI
     * get all up to date counters from database
     * update UI with each one
     */
    public void redraw() {
        CounterDBHelper db = new CounterDBHelper(this);
        counters = db.getAllCounters();
        for (Counter counter : counters) {
            updateUI(counter);
        }

        Button newCounter = new Button(this);
        newCounter.setText(getResources().getString(R.string.addCounter));
        newCounter.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        newCounter.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        newCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectData = new Intent(ViewCounterActivity.this, CreateCounterActivity.class);
                startActivityForResult(projectData, 1);
            }
        });

        main.addView(newCounter);
    }

    /**
     * for sensor usage
     */
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * for sensor usage
     */
    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
