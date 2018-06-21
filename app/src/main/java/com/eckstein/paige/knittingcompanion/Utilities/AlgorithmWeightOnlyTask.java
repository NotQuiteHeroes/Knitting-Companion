package com.eckstein.paige.knittingcompanion.Utilities;

import android.os.AsyncTask;

import com.eckstein.paige.knittingcompanion.Yarn.YarnList;
import com.eckstein.paige.knittingcompanion.Yarn.YarnSearchActivity;

public class AlgorithmWeightOnlyTask extends AsyncTask {

    // The task that will run the (weight/yardage only) knapsack algorithm on a background thread

    private YarnSearchActivity context;
    private YarnList yarns;

    public AlgorithmWeightOnlyTask(YarnSearchActivity context, YarnList yarns) {
        this.context = context;
        this.yarns = yarns;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return yarns;
    }

    @Override
    protected void onPostExecute(Object result) {
        context.handleResult((YarnList) result);
    }
}

