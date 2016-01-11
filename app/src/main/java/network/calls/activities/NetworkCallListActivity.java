package network.calls.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

import model.classes.MyListItem;

public class NetworkCallListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> myListItems_activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_call_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {

        listView = (ListView) findViewById(R.id.NetworkCallListActivity_ListView);
        myListItems_activities = new ArrayList<>();
        myListItems_activities.add(new MyListItem("Post a Request", PostRequestActivity.class));

        ArrayAdapter<MyListItem> adapter = new ArrayAdapter<MyListItem>(getApplicationContext(), R.layout.simple_list_item_1, myListItems_activities);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PostRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
