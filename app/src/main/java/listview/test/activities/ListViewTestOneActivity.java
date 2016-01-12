package listview.test.activities;

import android.app.ProgressDialog;
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

public class ListViewTestOneActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<LearningStandards> learningStandardses;
    private ProgressDialog progressDialog;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Posting...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);

        listView = (ListView) findViewById(R.id.ListViewTestOneActivity_listView);
        learningStandardses = new ArrayList<>();
        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                URL url = new URL("http://54.152.108.131/iphone111/getLearningStandards");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                String result = IOUtils.toString(httpURLConnection.getInputStream());
                System.out.println("" + result);

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("LearningStandards");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject singleObject = jsonArray.getJSONObject(i);
                    JSONObject learningStandardsJsonObject = singleObject.getJSONObject("LearningStandards");
                    LearningStandards learningStandards = new LearningStandards();
                    learningStandards.setStandard_title("" + learningStandardsJsonObject.getString("standard_title"));
                    learningStandards.setRef_id("" + learningStandardsJsonObject.getString("ref_id"));
                    learningStandards.setDescription("" + learningStandardsJsonObject.getString("description"));
                    learningStandardses.add(learningStandards);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            System.out.println("learningStandardses: size: " + learningStandardses.size());
            ArrayAdapter<LearningStandards> adapter =
                    new ArrayAdapter<LearningStandards>(getApplicationContext(), R.layout.simple_list_item_1, learningStandardses);
            listView.setAdapter(adapter);
        }
    }

    private class LearningStandards {
        private String standard_title;
        private String ref_id;
        private String description;

        public String getStandard_title() {
            return standard_title;
        }

        public void setStandard_title(String standard_title) {
            this.standard_title = standard_title;
        }

        public String getRef_id() {
            return ref_id;
        }

        public void setRef_id(String ref_id) {
            this.ref_id = ref_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return standard_title;
        }
    }
}
