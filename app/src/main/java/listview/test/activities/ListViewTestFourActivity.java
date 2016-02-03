package listview.test.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListViewTestFourActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.ListViewTestFourActivity_listView);
        new DownloadJSON().execute();

    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                places = new ArrayList<>();
                URL url = new URL("http://14.140.200.186/Hospital/get_city.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                String result = IOUtils.toString(httpURLConnection.getInputStream());
                System.out.println("" + result);
                JSONObject result_JsonObject = new JSONObject(result);
                JSONArray place_JsonArray = result_JsonObject.getJSONArray("Cities");
                for (int i = 0; i < place_JsonArray.length(); i++) {
                    JSONObject single_Item = place_JsonArray.getJSONObject(i);
                    Place place = new Place();
                    place.setId("" + single_Item.getString("city_id"));
                    place.setId("" + single_Item.getString("city_name"));
                    places.add(place);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (places.size() > 0) {
                ArrayAdapter<Place> adapter =
                        new ArrayAdapter<Place>(getApplicationContext(), R.layout.simple_list_item_1, places);
                listView.setAdapter(adapter);
            }
        }
    }

    private class Place {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


