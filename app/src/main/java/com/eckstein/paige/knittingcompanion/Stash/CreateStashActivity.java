package com.eckstein.paige.knittingcompanion.Stash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.R;
import com.eckstein.paige.knittingcompanion.Yarn.Yarn;

import com.eckstein.paige.knittingcompanion.DatabaseHelpers.StashDBHelper;

public class CreateStashActivity extends BaseActivity {
    LinearLayout main;
    RelativeLayout rel;
    private String name, colorWay, weightString, fiberString;
    private int yardsInt, skeinsInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = findViewById(R.id.mainLayout);
        rel = new RelativeLayout(this);

        //text fields ==============================================================================
        //yarn name label
        TextView yarnNameLabel = new TextView(this);
        yarnNameLabel.setTextSize(20);
        yarnNameLabel.setId(View.generateViewId());
        yarnNameLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        yarnNameLabel.setText(getResources().getString(R.string.yarnNameLabel));

        //yarn name
        final EditText yarnName = new EditText(this);
        yarnName.setTextSize(18);
        yarnName.setId(View.generateViewId());

        //colorway label
        TextView colorwayLabel = new TextView(this);
        colorwayLabel.setTextSize(20);
        colorwayLabel.setId(View.generateViewId());
        colorwayLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        colorwayLabel.setText(getResources().getString(R.string.colorWayLabel));

        //colorway
        final EditText colorway = new EditText(this);
        colorway.setTextSize(18);
        colorway.setId(View.generateViewId());

        //weight label
        TextView weightLabel = new TextView(this);
        weightLabel.setTextSize(20);
        weightLabel.setId(View.generateViewId());
        weightLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        weightLabel.setText(getResources().getString(R.string.weightLabel));

        //weight
        final EditText weight = new EditText(this);
        weight.setTextSize(18);
        weight.setId(View.generateViewId());

        //fiber label
        TextView fiberLabel = new TextView(this);
        fiberLabel.setTextSize(20);
        fiberLabel.setId(View.generateViewId());
        fiberLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        fiberLabel.setText(getResources().getString(R.string.fiberLabel));

        //fiber
        final EditText fiber = new EditText(this);
        fiber.setTextSize(18);
        fiber.setId(View.generateViewId());

        //totalYards label
        TextView totalYardsLabel = new TextView(this);
        totalYardsLabel.setTextSize(20);
        totalYardsLabel.setId(View.generateViewId());
        totalYardsLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        totalYardsLabel.setText(getResources().getString(R.string.totalYardLabel));

        //totalYards
        final EditText totalYards = new EditText(this);
        totalYards.setTextSize(18);
        totalYards.setId(View.generateViewId());
        totalYards.setTypeface(getResources().getFont(R.font.mahoni));
        totalYards.setInputType(InputType.TYPE_CLASS_NUMBER);

        //total skeins label
        TextView totalSkeinsLabel = new TextView(this);
        totalSkeinsLabel.setTextSize(20);
        totalSkeinsLabel.setId(View.generateViewId());
        totalSkeinsLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        totalSkeinsLabel.setText(getResources().getString(R.string.skeinsLabel));

        //total skeins
        final EditText totalSkeins = new EditText(this);
        totalSkeins.setTextSize(18);
        totalSkeins.setId(View.generateViewId());
        totalSkeins.setTypeface(getResources().getFont(R.font.mahoni));
        totalSkeins.setInputType(InputType.TYPE_CLASS_NUMBER);

        //layout params ============================================================================
        //yarn name label
        RelativeLayout.LayoutParams yarnNameLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yarnNameLabelParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        yarnNameLabelParams.setMargins(15, 10, 10, 10);
        yarnNameLabel.setLayoutParams(yarnNameLabelParams);

        //yarn name
        RelativeLayout.LayoutParams yarnNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yarnNameParams.addRule(RelativeLayout.BELOW, yarnNameLabel.getId());
        yarnNameParams.setMargins(15, 10, 10, 10);
        yarnName.setLayoutParams(yarnNameParams);

        //color way label
        RelativeLayout.LayoutParams colorWayLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        colorWayLabelParams.addRule(RelativeLayout.BELOW, yarnName.getId());
        colorWayLabelParams.setMargins(15, 10, 10, 10);
        colorwayLabel.setLayoutParams(colorWayLabelParams);

        //color way
        RelativeLayout.LayoutParams colorwayParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        colorwayParams.addRule(RelativeLayout.BELOW, colorwayLabel.getId());
        colorwayParams.setMargins(15, 10, 10, 10);
        colorway.setLayoutParams(colorwayParams);

