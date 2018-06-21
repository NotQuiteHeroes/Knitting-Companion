package com.eckstein.paige.knittingcompanion.Counters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.R;

import com.eckstein.paige.knittingcompanion.DatabaseHelpers.CounterDBHelper;

public class CreateCounterActivity extends BaseActivity {

    LinearLayout main;
    RelativeLayout rel;
    String counterName, projectName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = findViewById(R.id.mainLayout);
        rel = new RelativeLayout(this);

        TextView projectNameLabel = new TextView(this);
        projectNameLabel.setTextSize(20);
        projectNameLabel.setId(View.generateViewId());
        projectNameLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        projectNameLabel.setText(getResources().getString(R.string.projectNameLabel));

        final EditText projectNameField = new EditText(this);
        projectNameField.setTextSize(18);
        projectNameField.setId(View.generateViewId());
        projectNameField.setHint(getResources().getString(R.string.projectName));

        TextView counterLabel = new TextView(this);
        counterLabel.setTextSize(20);
        counterLabel.setId(View.generateViewId());
        counterLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        counterLabel.setText(getResources().getString(R.string.counter));

        final EditText counterNameField = new EditText(this);
        counterNameField.setTextSize(18);
        counterNameField.setId(View.generateViewId());
        counterNameField.setHint(getResources().getString(R.string.counterHint));

        RelativeLayout.LayoutParams projectLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectLabelParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        projectLabelParams.setMargins(15, 10, 10, 10);
        projectNameLabel.setLayoutParams(projectLabelParams);

        RelativeLayout.LayoutParams projectFieldParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectFieldParams.addRule(RelativeLayout.BELOW, projectNameLabel.getId());
        projectFieldParams.setMargins(15, 10, 10, 10);
        projectNameField.setLayoutParams(projectFieldParams);

        RelativeLayout.LayoutParams counterLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        counterLabelParams.addRule(RelativeLayout.BELOW, projectNameField.getId());
        counterLabelParams.setMargins(15, 10, 10, 10);
        counterLabel.setLayoutParams(counterLabelParams);

        RelativeLayout.LayoutParams counterParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        counterParams.addRule(RelativeLayout.BELOW, counterLabel.getId());
        counterParams.setMargins(15, 10, 10, 10);
        counterNameField.setLayoutParams(counterParams);

        Button doneButton = new Button(this);
        doneButton.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        doneButton.setText(getResources().getString(R.string.done));
        doneButton.setId(View.generateViewId());
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectName = projectNameField.getText().toString();
                counterName = counterNameField.getText().toString();

                Counter counter = new Counter(projectName, counterName);

                updateDb(counter);

                Intent projectData = new Intent(CreateCounterActivity.this, ViewCounterActivity.class);
                projectData.putExtra("counter", counter);
                setResult(RESULT_OK, projectData);
                finish();
            }
        });


        rel.addView(projectNameLabel);
        rel.addView(projectNameField);
        rel.addView(counterLabel);
        rel.addView(counterNameField);

        main.addView(rel);
        main.addView(doneButton);
    }

    public void updateDb(Counter counter) {
        CounterDBHelper db = new CounterDBHelper(this);
        db.insert(projectName, counterName, "0", "0", "0");
    }
}
