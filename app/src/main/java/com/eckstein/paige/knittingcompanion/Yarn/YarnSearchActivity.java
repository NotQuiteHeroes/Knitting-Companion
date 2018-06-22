package com.eckstein.paige.knittingcompanion.Yarn;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity;
import com.eckstein.paige.knittingcompanion.MainActivity;
import com.eckstein.paige.knittingcompanion.R;
import com.eckstein.paige.knittingcompanion.Utilities.AlgorithmWeightOnlyTask;
import com.eckstein.paige.knittingcompanion.Utilities.FullYarnsResult;
import com.eckstein.paige.knittingcompanion.Utilities.GetDetailedYarnsTask;
import com.eckstein.paige.knittingcompanion.Utilities.GetYarnsTask;
import com.eckstein.paige.knittingcompanion.Utilities.YarnSearchResult;

/**
 * Activity to search for Yarn through Ravelry
 * Credit to Sofivanhanen and her code, from which this was edited
 * From https://github.com/sofivanhanen/Yarnie
 */
public class YarnSearchActivity extends BaseActivity {

    LinearLayout main;
    RelativeLayout rel;
    EditText yarnNameField;
    ProgressBar progressBar;
    TextView resultTextView;
    ScrollView resultView;

    AsyncTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = findViewById(R.id.mainLayout);
        rel = new RelativeLayout(this);
        resultView = new ScrollView(this);

        TextView yarnNameLabel = new TextView(this);
        yarnNameLabel.setText(getResources().getString(R.string.yarnNameLabel));
        yarnNameLabel.setTextSize(25);
        yarnNameLabel.setId(View.generateViewId());

        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));

        yarnNameField = new EditText(this);
        yarnNameField.setTextSize(30);
        yarnNameField.setTypeface(getResources().getFont(R.font.homeplanet));
        yarnNameField.setId(View.generateViewId());
        yarnNameField.setHint(getResources().getString(R.string.yarnName));


        progressBar = new ProgressBar(this);
        progressBar.setId(View.generateViewId());
        progressBar.setVisibility(View.GONE);

        resultTextView = new TextView(this);
        resultTextView.setTextSize(32);
        resultTextView.setTextColor(Color.BLACK);
        resultTextView.setTypeface(getResources().getFont(R.font.homeplanet));
        resultTextView.setId(View.generateViewId());

        Button done = new Button(this);
        done.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        done.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        done.setText(getResources().getString(R.string.done));
        done.setId(View.generateViewId());
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YarnSearchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button search = new Button(this);
        search.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPink));
        search.setTextColor(ContextCompat.getColor(this, R.color.offWhite));
        search.setText(getResources().getString(R.string.search));
        search.setId(View.generateViewId());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task != null) return; // There's a task running already.
                //get name of yarn to search for
                String name = yarnNameField.getText().toString();
                if (name.equals("")) {
                    // User didn't input amount of yarn!
                    makeToast("Please give name of yarn");
                    return;
                }
                // Start the API request
                progressBar.setVisibility(View.VISIBLE);
                task = new GetYarnsTask(YarnSearchActivity.this, name);
                task.execute();
            }
        });

        RelativeLayout.LayoutParams yarnNameLabelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        yarnNameLabelParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        yarnNameLabelParams.setMargins(15, 10, 10, 10);
        yarnNameLabel.setLayoutParams(yarnNameLabelParams);

        RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        nameParams.addRule(RelativeLayout.BELOW, yarnNameLabel.getId());
        nameParams.setMargins(15, 10, 10, 10);
        yarnNameField.setLayoutParams(nameParams);


        RelativeLayout.LayoutParams searchParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        searchParams.addRule(RelativeLayout.BELOW, yarnNameField.getId());
        searchParams.setMargins(15, 10, 10, 10);
        search.setLayoutParams(searchParams);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.BELOW, search.getId());
        buttonParams.setMargins(15, 10, 10, 10);
        done.setLayoutParams(buttonParams);

        RelativeLayout.LayoutParams progressParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        progressParams.addRule(RelativeLayout.BELOW, done.getId());
        progressParams.setMargins(15, 10, 10, 10);
        progressBar.setLayoutParams(progressParams);

        RelativeLayout.LayoutParams resultParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        resultParams.setMargins(15, 10, 10, 10);
        resultTextView.setLayoutParams(resultParams);

        resultView.addView(resultTextView);

        rel.addView(yarnNameLabel);
        rel.addView(v);
        rel.addView(yarnNameField);
        rel.addView(search);
        rel.addView(done);
        rel.addView(progressBar);

        main.addView(rel);
        main.addView(resultView);
    }

    public void makeToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    // handleResult is only called after a successful query.
    // GetYarnsTask returns YarnSearchResult.
    public void handleResult(YarnSearchResult result) {
        // GetYarnsTask is useful for looking up yarns by parameters.
        // However, the results are insufficient Yarn objects.
        // They do not contain all the details (for instance, the yardage.)
        // Therefore, we request new, detailed versions of those yarns,
        // searching by id.
        // This is a weakness in the Ravelry API.
        task = new GetDetailedYarnsTask(this, result.getIdsAsString());
        task.execute();
    }

    // GetDetailedYarnsTask returns FullYarnsResult.
    public void handleResult(FullYarnsResult result) {
        // We run the algorithm on a separate thread so as to not block the UI.

        task = new AlgorithmWeightOnlyTask(this,
                result.getYarnAsList());

        task.execute();
    }

    // AlgorithmWeightOnlyTask returns a list of yarns.
    public void handleResult(YarnList result) {
        progressBar.setVisibility(View.GONE);
        printListOfPatterns(result);
        task = null;
    }

    /**
     * print all yarn information returned by Ravelry search
     * @param yarns List of Yarn objects returned by Ravelry search
     */
    private void printListOfPatterns(YarnList yarns) {
        StringBuilder string = new StringBuilder();
        if (yarns.isEmpty()) {
            string.append("No yarns! Try a different name.");
        } else {
            for (Yarn yarn : yarns) {
                string.append(yarn.getCompanyName() + "\n");
                string.append(yarn.getName() + "\n");
                string.append("Discontinued: " + yarn.getDiscontinued() + "\n");
                string.append("Grams: " + yarn.getGrams() + "\n");
                string.append("Average Rating: " + yarn.getRating_average() + "\n");
                string.append("WPI: " + yarn.getWpi() + "\n");
                string.append("Yardage: " + yarn.getYardage() + "\n");
            }
        }
        setResultText(string.toString());
    }

    // For general API failures
    public void handleFailedAsyncTask() {
        handleFailedAsyncTask("Connecting to the API failed.");
    }

    // For specific API failure messages
    public void handleFailedAsyncTask(String message) {
        progressBar.setVisibility(View.GONE);
        makeToast(message);
        task = null;
    }

    private void setResultText(String text) {
        resultTextView.clearAnimation();
        resultTextView.setText(text);
    }
}



