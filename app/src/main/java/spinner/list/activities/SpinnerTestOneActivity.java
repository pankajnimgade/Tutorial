package spinner.list.activities;

import android.os.Bundle;
import android.app.Activity;

import com.nimgade.pk.mytutorialapplication.R;

public class SpinnerTestOneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test_one);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
