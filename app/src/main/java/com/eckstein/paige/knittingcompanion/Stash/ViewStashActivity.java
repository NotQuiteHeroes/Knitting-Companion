package com.eckstein.paige.knittingcompanion.Stash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.R;
import com.eckstein.paige.knittingcompanion.Yarn.Yarn.ViewYarnActivity;
import com.eckstein.paige.knittingcompanion.Yarn.Yarn;

import java.util.ArrayList;

import com.eckstein.paige.knittingcompanion.DatabaseHelpers.StashDBHelper;

public class ViewStashActivity extends BaseActivity {

    ArrayList<Yarn> stash;
    LinearLayout main;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stash = new ArrayList<>();
        main = findViewById(R.id.mainLayout);
        StashDBHelper db = new StashDBHelper(this);
        stash = db.getAllYarn();

        for (Yarn yarn : stash) {
            updateUI(yarn);
        }

        Button newYarn = new Button(this);
        newYarn.setText(getResources().getString(R.string.addYarn));
        newYarn.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        newYarn.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        newYarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStashActivity.this, CreateStashActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        main.addView(newYarn);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Yarn yarn = bundle.getParcelable("yarn");
                    updateUI(yarn);
                }
            }
        }
    }

    public void updateUI(Yarn yarn) {
        final Yarn finalYarn = yarn;
        RelativeLayout rel = new RelativeLayout(this);

        TextView yarnName = new TextView(this);
        yarnName.setTextSize(20);
        yarnName.setId(View.generateViewId());
        yarnName.setText(yarn.getName());

        TextView colorway = new TextView(this);
        colorway.setTextSize(18);
        colorway.setId(View.generateViewId());
        colorway.setText(yarn.getColorway());

        //view button set up
        Button viewProject = new Button(this);
        viewProject.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        viewProject.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        viewProject.setText(getResources().getString(R.string.view));
        viewProject.setId(View.generateViewId());

        RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        nameParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        nameParams.setMargins(15, 10, 10, 10);
        yarnName.setLayoutParams(nameParams);

        RelativeLayout.LayoutParams colorParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        colorParams.addRule(RelativeLayout.BELOW, yarnName.getId());
        colorParams.setMargins(15, 10, 10, 10);
        colorway.setLayoutParams(colorParams);

        //layout params for button
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        buttonParams.setMargins(10, 10, 10, 10);
        viewProject.setLayoutParams(buttonParams);
        viewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStashActivity.this, ViewYarnActivity.class);
                intent.putExtra("yarn", finalYarn);
                startActivity(intent);
                finish();
            }
        });

        //draw line between each project
        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));

        //add text views and button to relative layout
        rel.addView(yarnName);
        rel.addView(colorway);
        rel.addView(viewProject);
        //add relative layout to main linear layout
        main.addView(rel);
        main.addView(v);
    }

}
