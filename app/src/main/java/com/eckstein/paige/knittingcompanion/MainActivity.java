package com.eckstein.paige.knittingcompanion;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity{

    ArrayList<Project> allProjects;
    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allProjects = new ArrayList<>();
        main = findViewById(R.id.mainLayout);

        for(Project project: allProjects) {
            updateUI(project);
        }

        //new project button
        Button newProject = new Button(this);
        newProject.setText(getResources().getString(R.string.newProject));
        newProject.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        newProject.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        newProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateProjectActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        main.addView(newProject);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                Bundle bundle = data.getExtras();
                if(bundle != null)
                {
                    Project project = bundle.getParcelable("project");
                    allProjects.add(project);
                    updateUI(project);
                }
            }
        }
    }

    public void updateUI(Project project)
    {
        //create new RelativeLayout for each project
        RelativeLayout rel = new RelativeLayout(this);
        //projectName set up
        TextView projectName = new TextView(this);
        projectName.setTextSize(20);
        projectName.setId(View.generateViewId());
        projectName.setText(project.getProjectName());
        //pattern name set up
        TextView patternName = new TextView(this);
        patternName.setTextSize(18);
        patternName.setId(View.generateViewId());
        patternName.setText(project.getPatternName());
        //view button set up
        Button viewProject = new Button(this);
        viewProject.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        viewProject.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        viewProject.setText(getResources().getString(R.string.view));
        viewProject.setId(View.generateViewId());

        //layout params for project name
        RelativeLayout.LayoutParams projectParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        projectParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        projectParams.setMargins(15, 10, 10, 10);
        projectName.setLayoutParams(projectParams);

        //layout params for pattern name
        RelativeLayout.LayoutParams patternParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        patternParams.addRule(RelativeLayout.BELOW, projectName.getId());
        patternParams.setMargins(15, 10, 10, 10);
        patternName.setLayoutParams(patternParams);

        //layout params for button
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        buttonParams.setMargins(10, 10, 10, 10);
        viewProject.setLayoutParams(buttonParams);

        //draw line between each project
        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));

        //add text views and button to relative layout
        rel.addView(projectName);
        rel.addView(patternName);
        rel.addView(viewProject);
        //add relative layout to main linear layout
        main.addView(rel);
        main.addView(v);
    }
}
