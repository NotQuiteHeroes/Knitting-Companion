package com.eckstein.paige.knittingcompanion.Projects;

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
import com.eckstein.paige.knittingcompanion.DatabaseHelpers.ProjectDBHelper;
import com.eckstein.paige.knittingcompanion.MainActivity;
import com.eckstein.paige.knittingcompanion.R;

/**
 * Activity to create new Project
 */
public class CreateProjectActivity extends BaseActivity {

    private String startDate, endDate;
    private String patternName, yarnName, projectName;
    private int totalYardage, yardageUsed, totalSkeins;
    private String colorWay, note;
    private float size;
    private String yarnWeight, fiber, needleType, needleLength;
    float needleSize;
    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = findViewById(R.id.mainLayout);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_edit_project, null, false);
        main.addView(contentView);

        Bundle bundle = getIntent().getExtras();
        //get project to edit
        project = bundle.getParcelable("project");

        final EditText projectNameField = findViewById(R.id.projectField);
        final EditText patternNameField = findViewById(R.id.patternField);
        final EditText startDateField = findViewById(R.id.startField);
        final EditText endDateField = findViewById(R.id.endField);
        final EditText sizeField = findViewById(R.id.sizeField);

        final EditText yarnNameField = findViewById(R.id.yarnNameField);
        final EditText yarnWeightField = findViewById(R.id.yarnWeightField);
        final EditText colorWayField = findViewById(R.id.colorwayField);
        final EditText totalYardageField = findViewById(R.id.yardUsedField);
        final EditText fiberField = findViewById(R.id.fiberField);

        final EditText needleSizeField = findViewById(R.id.needleSizeField);
        final EditText needleTypeField = findViewById(R.id.needleTypeField);
        final EditText needleLengthField = findViewById(R.id.needleLengthField);

        final EditText noteField = findViewById(R.id.noteField);


        //Done button ==============================================================================
        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from text fields
                startDate = startDateField.getText().toString();
                endDate = endDateField.getText().toString();
                patternName = patternNameField.getText().toString();
                yarnName = yarnNameField.getText().toString();
                projectName = projectNameField.getText().toString();
                colorWay = colorWayField.getText().toString();
                note = noteField.getText().toString();
                yarnWeight = yarnWeightField.getText().toString();
                fiber = fiberField.getText().toString();
                needleType = needleTypeField.getText().toString();
                needleLength = needleLengthField.getText().toString();

                String temp = totalYardageField.getText().toString();
                //if any int fields are empty, set to 0
                if (!temp.equals("")) {
                    totalYardage = Integer.parseInt(temp);
                } else {
                    totalYardage = 0;
                }
                temp = sizeField.getText().toString();
                if (!temp.equals("")) {
                    size = Float.parseFloat(temp);
                } else {
                    size = 0;
                }
                temp = needleSizeField.getText().toString();
                if(!temp.equals(""))
                {
                    needleSize = Float.parseFloat(temp);
                } else {
                    needleSize = 0;
                }

                //create project object and populate with retrieved information
                Project project = new Project();
                project.setStartDate(startDate);
                project.setEndDate(endDate);
                project.setPatternName(patternName);
                project.setYarnName(yarnName, -1);
                project.setProjectName(projectName);
                project.setColorWay(colorWay, -1);
                project.setNote(note);
                project.setYardageUsed(totalYardage);
                project.setSize(size);
                project.setWeight(yarnWeight, -1);
                project.setFiber(fiber, -1);
                project.setNeedleSize(needleSize, -1);
                project.setNeedleType(needleType, -1);
                project.setNeedleLength(needleLength, -1);

                //update database with edited Project
                updateDb(project);

                //return new project object to View Project Activity
                Intent projectData = new Intent(CreateProjectActivity.this, MainActivity.class);
                projectData.putExtra("project", project);
                setResult(RESULT_OK, projectData);
                finish();
            }
        });

    }

    /**
     * add newly created Project to Project database
     * @param project
     */
    public void updateDb(Project project) {
        ProjectDBHelper db = new ProjectDBHelper(this);
        String projectName, patternName, yarnName;
        String start, end;
        String totalYards, yardsUsed, colorway;
        String note, size, skeins;

        //get project information
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

        //insert new row
        db.insert(projectName, patternName, yarnName, start, end, totalYards, yardsUsed, skeins,
                colorway, note, size, "test", "test", "0", "test",
                "test", "test");
    }
}
