package listview.test.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListVewTestThreeActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Entity> entities;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vew_test_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.ListVewTestThreeActivity_listView);
        entities = new ArrayList<>();
        myAdapter = new MyAdapter(getApplicationContext(), R.layout.simple_list_item_1, entities);
        listView.setAdapter(myAdapter);

        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        private ArrayList<Entity> tempEntities;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tempEntities = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                URL url = new URL("http://api.androidhive.info/feed/feed.json");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                String result = IOUtils.toString(httpURLConnection.getInputStream());
                System.out.println("" + result);

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("feed");

                for (int i = 0; i < jsonArray.length(); i++) {
                    Entity entity = new Entity();
                    entity.setId(jsonArray.getJSONObject(i).getString("id"));
                    entity.setName(jsonArray.getJSONObject(i).getString("name"));
                    entity.setUrl(jsonArray.getJSONObject(i).getString("image"));
                    System.out.println(""+entity.getName());
                    tempEntities.add(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (tempEntities != null) {
                entities.addAll(tempEntities);
                System.out.println("Size: "+entities.size());
                myAdapter.notifyDataSetChanged();
            }

        }
    }


    private class MyAdapter extends ArrayAdapter {

        private ArrayList<Entity> a_productInfos;
        private Context a_context;
        private LayoutInflater a_layoutInflater;

        public MyAdapter(Context context, int resource, ArrayList<Entity> a_productInfos) {
            super(context, resource, a_productInfos);
            this.a_productInfos = a_productInfos;
            this.a_context = context;
            a_layoutInflater = LayoutInflater.from(this.a_context);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                row = a_layoutInflater.inflate(R.layout.single_item_listview_test_three, parent, false);
                holder = new ViewHolder();
                holder.id = (TextView) row.findViewById(R.id.ListVewTestThreeActivity_id_textView);
                holder.name = (TextView) row.findViewById(R.id.ListVewTestThreeActivity_name_textView);
                holder.url = (TextView) row.findViewById(R.id.ListVewTestThreeActivity_url_textView);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final Entity productInfo = a_productInfos.get(position);
            holder.id.setText("" + productInfo.getId());
            holder.name.setText("" + productInfo.getName());
            holder.url.setText("" + productInfo.getUrl());
            return row;
        }

        class ViewHolder {
            TextView id;
            TextView name;
            TextView url;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }
    }

    public class Entity {

        private String id;
        private String name;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
