package com.eckstein.paige.knittingcompanion.Projects;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.DatabaseHelpers.ProjectDBHelper;
import com.eckstein.paige.knittingcompanion.MainActivity;
import com.eckstein.paige.knittingcompanion.R;

import com.eckstein.paige.knittingcompanion.Counters.Counter;
import com.eckstein.paige.knittingcompanion.DatabaseHelpers.CounterDBHelper;

/**
 * Activity to get in depth view of individual project
 */
public class ViewProjectActivity extends BaseActivity {

    LinearLayout main;
    RelativeLayout rel;
    Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = findViewById(R.id.mainLayout);
        rel = new RelativeLayout(this);
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


        //field labels =============================================================================
        //project name label
        TextView projectNameLabel = new TextView(this);
        projectNameLabel.setTextSize(15);
        projectNameLabel.setId(View.generateViewId());
        projectNameLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        projectNameLabel.setText(getResources().getString(R.string.projectNameLabel));

        //pattern name label
        TextView patternNameLabel = new TextView(this);
        patternNameLabel.setTextSize(15);
        patternNameLabel.setId(View.generateViewId());
        patternNameLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        patternNameLabel.setText(getResources().getString(R.string.patternNameLabel));

        //start date label
        TextView startLabel = new TextView(this);
        startLabel.setTextSize(15);
        startLabel.setId(View.generateViewId());
        startLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        startLabel.setText(getResources().getString(R.string.startLabel));

        //end date label
        TextView endLabel = new TextView(this);
        endLabel.setTextSize(15);
        endLabel.setId(View.generateViewId());
        endLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        endLabel.setText(getResources().getString(R.string.endLabel));

        //yarn name label
        TextView yarnNameLabel = new TextView(this);
        yarnNameLabel.setTextSize(15);
        yarnNameLabel.setId(View.generateViewId());
        yarnNameLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        yarnNameLabel.setText(getResources().getString(R.string.yarnNameLabel));

        //colorway label
        TextView colorwayLabel = new TextView(this);
        colorwayLabel.setTextSize(15);
        colorwayLabel.setId(View.generateViewId());
        colorwayLabel.setTextColor(ContextCompat.getColor(this, R.color.darkPink));
        colorwayLabel.setText(getResources().getString(R.string.colorWayLabel));

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
        startDateField.setTypeface(getResources().getFont(R.font.mahoni));
        startDateField.setId(View.generateViewId());
        startDateField.setText(project.getStartDate());

