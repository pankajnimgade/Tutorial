package spinner.list.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.nimgade.pk.mytutorialapplication.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

public class SpinnerLoadFromNetworkActivity extends AppCompatActivity {

    private static final String TAG = "SpinnerLoadFromNetwork";
    private ProgressDialog progressDialog;

    private Spinner spinner;
    private ArrayList<Country> countries;
    private ArrayAdapter<Country> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_load_from_network);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");

        spinner = (Spinner) findViewById(R.id.SpinnerLoadFromNetworkActivity_spinner);

//        use_AsyncTask();
        ues_OkHttp();
    }

    private void ues_OkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("http://leansigmaway.com/api/ca_api/api.php?type=getCountries").build();
        progressDialog.show();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "onFailure: ");
                progressDialog.dismiss();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json_response = response.body().string();
                Log.d(TAG, "onResponse: \n"+json_response);
                try {
                    JSONObject jsonObject = new JSONObject(json_response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    Type type = new TypeToken<ArrayList<Country>>(){}.getType();
                    countries = (new Gson()).fromJson(jsonArray.toString(), type);
                    adapter = new ArrayAdapter<Country>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, countries);
                    SpinnerLoadFromNetworkActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            spinner.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void use_AsyncTask() {
        new MyAsyncTask().execute();
    }


    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://leansigmaway.com/api/ca_api/api.php?type=getCountries");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                String result = IOUtils.toString(httpURLConnection.getInputStream());
                Log.d(TAG, ": \n" + result);
                JSONObject jsonObject = new JSONObject(result);
                String countryList = jsonObject.getJSONArray("result").toString();
                Type token = new TypeToken<ArrayList<Country>>() {
                }.getType();
                countries = (new Gson()).fromJson(countryList, token);

                for (Country country : countries) {
                    Log.d(TAG, ": id: " + country.getCountryID() + " name: " + country.getCountryName());
                }
                 adapter =
                        new ArrayAdapter<Country>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, countries);
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            spinner.setAdapter(adapter);
        }
    }

    private class Country implements Comparator<Country> {

        @SerializedName("id")
        private int countryID;

        @SerializedName("name")
        private String countryName;

        public Country() {
        }

        public Country(int countryID, String countryName) {
            this.countryID = countryID;
            this.countryName = countryName;
        }

        public int getCountryID() {
            return countryID;
        }

        public void setCountryID(int countryID) {
            this.countryID = countryID;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        @Override
        public String toString() {
            return countryName;
        }


        @Override
        public int compare(Country lhs, Country rhs) {
            return lhs.getCountryName().compareTo(rhs.getCountryName());
        }
    }

}