        //weight label
        RelativeLayout.LayoutParams weightLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        weightLabelParams.addRule(RelativeLayout.BELOW, colorway.getId());
        weightLabelParams.setMargins(15, 10, 10, 10);
        weightLabel.setLayoutParams(weightLabelParams);

        //weight
        RelativeLayout.LayoutParams weightParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        weightParams.addRule(RelativeLayout.BELOW, weightLabel.getId());
        weightParams.setMargins(15, 10, 10, 10);
        weight.setLayoutParams(weightParams);

        //fiber label
        RelativeLayout.LayoutParams fiberLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        fiberLabelParams.addRule(RelativeLayout.BELOW, weight.getId());
        fiberLabelParams.setMargins(15, 10, 10, 10);
        fiberLabel.setLayoutParams(fiberLabelParams);

        //fiber
        RelativeLayout.LayoutParams fiberParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        fiberParams.addRule(RelativeLayout.BELOW, fiberLabel.getId());
        fiberParams.setMargins(15, 10, 10, 10);
        fiber.setLayoutParams(fiberParams);

        //total yards label
        RelativeLayout.LayoutParams yardsLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yardsLabelParams.addRule(RelativeLayout.BELOW, fiber.getId());
        yardsLabelParams.setMargins(15, 10, 10, 10);
        totalYardsLabel.setLayoutParams(yardsLabelParams);

        //total yards
        RelativeLayout.LayoutParams yardsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yardsParams.addRule(RelativeLayout.BELOW, totalYardsLabel.getId());
        yardsParams.setMargins(15, 10, 10, 10);
        totalYards.setLayoutParams(yardsParams);

        //skeins label
        RelativeLayout.LayoutParams skeinsLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        skeinsLabelParams.addRule(RelativeLayout.BELOW, totalYards.getId());
        skeinsLabelParams.setMargins(15, 10, 10, 10);
        totalSkeinsLabel.setLayoutParams(skeinsLabelParams);

        //skeins
        RelativeLayout.LayoutParams skeinsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        skeinsParams.addRule(RelativeLayout.BELOW, totalSkeinsLabel.getId());
        skeinsParams.setMargins(15, 10, 10, 10);
        totalSkeins.setLayoutParams(skeinsParams);

        Button doneButton = new Button(this);
        doneButton.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        doneButton.setText(getResources().getString(R.string.done));
        doneButton.setId(View.generateViewId());

        RelativeLayout.LayoutParams doneParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        doneParams.addRule(RelativeLayout.BELOW, totalSkeins.getId());
        doneParams.setMargins(15, 10, 10, 10);
        doneButton.setLayoutParams(doneParams);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = yarnName.getText().toString();
                colorWay = colorway.getText().toString();
                weightString = weight.getText().toString();
                fiberString = fiber.getText().toString();
                String temp = totalYards.getText().toString();
                if (!temp.equals("")) {
                    yardsInt = Integer.parseInt(temp);
                } else {
                    yardsInt = 0;
                }
                temp = totalSkeins.getText().toString();
                if (!temp.equals("")) {
                    skeinsInt = Integer.parseInt(temp);
                } else {
                    skeinsInt = 0;
                }

                Yarn yarn = new Yarn(name, colorWay, weightString, fiberString, yardsInt, skeinsInt);
                updateDB(yarn);

                Intent projectData = new Intent(CreateStashActivity.this, ViewStashActivity.class);
                projectData.putExtra("yarn", yarn);
                setResult(RESULT_OK, projectData);
                finish();
            }
        });

        rel.addView(yarnNameLabel);
        rel.addView(yarnName);
        rel.addView(colorwayLabel);
        rel.addView(colorway);
        rel.addView(weightLabel);
        rel.addView(weight);
        rel.addView(fiberLabel);
        rel.addView(fiber);
        rel.addView(totalYardsLabel);
        rel.addView(totalYards);
        rel.addView(totalSkeinsLabel);
        rel.addView(totalSkeins);
        rel.addView(doneButton);

        main.addView(rel);
    }


    public void updateDB(Yarn yarn) {
        StashDBHelper db = new StashDBHelper(this);
        String name, colorway, weight, fiber, yards, skeins;

        name = yarn.getName();
        colorway = yarn.getColorway();
        weight = yarn.getWeight();
        fiber = yarn.getFiber();
        yards = String.valueOf(yarn.getTotalYards());
        skeins = String.valueOf(yarn.getTotalSkeins());

        db.insert(name, colorway, weight, fiber, yards, skeins);
    }
}
