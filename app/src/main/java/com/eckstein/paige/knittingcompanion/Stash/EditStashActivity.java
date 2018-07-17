package com.eckstein.paige.knittingcompanion.Stash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.LayoutInflater;
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

public class EditStashActivity extends BaseActivity {

    private String name, colorWay, weightString, fiberString;
    private int yardsInt, skeinsInt;
    private Yarn yarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = findViewById(R.id.mainLayout);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_edit_yarn, null, false);
        main.addView(contentView);

        //get Yarn object to edit from View Stash Activity
        Bundle bundle = getIntent().getExtras();
        yarn = bundle.getParcelable("yarn");

        final EditText yarnName = findViewById(R.id.yarnNameField);
        final EditText colorway = findViewById(R.id.colorwayField);
        final EditText weight = findViewById(R.id.weightField);
        final EditText fiber = findViewById(R.id.fiberField);
        final EditText totalYards = findViewById(R.id.yardsField);
        final EditText totalSkeins = findViewById(R.id.skeinsField);

        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get edited yarn information from text fields
                name = yarnName.getText().toString();
                colorWay = colorway.getText().toString();
                weightString = weight.getText().toString();
                fiberString = fiber.getText().toString();
                String temp = totalYards.getText().toString();
                //if any int fields are empty, set to 0
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

                //create yarn object from retrieved information
                Yarn yarn = new Yarn(name, colorWay, weightString, fiberString, yardsInt, skeinsInt);
                //update Yarn object in database
                updateDB(yarn);

                //return edited yarn object to ViewStashActivity
                Intent projectData = new Intent(EditStashActivity.this, ViewStashActivity.class);
                projectData.putExtra("yarn", yarn);
                setResult(RESULT_OK, projectData);
                finish();
            }
        });

    }

    /**
     * update edited yarn object in database
     * @param yarn edited Yarn object to update
     */
    public void updateDB(Yarn yarn) {
        //yarn fields
        StashDBHelper db = new StashDBHelper(this);
        String name, colorway, weight, fiber, yards, skeins;

        //get yarn fields
        name = yarn.getName();
        colorway = yarn.getColorway();
        weight = yarn.getWeight();
        fiber = yarn.getFiber();
        yards = String.valueOf(yarn.getTotalYards());
        skeins = String.valueOf(yarn.getTotalSkeins());

        //update row in database
        db.updateStash(name, colorway, weight, fiber, yards, skeins);
    }
}
