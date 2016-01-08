package activities.list.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> strings;
    public static final String NEW_ELEMENT = "NEW_ELEMENT";
    public static final String ELEMENT_VALUE = "ELEMENT_VALUE";
    public static final String ELEMENT_POSITION = "ELEMENT_POSITION";
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.NoteListActivity_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), NoteEditActivity.class);
                intent.putExtra(NEW_ELEMENT, false);
                intent.putExtra(ELEMENT_POSITION, position);
                intent.putExtra(ELEMENT_VALUE, text);
                startActivityForResult(intent, 123);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.NoteListActivity_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoteEditActivity.class);
                intent.putExtra(NEW_ELEMENT, true);
                startActivityForResult(intent, 123);
            }
        });

        setUpList();

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("NoteListActivity1", "" + data.getStringExtra(NoteListActivity.ELEMENT_VALUE));

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                Log.d("NoteListActivity2", "" + data.getStringExtra(NoteListActivity.ELEMENT_VALUE));
                boolean isNewElement = data.getBooleanExtra(NoteListActivity.NEW_ELEMENT, false);
                if (isNewElement) {
                    strings.add("" + data.getStringExtra(NoteListActivity.ELEMENT_VALUE));
                    adapter.notifyDataSetChanged();
                } else {
                    strings.set(data.getIntExtra(NoteListActivity.ELEMENT_POSITION, 0), "" + data.getStringExtra(NoteListActivity.ELEMENT_VALUE));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void setUpList() {
        strings = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            strings.add("Element_1_" + i);
        }
    }
}
