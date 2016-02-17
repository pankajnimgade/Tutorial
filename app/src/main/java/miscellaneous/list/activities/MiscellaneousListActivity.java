package miscellaneous.list.activities;

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

public class MiscellaneousListActivity extends AppCompatActivity {

    private ArrayList<MyListItem> myListItems;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.MiscellaneousListActivity_listView);
        myListItems = new ArrayList<>();
        myListItems.add(new MyListItem("Contextual Action Mode", ContextualActionModeActivity.class));
        myListItems.add(new MyListItem("Contextual Action Mode Test One", ContextualActionModeTestOneActivity.class));
        myListItems.add(new MyListItem("Create ImageView dynamically", ImageViewDynamicallyActivity.class));
        myListItems.add(new MyListItem("Test Percent One", TestPercentOneActivity.class));
        myListItems.add(new MyListItem("Test Percent Two", PercentTwoActivity.class));
        myListItems.add(new MyListItem("Show Image Activity", ShowImageActivity.class));
        myListItems.add(new MyListItem("Show Image Two Activity", ShowImageTwoActivity.class));
        myListItems.add(new MyListItem("Read the Text from asset Activity", ReadFromAssetActivity.class));
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
