package data.binding.list.activities;

import android.os.Bundle;
import android.app.Activity;

import com.nimgade.pk.mytutorialapplication.R;

public class DataBindingTestOneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_test_one);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
