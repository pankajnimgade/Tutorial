package network.calls.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * { http://stackoverflow.com/questions/35004874/json-parsing-from-database }
 */
public class ParseJSONActivity extends AppCompatActivity {

    private Button button;
    private Button previous_Button;
    private Button next_Button;

    private ListView listView;
    private ArrayList<People> peoples;
    private MyAdapter adapter;
    private int TRACK = 0;

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
        previous_Button = (Button) findViewById(R.id.ParseJSONActivity_Previous_button);
        previous_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peoples != null) {
                    if (peoples.size() > 0) {
                        if ((TRACK < peoples.size() - 1) && (TRACK >= 1)) {
                            People people = (People) listView.getItemAtPosition(--TRACK);
                            for (People single_item : peoples) {
                                single_item.setSelected(false);
                            }
                            people.setSelected(true);
                            adapter.notifyDataSetChanged();
                        }else{
                            TRACK = 0;
                        }
                    }
                }
            }
        });
        next_Button = (Button) findViewById(R.id.ParseJSONActivity_next_button);
        next_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peoples != null) {
                    if (peoples.size() > 0) {
                        if (TRACK < peoples.size() - 2) {
                            People people = (People) listView.getItemAtPosition(++TRACK);
                            for (People single_item : peoples) {
                                single_item.setSelected(false);
                            }
                            people.setSelected(true);
                            adapter.notifyDataSetChanged();
                        }else {
                            System.out.println("TRACK: "+TRACK);
                            TRACK = 0;
                        }
                    }
                }else{
                    System.out.println("peoples is null");
                }
            }
        });
        listView = (ListView) findViewById(R.id.ParseJSONActivity_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (peoples != null) {
                    if (peoples.size() > 0) {
                        TRACK = position;
                        People people = (People) listView.getItemAtPosition(position);
                        for (People single_item : peoples) {
                            single_item.setSelected(false);
                        }
                        people.setSelected(true);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

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
                    adapter = new MyAdapter(getApplicationContext(), R.layout.single_item_custom_one, peoples);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private class MyAdapter extends ArrayAdapter {

        private ArrayList<People> a_productInfos;
        private Context a_context;
        private LayoutInflater a_layoutInflater;

        public MyAdapter(Context context, int resource, ArrayList<People> a_productInfos) {
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
                row = a_layoutInflater.inflate(R.layout.single_item_custom_one, parent, false);
                holder = new ViewHolder();
                holder.product_name = (TextView) row.findViewById(R.id.single_item_custom_one_textView);
                holder.item_LinearLayout = (LinearLayout) row.findViewById(R.id.single_item_custom_one_linearLayout);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final People productInfo = a_productInfos.get(position);
            holder.product_name.setText("" + productInfo.getName());

            if (productInfo.isSelected) {
                holder.item_LinearLayout.setBackgroundColor(Color.parseColor("#ff44ff"));
            } else {
                holder.item_LinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }

            return row;
        }

        class ViewHolder {
            TextView product_name;
            LinearLayout item_LinearLayout;
        }

    }

    private class People {
        private String id;
        private String Name;
        private String profession;
        private String image;
        boolean isSelected;

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

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}
