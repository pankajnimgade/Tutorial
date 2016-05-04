package data.binding.list.activities;

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

import data.binding.list.activities.one.DataBindingTestOneActivity;
import data.binding.list.activities.three.DataBindingThreeActivity;
import data.binding.list.activities.two.DataBindingTwoActivity;
import model.classes.MyListItem;

public class DataBindingListActivity extends AppCompatActivity {

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.DataBindingListActivity_listView);
        ArrayList<MyListItem> myListItems = new ArrayList<>();
        myListItems.add(new MyListItem("DataBinding Test one", DataBindingTestOneActivity.class));
        myListItems.add(new MyListItem("DataBinding Test Two", DataBindingTwoActivity.class));
        myListItems.add(new MyListItem("DataBinding Test Three", DataBindingThreeActivity.class));

        ArrayAdapter<MyListItem> adapter =
                new ArrayAdapter<MyListItem>(getApplicationContext(), R.layout.simple_list_item_1, myListItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyListItem myListItem = (MyListItem) listView.getItemAtPosition(position);
                Intent myIntent = new Intent(getApplicationContext(), myListItem.getActivity_class());
                startActivity(myIntent);
            }
        });

    }

}
