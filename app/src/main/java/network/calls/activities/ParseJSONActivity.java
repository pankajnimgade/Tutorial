package network.calls.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ParseJSONActivity extends AppCompatActivity {

    private Button button;
    private ListView listView;
    private ArrayList<People> peoples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        button = (Button) findViewById(R.id.ParseJSONActivity_ok_button);
        listView = (ListView) findViewById(R.id.ParseJSONActivity_listView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadJSON().execute();
            }
        });
    }


    private class DownloadJSON extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL("http://www.humanfox.com/capsule/dash.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                String result = IOUtils.toString(httpURLConnection.getInputStream());
                System.out.println("" + result);
                if (result != null) {
                    JSONObject result_jsonObject = new JSONObject(result);
                    JSONArray result_JsonArray = result_jsonObject.getJSONArray("result");
                    if (result_JsonArray != null) {
                        if (result_JsonArray.length() > 0) {
                            peoples = new ArrayList<>();
                            for (int i = 0; i < result_JsonArray.length(); i++) {
                                People people = new People();
                                JSONObject jsonObject = result_JsonArray.getJSONObject(i);
                                people.setId("" + jsonObject.getString("id"));
                                people.setName("" + jsonObject.getString("Name"));
                                people.setProfession("" + jsonObject.getString("profession"));
                                people.setImage("" + jsonObject.getString("image"));
                                peoples.add(people);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (peoples != null) {
                if (peoples.size() > 0) {
                    ArrayAdapter<People> adapter =
                            new ArrayAdapter<People>(getApplicationContext(), R.layout.simple_list_item_1, peoples);
                    listView.setAdapter(adapter);

                }
            }
        }
    }

    private class People {
        private String id;
        private String Name;
        private String profession;
        private String image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return Name;
        }
    }

}
