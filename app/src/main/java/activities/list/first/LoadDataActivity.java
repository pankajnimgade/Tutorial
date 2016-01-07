package activities.list.first;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LoadDataActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<WorldPopulation> worldPopulations;

    private TextView rank_TextView;
    private TextView country_TextView;
    private TextView population_TextView;
    private TextView url_TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner) findViewById(R.id.LoadDataActivity_spinner);

        rank_TextView = (TextView) findViewById(R.id.LoadDataActivity_rank_textView);
        country_TextView = (TextView) findViewById(R.id.LoadDataActivity_country_textView);
        population_TextView = (TextView) findViewById(R.id.LoadDataActivity_population_textView);
        url_TextView = (TextView) findViewById(R.id.LoadDataActivity_url_textView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                WorldPopulation population = (WorldPopulation) spinner.getItemAtPosition(position);
                rank_TextView.setText("" + population.getRank());
                country_TextView.setText("" + population.getCountry());
                population_TextView.setText("" + population.getPopulation());
                url_TextView.setText("" + population.getFlag());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new DownloadData().execute();
    }


    private class DownloadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                worldPopulations = new ArrayList<>();
                URL url = new URL("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                String result = IOUtils.toString(httpURLConnection.getInputStream());
                System.out.println("" + result);
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("worldpopulation");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject singleItem = jsonArray.getJSONObject(i);
                    WorldPopulation population = new WorldPopulation();
                    population.rank = singleItem.getString("rank");
                    population.country = singleItem.getString("country");
                    population.population = singleItem.getString("population");
                    population.flag = singleItem.getString("flag");
                    worldPopulations.add(population);
                }

                Log.d("TAG", "" + result);
                Log.d("TAG", "size: " + worldPopulations.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayAdapter<WorldPopulation> adapter = new ArrayAdapter<WorldPopulation>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, worldPopulations);
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            spinner.setAdapter(adapter);
        }
    }

    class WorldPopulation {
        private String rank;
        private String country;
        private String population;
        private String flag;

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPopulation() {
            return population;
        }

        public void setPopulation(String population) {
            this.population = population;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        @Override
        public String toString() {
            return country;
        }
    }

}
