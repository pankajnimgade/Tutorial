package google.map.list.activities;

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

public class GoogleMapListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> myListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        // do not be dismayed by the reviews or comments people might make, it takes guts and a lot a courage to speak up in while eveyone is watching

        listView = (ListView) findViewById(R.id.GoogleMapListActivity_listView);
        myListItems = new ArrayList<>();
        myListItems.add(new MyListItem("GoogleMap TestOneActivity", GoogleMapTestOneActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Two", GoogleMapTestTwoActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Three", GoogleMapTestThreeActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Four Lite Mode", GoogleMapFourLiteModeActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Five Street View", GoogleMapFiveStreetViewActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Six launch google map", GoogleMapSixLaunchActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Seven Camera", GoogleMapSevenCameraActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Eight My Location", GoogleMapEightMyLocationActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Nine Marker", GoogleMapNineMarkersActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Ten Marker changes", GoogleMapTenMarkersActivity.class));
        myListItems.add(new MyListItem("GoogleMap Test Info Window", GoogleMapInfoWindowActivity.class));

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
