package com.eckstein.paige.knittingcompanion.Projects;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.DatabaseHelpers.ProjectDBHelper;
import com.eckstein.paige.knittingcompanion.MainActivity;
import com.eckstein.paige.knittingcompanion.R;

import com.eckstein.paige.knittingcompanion.Counters.Counter;
import com.eckstein.paige.knittingcompanion.DatabaseHelpers.CounterDBHelper;

import org.w3c.dom.Text;

/**
 * Activity to get in depth view of individual project
 */
public class ViewProjectActivity extends BaseActivity {


    Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = findViewById(R.id.mainLayout);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_view_project, null, false);
        //drawer.addView(contentView, 0);
        main.addView(contentView);

        //get intent data from MainActivity
        Bundle bundle = getIntent().getExtras();
        //get project sent through data
        project = bundle.getParcelable("project");

        //update UI for specified project
        updateUI(project);
    }

    /**
     * Programmatically set up UI
     * @param project
     */
    public void updateUI(Project project) {
        final Project finalProject = project;
        TextView projectName = findViewById(R.id.projectField);
        projectName.setText(project.getProjectName());

        TextView patternName = findViewById(R.id.patternField);
        patternName.setText(project.getPatternName());

        TextView startDate = findViewById(R.id.startField);
        startDate.setText(project.getStartDate());

        TextView endDate = findViewById(R.id.endField);
        endDate.setText(project.getEndDate());

        TextView size = findViewById(R.id.sizeField);
        size.setText(String.valueOf(project.getSize()));

        TextView yarnName = findViewById(R.id.yarnNameField);
        yarnName.setText(project.getYarnName());

        TextView colorway = findViewById(R.id.colorwayField);
        colorway.setText(project.getColorWay(-1));

        TextView yards = findViewById(R.id.yardUsedField);
        yards.setText(String.valueOf(project.getYardageUsed()));

        TextView needleSize = findViewById(R.id.needleSizeField);
        needleSize.setText(String.valueOf(project.getNeedleSize(-1)));

        TextView needleType = findViewById(R.id.needleTypeField);
        needleType.setText(project.getNeedleType(-1));

        TextView needleLength = findViewById(R.id.needleLengthField);
        needleLength.setText(project.getNeedleLength(-1));

        // add counter button =====================================================================
        Button addCounter = findViewById(R.id.counterButton);
        addCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewProjectActivity.this);
                builder.setTitle("Add Counter");
                View viewInflated = LayoutInflater.from(ViewProjectActivity.this).inflate(R.layout.pop_up, (ViewGroup) findViewById(android.R.id.content), false);
                final EditText input = viewInflated.findViewById(R.id.input);
                builder.setView(viewInflated);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Counter counter = new Counter(finalProject.getProjectName(), input.getText().toString());
                        updateCounterDB(counter);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

        // Edit button =============================================================================
        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectData = new Intent(ViewProjectActivity.this, EditProjectActivity.class);
                projectData.putExtra("project", finalProject);
                startActivityForResult(projectData, 1);
            }
        });

        // done button =============================================================================
        Button doneButton = findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectData = new Intent(ViewProjectActivity.this, MainActivity.class);
                startActivity(projectData);
                finish();
            }
        });
    }

    /**
     * Update database with project if it was edited
     * @param project Project object that was edited
     */
    public void updateDB(Project project) {
        //Project fields
        ProjectDBHelper db = new ProjectDBHelper(this);
        String projectName, patternName, yarnName;
        String start, end;
        String totalYards, yardsUsed, colorway;
        String note, size, skeins;
        String yarnWeight, fiber, needleSize, needleSizeType, needleType, needleLength;

        //get project fields
        projectName = project.getProjectName();
        patternName = project.getPatternName();
        yarnName = project.getYarnName();
        start = project.getStartDate();
        end = project.getEndDate();
        totalYards = String.valueOf(project.getTotalYardage(-1));
        yardsUsed = String.valueOf(project.getYardageUsed());
        colorway = project.getColorWay(-1);
        note = project.getNote(0);
        size = String.valueOf(project.getSize());
        skeins = String.valueOf(project.getSkeins(-1));
        yarnWeight = project.getWeight(-1);
        fiber = project.getFiber(-1);
        needleSize = String.valueOf(project.getNeedleSize(-1));
        needleSizeType = project.getNeedleSizeType(-1);
        needleType = project.getNeedleType(-1);
        needleLength = project.getNeedleLength(-1);

        //update project in database
        db.updateProject(projectName, patternName, yarnName, start, end, totalYards, yardsUsed,
                skeins, colorway, note, size, yarnWeight, fiber, needleSize, needleSizeType, needleType,
                needleLength);
    }

    /**
     * Update Counter database if new counter was created
     * @param counter newly created counter object
     */
    public void updateCounterDB(Counter counter) {
        //counter fields
        CounterDBHelper db = new CounterDBHelper(this);
        String projectName, counterName, ones, tens, hundreds;

        //get counter information
        projectName = counter.getProjectName();
        counterName = counter.getName();
        ones = String.valueOf(counter.getOnes());
        tens = String.valueOf(counter.getTens());
        hundreds = String.valueOf(counter.getHundreds());

        //add new counter to database
        db.insert(projectName, counterName, ones, tens, hundreds);
    }

    /**
     * On result from edit project activity
     * @param requestCode Int request code (1)
     * @param resultCode Int result code (RESULT OK)
     * @param data Intent data returned from edit project activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if all was successful
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //get bundle from intent
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    //get Project from bundle
                    Project project = bundle.getParcelable("project");
                    //update database with edited project
                    updateDB(project);
                    //update text fields with edited project
                    updateUI(project);
                }
            }
        }
    }
}
