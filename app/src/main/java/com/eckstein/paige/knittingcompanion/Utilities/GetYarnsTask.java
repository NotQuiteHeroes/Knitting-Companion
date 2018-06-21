package com.eckstein.paige.knittingcompanion.Utilities;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.eckstein.paige.knittingcompanion.R;
import com.eckstein.paige.knittingcompanion.Yarn.YarnSearchActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetYarnsTask extends AsyncTask {

    // Context to affect UI (show toasts, show/hide progress bar etc.)
    private YarnSearchActivity context;
    private String yarnName;

    //GSONconverter to we can parse date
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy/MM/dd")
            .create();

    // We use Retrofit to easily connect to the API.
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.ravelry.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public GetYarnsTask(YarnSearchActivity context, String search) {
        this.context = context;
        yarnName = search;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        RavAPI service = retrofit.create(RavAPI.class);
        // Using HTTP basic authentication
        String keys = context.getString(R.string.API_username) + ":" + context.getString(R.string.API_password);
        String authHeader = "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP);
        if (authHeader.length() == 1) {
            Log.e(this.getClass().toString(), "API keys missing.");
            return null;
        }

        Call<YarnSearchResult> call = service.getYarns(yarnName, authHeader);

        try {
            Response<YarnSearchResult> response = call.execute();
            if (response.isSuccessful()) {
                // yay success
            } else {
                // oh no
            }
            return response.body();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        if (result == null || !result.getClass().equals(YarnSearchResult.class)) {
            context.handleFailedAsyncTask();
            Log.e(this.getClass().toString(), "Result was not a PatternsSearchResult object!");
        } else {
            context.handleResult((YarnSearchResult) result);
        }
    }
}

