package loaders.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

import model.classes.MyListItem;

public class LoadersListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> loaders_MyListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaders_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.LoadersListActivity_listView);
        loaders_MyListItems = new ArrayList<>();
        loaders_MyListItems.add(new MyListItem("Loader-Test-One",LoaderTestOneActivity.class));

        ArrayAdapter<MyListItem> adapter = new ArrayAdapter<MyListItem>(getApplicationContext(), R.layout.simple_list_item_1, loaders_MyListItems);
        listView.setAdapter(adapter);
    }

}
