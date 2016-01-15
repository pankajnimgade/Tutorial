package activities.list.first;

import android.os.Bundle;
import android.app.Activity;
import com.nimgade.pk.mytutorialapplication.R;

public class ExampleOneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_one);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
