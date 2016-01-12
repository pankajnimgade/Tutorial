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
import java.util.HashMap;

public class ListViewTestOneActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<LearningStandards> learningStandardses;
    private ArrayList<MyGroup> myGroups;
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
//        new DownloadJSON().execute();
        new DownloadAndPrepareGroupJSON().execute();
    }

    private class DownloadAndPrepareGroupJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                myGroups = new ArrayList<>();
                URL url = new URL("http://54.152.108.131/iphone111/getLearningStandards");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                String result = IOUtils.toString(httpURLConnection.getInputStream());
                System.out.println("" + result);

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("LearningStandards");
                HashMap<String, MyGroup> stringMyGroupHashMap = new HashMap<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject singleObject = jsonArray.getJSONObject(i);
                    JSONObject learningStandardsJsonObject = singleObject.getJSONObject("LearningStandards");
                    LearningStandards learningStandards = new LearningStandards();
                    if (!stringMyGroupHashMap.containsKey(learningStandardsJsonObject.getString("standard_title"))) {
                        MyGroup myGroup = new MyGroup();
                        LearningStandards single_learningStandards = new LearningStandards();
                        single_learningStandards.setStandard_title(learningStandardsJsonObject.getString("standard_title"));
                        single_learningStandards.setRef_id(learningStandardsJsonObject.getString("ref_id"));
                        single_learningStandards.setStandard_title(learningStandardsJsonObject.getString("description"));
                        myGroup.getLearningStandards().add(single_learningStandards);
                        myGroup.setStandard_title(learningStandardsJsonObject.getString("standard_title"));
                        stringMyGroupHashMap.put(learningStandardsJsonObject.getString("standard_title"), myGroup);
                    } else {
                        MyGroup myGroup = stringMyGroupHashMap.get(learningStandardsJsonObject.getString("standard_title"));
                        LearningStandards single_learningStandards = new LearningStandards();
                        single_learningStandards.setStandard_title(learningStandardsJsonObject.getString("standard_title"));
                        single_learningStandards.setRef_id(learningStandardsJsonObject.getString("ref_id"));
                        single_learningStandards.setStandard_title(learningStandardsJsonObject.getString("description"));
                        myGroup.getLearningStandards().add(single_learningStandards);
                    }

                }

                for (MyGroup myGroup : stringMyGroupHashMap.values()) {
                    myGroups.add(myGroup);
                }
                System.out.println("Size of hashMap: " + stringMyGroupHashMap.size());
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
            ArrayAdapter<MyGroup> adapter =
                    new ArrayAdapter<MyGroup>(getApplicationContext(), R.layout.simple_list_item_1, myGroups);
            listView.setAdapter(adapter);
        }
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

    private class MyGroup {

        private ArrayList<LearningStandards> learningStandards;
        private String standard_title;

        public MyGroup() {
            this.learningStandards = new ArrayList<>();
            this.standard_title = "";
        }

        public ArrayList<LearningStandards> getLearningStandards() {
            return learningStandards;
        }

        public void setLearningStandards(ArrayList<LearningStandards> learningStandards) {
            this.learningStandards = learningStandards;
        }

        public String getStandard_title() {
            return standard_title;
        }

        public void setStandard_title(String standard_title) {
            this.standard_title = standard_title;
        }

        @Override
        public String toString() {
            return standard_title;
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
