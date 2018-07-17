package com.eckstein.paige.knittingcompanion.Stash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.MainActivity;
import com.eckstein.paige.knittingcompanion.R;

import com.eckstein.paige.knittingcompanion.DatabaseHelpers.StashDBHelper;
import com.eckstein.paige.knittingcompanion.Yarn.Yarn;

public class ViewYarnActivity extends BaseActivity {

    LinearLayout main;
    Yarn yarn;

    TextView yarnName;
    TextView colorway;
    TextView weight;
    TextView fiber;
    TextView totalYards;
    TextView totalSkeins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = findViewById(R.id.mainLayout);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_view_yarn, null, false);
        main.addView(contentView);

        Bundle bundle = getIntent().getExtras();
        yarn = bundle.getParcelable("yarn");

        yarnName = findViewById(R.id.yarnNameField);
        colorway = findViewById(R.id.colorwayField);
        weight = findViewById(R.id.weightField);
        fiber = findViewById(R.id.fiberField);
        totalYards = findViewById(R.id.yardsField);
        totalSkeins = findViewById(R.id.skeinsField);

        updateUI(yarn);

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectData = new Intent(ViewYarnActivity.this, EditStashActivity.class);
                projectData.putExtra("yarn", yarn);
                startActivityForResult(projectData, 1);
            }
        });

        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectData = new Intent(ViewYarnActivity.this, MainActivity.class);
                startActivity(projectData);
                finish();
            }
        });

    }

    public void updateUI(Yarn yarn)
    {
        yarnName.setText(yarn.getName());
        colorway.setText(yarn.getColorway());
        weight.setText(yarn.getWeight());
        fiber.setText(yarn.getFiber());
        totalYards.setText(String.valueOf(yarn.getTotalYards()));
        totalSkeins.setText(String.valueOf(yarn.getTotalSkeins()));
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

        db.updateStash(name, colorway, weight, fiber, yards, skeins);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Yarn yarn = bundle.getParcelable("yarn");
                    updateDB(yarn);
                    updateUI(yarn);
                }
            }
        }
    }
}