        //endDate
        final TextView endDateField = new TextView(this);
        endDateField.setTextSize(15);
        endDateField.setTypeface(getResources().getFont(R.font.mahoni));
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
        for (int i = 0; i < size; i++) {
            noteField.append(project.getNote(i) + "\n");
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

        //project name label
        RelativeLayout.LayoutParams projectNameLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectNameLabelParams.addRule(RelativeLayout.BELOW, projectLine.getId());
        projectNameLabelParams.setMargins(15, 10, 10, 10);
        projectNameLabel.setLayoutParams(projectNameLabelParams);

        //project name
        RelativeLayout.LayoutParams projectNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectNameParams.addRule(RelativeLayout.BELOW, projectNameLabel.getId());
        projectNameParams.setMargins(15, 10, 10, 10);
        projectNameField.setLayoutParams(projectNameParams);

        //pattern name label
        RelativeLayout.LayoutParams patternNameLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        patternNameLabelParams.addRule(RelativeLayout.BELOW, projectNameField.getId());
        patternNameLabelParams.setMargins(15, 10, 10, 10);
        patternNameLabel.setLayoutParams(patternNameLabelParams);

        //pattern name
        RelativeLayout.LayoutParams patternNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        patternNameParams.addRule(RelativeLayout.BELOW, patternNameLabel.getId());
        patternNameParams.setMargins(15, 10, 10, 10);
        patternNameField.setLayoutParams(patternNameParams);

        //start date label
        RelativeLayout.LayoutParams startLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        startLabelParams.addRule(RelativeLayout.BELOW, patternNameField.getId());
        startLabelParams.setMargins(15, 10, 10, 10);
        startLabel.setLayoutParams(startLabelParams);

        //start date
        RelativeLayout.LayoutParams startParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        startParams.addRule(RelativeLayout.BELOW, startLabel.getId());
        startParams.setMargins(15, 10, 10, 10);
        startDateField.setLayoutParams(startParams);

        //end date label
        RelativeLayout.LayoutParams endLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        endLabelParams.addRule(RelativeLayout.BELOW, startDateField.getId());
        endLabelParams.setMargins(15, 10, 10, 10);
        endLabel.setLayoutParams(endLabelParams);

        //end date
        RelativeLayout.LayoutParams endParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        endParams.addRule(RelativeLayout.BELOW, endLabel.getId());
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

        //yarn name label
        RelativeLayout.LayoutParams yarnNameLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yarnNameLabelParams.addRule(RelativeLayout.BELOW, yarnLine.getId());
        yarnNameLabelParams.setMargins(15, 10, 10, 10);
        yarnNameLabel.setLayoutParams(yarnNameLabelParams);

        //yarn name
        RelativeLayout.LayoutParams yarnNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yarnNameParams.addRule(RelativeLayout.BELOW, yarnNameLabel.getId());
        yarnNameParams.setMargins(15, 10, 10, 10);
        yarnNameField.setLayoutParams(yarnNameParams);

        //colorway label
        RelativeLayout.LayoutParams colorwayLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        colorwayLabelParams.addRule(RelativeLayout.BELOW, yarnNameField.getId());
        colorwayLabelParams.setMargins(15, 10, 10, 10);
        colorwayLabel.setLayoutParams(colorwayLabelParams);

        //colorway
        RelativeLayout.LayoutParams colorwayParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        colorwayParams.addRule(RelativeLayout.BELOW, colorwayLabel.getId());
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
        noteLine.setLayoutParams(noteLineParams);

        //note field
        RelativeLayout.LayoutParams noteParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        noteParams.addRule(RelativeLayout.BELOW, notesLabel.getId());
        noteParams.setMargins(15, 10, 10, 10);
        noteField.setLayoutParams(noteParams);

        // add counter button =====================================================================
        Button addCounter = new Button(this);
        addCounter.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        addCounter.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        addCounter.setText(getResources().getString(R.string.addCounter));
        addCounter.setId(View.generateViewId());

        RelativeLayout.LayoutParams counterParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        counterParams.addRule(RelativeLayout.BELOW, noteField.getId());
        counterParams.setMargins(15, 10, 10, 10);
        addCounter.setLayoutParams(counterParams);

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
        Button editButton = new Button(this);
        editButton.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        editButton.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        editButton.setText(getResources().getString(R.string.edit));
        editButton.setId(View.generateViewId());

        RelativeLayout.LayoutParams editParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        editParams.addRule(RelativeLayout.BELOW, addCounter.getId());
        editParams.setMargins(15, 10, 10, 10);
        editButton.setLayoutParams(editParams);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectData = new Intent(ViewProjectActivity.this, EditProjectActivity.class);
                projectData.putExtra("project", finalProject);
                startActivityForResult(projectData, 1);
            }
        });

        // done button =============================================================================
        Button doneButton = new Button(this);
        doneButton.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        doneButton.setText(getResources().getString(R.string.done));
        doneButton.setId(View.generateViewId());

        RelativeLayout.LayoutParams doneParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        doneParams.addRule(RelativeLayout.BELOW, editButton.getId());
        doneParams.setMargins(15, 10, 10, 10);
        doneButton.setLayoutParams(doneParams);

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
        rel.addView(projectNameLabel);
        rel.addView(projectNameField);
        rel.addView(patternNameLabel);
        rel.addView(patternNameField);
        rel.addView(startLabel);
        rel.addView(startDateField);
        rel.addView(endLabel);
        rel.addView(endDateField);
        rel.addView(sizeLabel);
        rel.addView(sizeField);
        rel.addView(yardsUsedLabel);
        rel.addView(yardageUsedField);
        rel.addView(yarnLabel);
        rel.addView(yarnLine);
        rel.addView(yarnNameLabel);
        rel.addView(yarnNameField);
        rel.addView(colorwayLabel);
        rel.addView(colorWayField);
        rel.addView(totalYardageLabel);
        rel.addView(totalYardageField);
        rel.addView(skeinsLabel);
        rel.addView(skeinsField);
        rel.addView(notesLabel);
        rel.addView(noteLine);
        rel.addView(noteField);
        rel.addView(addCounter);
        rel.addView(editButton);
        rel.addView(doneButton);

        main.addView(rel);
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

        //get project fields
        projectName = project.getProjectName();
        patternName = project.getPatternName();
        yarnName = project.getYarnName();
        start = project.getStartDate();
        end = project.getEndDate();
        totalYards = String.valueOf(project.getTotalYardage());
        yardsUsed = String.valueOf(project.getYardageUsed());
        colorway = project.getColorWay();
        note = project.getNote(0);
        size = String.valueOf(project.getSize());
        skeins = String.valueOf(project.getSkeins());

        //update project in database
        db.updateProject(projectName, patternName, yarnName, start, end, totalYards, yardsUsed,
                skeins, colorway, note, size);
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
