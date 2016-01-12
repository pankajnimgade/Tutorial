package listview.test.activities;

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

public class ListViewListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> list_MyListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listv_view_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        list_MyListItems = new ArrayList<>();
        listView = (ListView) findViewById(R.id.ListViewListActivity_listView);
        list_MyListItems.add(new MyListItem("ListView-Test-One", ListViewTestOneActivity.class));
        list_MyListItems.add(new MyListItem("ListView-Test-Two", CustomListViewActivity.class));
        ArrayAdapter<MyListItem> adapter =
                new ArrayAdapter<MyListItem>(getApplicationContext(), R.layout.simple_list_item_1, list_MyListItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyListItem myListItem = (MyListItem) listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), myListItem.getActivity_class());
                startActivity(intent);
            }
        });
    }

}
