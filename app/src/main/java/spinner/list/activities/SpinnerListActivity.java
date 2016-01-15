package spinner.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

import model.classes.MyListItem;

public class SpinnerListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyListItem> myListItems;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.SpinnerListActivity_listView);

        myListItems = new ArrayList<>();

    }

}
