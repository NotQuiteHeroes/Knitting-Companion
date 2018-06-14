package com.eckstein.paige.knittingcompanion;

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

public class CreateProjectActivity extends BaseActivity {

    LinearLayout main;
    RelativeLayout rel;
    private String startDate, endDate;
    private String patternName, yarnName, projectName;
    private int totalYardage, yardageUsed, totalSkeins;
    private String colorWay, note;
    private float size;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        main = findViewById(R.id.mainLayout);
        rel = new RelativeLayout(this);

        //Project Headers ==========================================================================
        //project label
        TextView projectLabel = new TextView(this);
        projectLabel.setTextSize(20);
        projectLabel.setId(View.generateViewId());
        projectLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        projectLabel.setText(getResources().getString(R.string.project));

        //draw line between each project
        View projectLine = new View(this);
        projectLine.setId(View.generateViewId());
        projectLine.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));

        //Yarn Label
        TextView yarnLabel = new TextView(this);
        yarnLabel.setTextSize(20);
        yarnLabel.setId(View.generateViewId());
        yarnLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        yarnLabel.setText(getResources().getString(R.string.yarnLabel));

        //draw line between each project
        View yarnLine = new View(this);
        yarnLine.setId(View.generateViewId());
        yarnLine.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));

        //Notes Label
        TextView notesLabel = new TextView(this);
        notesLabel.setTextSize(20);
        notesLabel.setId(View.generateViewId());
        notesLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        notesLabel.setText(getResources().getString(R.string.noteLabel));

        //draw line between each project
        View noteLine = new View(this);
        noteLine.setId(View.generateViewId());
        noteLine.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));


        //int field labels =========================================================================
        //totalYardage label
        TextView totalYardageLabel = new TextView(this);
        totalYardageLabel.setTextSize(15);
        totalYardageLabel.setId(View.generateViewId());
        totalYardageLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        totalYardageLabel.setText(getResources().getString(R.string.totalYardLabel));

        //yardageUsed label
        TextView yardsUsedLabel = new TextView(this);
        yardsUsedLabel.setTextSize(15);
        yardsUsedLabel.setId(View.generateViewId());
        yardsUsedLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        yardsUsedLabel.setText(getResources().getString(R.string.yardUsedLabel));

        //totalSkeins label
        TextView skeinsLabel = new TextView(this);
        skeinsLabel.setTextSize(15);
        skeinsLabel.setId(View.generateViewId());
        skeinsLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        skeinsLabel.setText(getResources().getString(R.string.skeinsLabel));

        //size label
        TextView sizeLabel = new TextView(this);
        sizeLabel.setTextSize(15);
        sizeLabel.setId(View.generateViewId());
        sizeLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        sizeLabel.setText(getResources().getString(R.string.sizeLabel));

        //input fields =============================================================================
        //startDate
        final EditText startDateField = new EditText(this);
        startDateField.setTextSize(15);
        startDateField.setId(View.generateViewId());
        startDateField.setHint(getResources().getString(R.string.startDate));

        //endDate
        final EditText endDateField = new EditText(this);
        endDateField.setTextSize(15);
        endDateField.setId(View.generateViewId());
        endDateField.setHint(getResources().getString(R.string.endDate));

        //patternName
        final EditText patternNameField = new EditText(this);
        patternNameField.setTextSize(15);
        patternNameField.setId(View.generateViewId());
        patternNameField.setHint(getResources().getString(R.string.patternName));

        //projectName
        final EditText projectNameField = new EditText(this);
        projectNameField.setTextSize(15);
        projectNameField.setId(View.generateViewId());
        projectNameField.setHint(getResources().getString(R.string.projectName));

        //yarnName
        final EditText yarnNameField = new EditText(this);
        yarnNameField.setTextSize(15);
        yarnNameField.setId(View.generateViewId());
        yarnNameField.setHint(getResources().getString(R.string.yarnName));

        //totalYardage
        final EditText totalYardageField = new EditText(this);
        totalYardageField.setTextSize(15);
        totalYardageField.setId(View.generateViewId());
        totalYardageField.setInputType(InputType.TYPE_CLASS_NUMBER);

        //yardageUsed
        final EditText yardageUsedField = new EditText(this);
        yardageUsedField.setTextSize(15);
        yardageUsedField.setId(View.generateViewId());
        yardageUsedField.setInputType(InputType.TYPE_CLASS_NUMBER);

        //skeins
        final EditText skeinsField = new EditText(this);
        skeinsField.setTextSize(15);
        skeinsField.setId(View.generateViewId());
        skeinsField.setInputType(InputType.TYPE_CLASS_NUMBER);

        //colorWay
        final EditText colorWayField = new EditText(this);
        colorWayField.setTextSize(15);
        colorWayField.setId(View.generateViewId());
        colorWayField.setHint(getResources().getString(R.string.colorWayName));

        //note
        final EditText noteField = new EditText(this);
        noteField.setTextSize(15);
        noteField.setId(View.generateViewId());
        noteField.setHint(getResources().getString(R.string.notesName));

        //size
        final EditText sizeField = new EditText(this);
        sizeField.setTextSize(15);
        sizeField.setId(View.generateViewId());
        sizeField.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //layout params ============================================================================

        //project label
        RelativeLayout.LayoutParams projectLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectLabelParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        projectLabelParams.setMargins(15, 10, 10, 10);
        projectLabel.setLayoutParams(projectLabelParams);

        RelativeLayout.LayoutParams projectLineParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 5);
        projectLineParams.addRule(RelativeLayout.BELOW, projectLabel.getId());
        projectLine.setLayoutParams(projectLineParams);

        //project name
        RelativeLayout.LayoutParams projectNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectNameParams.addRule(RelativeLayout.BELOW, projectLine.getId());
        projectNameParams.setMargins(15, 10, 10, 10);
        projectNameField.setLayoutParams(projectNameParams);

        //pattern name
        RelativeLayout.LayoutParams patternNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        patternNameParams.addRule(RelativeLayout.BELOW, projectNameField.getId());
        patternNameParams.setMargins(15, 10, 10, 10);
        patternNameField.setLayoutParams(patternNameParams);

        //start date
        RelativeLayout.LayoutParams startParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        startParams.addRule(RelativeLayout.BELOW, patternNameField.getId());
        startParams.setMargins(15, 10, 10, 10);
        startDateField.setLayoutParams(startParams);

        //end date
        RelativeLayout.LayoutParams endParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        endParams.addRule(RelativeLayout.BELOW, startDateField.getId());
        endParams.setMargins(15, 10, 10, 10);
        endDateField.setLayoutParams(endParams);

        //size label
        RelativeLayout.LayoutParams sizeLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        sizeLabelParams.addRule(RelativeLayout.BELOW, endDateField.getId());
        sizeLabelParams.setMargins(15, 10, 10, 10);
        sizeLabel.setLayoutParams(sizeLabelParams);

        //size field
        RelativeLayout.LayoutParams sizeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        sizeParams.addRule(RelativeLayout.BELOW, sizeLabel.getId());
        sizeParams.setMargins(15, 10, 10, 10);
        sizeField.setLayoutParams(sizeParams);

        //yardage label
        RelativeLayout.LayoutParams yardLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yardLabelParams.addRule(RelativeLayout.BELOW, sizeField.getId());
        yardLabelParams.setMargins(15, 10, 10, 10);
        yardsUsedLabel.setLayoutParams(yardLabelParams);

        //yardage field
        RelativeLayout.LayoutParams yardsUsedParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yardsUsedParams.addRule(RelativeLayout.BELOW, yardsUsedLabel.getId());
        yardsUsedParams.setMargins(15, 10, 10, 10);
        yardageUsedField.setLayoutParams(yardsUsedParams);

        //Yarn Label
        RelativeLayout.LayoutParams yarnLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yarnLabelParams.addRule(RelativeLayout.BELOW, yardageUsedField.getId());
        yarnLabelParams.setMargins(15, 10, 10, 10);
        yarnLabel.setLayoutParams(yarnLabelParams);

        RelativeLayout.LayoutParams yarnLineParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 5);
        yarnLineParams.addRule(RelativeLayout.BELOW, yarnLabel.getId());
        yarnLine.setLayoutParams(yarnLineParams);

        //yarn name
        RelativeLayout.LayoutParams yarnNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yarnNameParams.addRule(RelativeLayout.BELOW, yarnLabel.getId());
        yarnNameParams.setMargins(15, 10, 10, 10);
        yarnNameField.setLayoutParams(yarnNameParams);

        //colorway
        RelativeLayout.LayoutParams colorwayParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        colorwayParams.addRule(RelativeLayout.BELOW, yarnNameField.getId());
        colorwayParams.setMargins(15, 10, 10, 10);
        colorWayField.setLayoutParams(colorwayParams);

        //total yardage label
        RelativeLayout.LayoutParams totalLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        totalLabelParams.addRule(RelativeLayout.BELOW, colorWayField.getId());
        totalLabelParams.setMargins(15, 10, 10, 10);
        totalYardageLabel.setLayoutParams(totalLabelParams);

        //total yardage field
        RelativeLayout.LayoutParams totalParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        totalParams.addRule(RelativeLayout.BELOW, totalYardageLabel.getId());
        totalParams.setMargins(15, 10, 10, 10);
        totalYardageField.setLayoutParams(totalParams);

        //total skeins label
        RelativeLayout.LayoutParams skeinsLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        skeinsLabelParams.addRule(RelativeLayout.BELOW, totalYardageField.getId());
        skeinsLabelParams.setMargins(15, 10, 10, 10);
        skeinsLabel.setLayoutParams(skeinsLabelParams);

        //total skeins field
        RelativeLayout.LayoutParams skeinsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        skeinsParams.addRule(RelativeLayout.BELOW, skeinsLabel.getId());
        skeinsParams.setMargins(15, 10, 10, 10);
        skeinsField.setLayoutParams(skeinsParams);

        //Note Label
        RelativeLayout.LayoutParams noteLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        noteLabelParams.addRule(RelativeLayout.BELOW, skeinsField.getId());
        noteLabelParams.setMargins(15, 10, 10, 10);
        notesLabel.setLayoutParams(noteLabelParams);

        RelativeLayout.LayoutParams noteLineParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 5);
        noteLineParams.addRule(RelativeLayout.BELOW, notesLabel.getId());
        yarnLine.setLayoutParams(noteLineParams);

        //note field
        RelativeLayout.LayoutParams noteParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        noteParams.addRule(RelativeLayout.BELOW, notesLabel.getId());
        noteParams.setMargins(15, 10, 10, 10);
        noteField.setLayoutParams(noteParams);



        //Done button ==============================================================================
        Button doneButton = new Button(this);
        doneButton.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        doneButton.setText(getResources().getString(R.string.done));
        doneButton.setId(View.generateViewId());
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate = startDateField.getText().toString();
                endDate = endDateField.getText().toString();
                patternName = patternNameField.getText().toString();
                yarnName = yarnNameField.getText().toString();
                projectName = projectNameField.getText().toString();
                colorWay = colorWayField.getText().toString();
                note = noteField.getText().toString();
                totalYardage = Integer.parseInt(totalYardageField.getText().toString());
                yardageUsed = Integer.parseInt(yardageUsedField.getText().toString());
                totalSkeins = Integer.parseInt(skeinsField.getText().toString());
                size = Float.parseFloat(sizeField.getText().toString());

                Project project = new Project();
                project.setStartDate(startDate);
                project.setEndDate(endDate);
                project.setPatternName(patternName);
                project.setYarnName(yarnName);
                project.setProjectName(projectName);
                project.setColorWay(colorWay);
                project.setNote(note);
                project.setTotalYardage(totalYardage);
                project.setYardageUsed(yardageUsed);
                project.setSkeins(totalSkeins);
                project.setSize(size);

                Intent projectData = new Intent(CreateProjectActivity.this, MainActivity.class);
                projectData.putExtra("project", project);
                setResult(RESULT_OK, projectData);
                finish();
            }
        });

        // add views to relative layout ===========================================================
        rel.addView(projectLabel);
        rel.addView(projectLine);
        rel.addView(projectNameField);
        rel.addView(patternNameField);
        rel.addView(startDateField);
        rel.addView(endDateField);
        rel.addView(sizeLabel);
        rel.addView(sizeField);
        rel.addView(yardsUsedLabel);
        rel.addView(yardageUsedField);
        rel.addView(yarnLabel);
        rel.addView(yarnLine);
        rel.addView(yarnNameField);
        rel.addView(colorWayField);
        rel.addView(totalYardageLabel);
        rel.addView(totalYardageField);
        rel.addView(skeinsLabel);
        rel.addView(skeinsField);
        rel.addView(notesLabel);
        rel.addView(noteLine);
        rel.addView(noteField);

        main.addView(rel);
        main.addView(doneButton);

    }
}
