package google.map.list.activities;

import android.os.Bundle;
import android.app.Activity;

import com.nimgade.pk.mytutorialapplication.R;

public class GoogleMapTestTwoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_test_two);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
