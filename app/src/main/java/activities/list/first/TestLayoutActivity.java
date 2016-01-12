package activities.list.first;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.nimgade.pk.mytutorialapplication.R;

public class TestLayoutActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);


        initializeUI();

    }

    private void initializeUI() {
        String[] list = {"match1", "match2", "match3",
                "match12", "match11", "match8", "match7", "match4",
                "match13", "match10", "match9", "match6", "match5",};

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.TestLayoutActivity_autoCompleteTextView);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list_item_1, list);
        autoCompleteTextView.setAdapter(adapter);
    }

}
