package com.example.androidtestapp.app.services;


import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.example.androidtestapp.app.Constants;
import com.example.androidtestapp.app.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataFetcherService extends IntentService {

    private static final String LOG_TAG = "DataFetcherService";
    private static final String JSON_TEST_FILE = "http://10.56.140.56:7878/test";

    public DataFetcherService() {
        super("DataFetcherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //String dataString = intent.getDataString();

        //JSONObject result = fetch(JSON_TEST_FILE);
        String result = fakeFetch(JSON_TEST_FILE);
        Intent broadcastIntent = new Intent(Constants.BROADCAST_ACTION).putExtra(Constants.STATUS, result);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
    }

    private String fakeFetch(String url) {
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.news)));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return responseStrBuilder.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private String fetch(String url) {
        InputStream inputStream;
        //JSONObject result = new JSONObject();
        String result = "";
        try {
            Log.i(LOG_TAG, "*** Connecting: "+url);

            int timeout = 3000;
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, timeout);

            int timeoutSocket = 5000;
            HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);

            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpResponse httpResponse = httpClient.execute(new HttpGet(url));

            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null) {
                Log.i(LOG_TAG, "*** Getting result");
                //result = convertInputStreamToJson(inputStream);





                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                result = responseStrBuilder.toString();



            } else {
                throw new Exception("HTTP GET did not work");
            }

        } catch (Exception e) {
            Log.d(LOG_TAG, e.getLocalizedMessage());
        }

        return result;
    }

    private JSONObject convertInputStreamToJson(InputStream inputStream) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);

        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(responseStrBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
