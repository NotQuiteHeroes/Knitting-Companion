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

public class GetDetailedYarnsTask extends AsyncTask {

    // Context to affect UI (show toasts, show/hide progress bar etc.)
    private YarnSearchActivity context;
    // Pattern ids we're looking for
    private String ids;

    //GSONconverter to we can parse date
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy/MM/dd")
            .create();

    // We use Retrofit to easily connect to the API.
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.ravelry.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public GetDetailedYarnsTask(YarnSearchActivity context, String ids) {
        this.context = context;
        this.ids = ids;
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

        Call<FullYarnsResult> call = service.getYarnsById(ids, authHeader);

        try {
            Response<FullYarnsResult> response = call.execute();
            if (response.isSuccessful()) {
                Log.i(this.getClass().toString(), "Response successful!");
            } else {
                Log.e(this.getClass().toString(), "Response unsuccessful: " + response.errorBody().string());
            }
            return response.body();
        } catch (IOException e) {
            Log.e(this.getClass().toString(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        if (result == null || !result.getClass().equals(FullYarnsResult.class)) {
            context.handleFailedAsyncTask();
            Log.e(this.getClass().toString(), "Result was not a FullPatternsResult object!");
        } else {
            context.handleResult((FullYarnsResult) result);
        }
    }
}

