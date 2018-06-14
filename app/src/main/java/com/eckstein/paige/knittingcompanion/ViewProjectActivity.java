package com.eckstein.paige.knittingcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class ViewProjectActivity extends BaseActivity {

    LinearLayout main;
    RelativeLayout rel;
    Project project;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        main = findViewById(R.id.mainLayout);
        rel = new RelativeLayout(this);
        Bundle bundle = getIntent().getExtras();
        project = bundle.getParcelable("project");

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
        final TextView startDateField = new TextView(this);
        startDateField.setTextSize(15);
        startDateField.setId(View.generateViewId());
        startDateField.setText(project.getStartDate());

        //endDate
        final TextView endDateField = new TextView(this);
        endDateField.setTextSize(15);
        endDateField.setId(View.generateViewId());
        endDateField.setText(project.getEndDate());

        //patternName
        final TextView patternNameField = new TextView(this);
        patternNameField.setTextSize(15);
        patternNameField.setId(View.generateViewId());
        patternNameField.setText(project.getPatternName());

        //projectName
        final TextView projectNameField = new TextView(this);
        projectNameField.setTextSize(15);
        projectNameField.setId(View.generateViewId());
        projectNameField.setText(project.getProjectName());

        //yarnName
        final TextView yarnNameField = new TextView(this);
        yarnNameField.setTextSize(15);
        yarnNameField.setId(View.generateViewId());
        yarnNameField.setText(project.getYarnName());

        //totalYardage
        final TextView totalYardageField = new TextView(this);
        totalYardageField.setTextSize(15);
        totalYardageField.setTypeface(getResources().getFont(R.font.mahoni));
        totalYardageField.setId(View.generateViewId());
        totalYardageField.setText(String.valueOf(project.getTotalYardage()));

        //yardageUsed
        final TextView yardageUsedField = new TextView(this);
        yardageUsedField.setTextSize(15);
        yardageUsedField.setTypeface(getResources().getFont(R.font.mahoni));
        yardageUsedField.setId(View.generateViewId());
        yardageUsedField.setText(String.valueOf(project.getYardageUsed()));

        //skeins
        final TextView skeinsField = new TextView(this);
        skeinsField.setTextSize(15);
        skeinsField.setTypeface(getResources().getFont(R.font.mahoni));
        skeinsField.setId(View.generateViewId());
        skeinsField.setText(String.valueOf(project.getSkeins()));

        //colorWay
        final TextView colorWayField = new TextView(this);
        colorWayField.setTextSize(15);
        colorWayField.setId(View.generateViewId());
        colorWayField.setText(project.getColorWay());

        //note
        final TextView noteField = new TextView(this);
        noteField.setTextSize(15);
        noteField.setId(View.generateViewId());
        int size = project.getAllNotes().size();
        for(int i = 0; i < size; i++)
        {
            noteField.append(project.getNote(i)+"\n");
        }

        //size
        final TextView sizeField = new TextView(this);
        sizeField.setTextSize(15);
        sizeField.setTypeface(getResources().getFont(R.font.mahoni));
        sizeField.setId(View.generateViewId());
        sizeField.setText(String.valueOf(project.getSize()));

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
                Intent projectData = new Intent(ViewProjectActivity.this, MainActivity.class);
                startActivity(projectData);
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
