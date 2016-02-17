package miscellaneous.list.activities;

import android.os.Bundle;
import android.app.Activity;

import com.nimgade.pk.mytutorialapplication.R;

public class ReadFromAssetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_from_asset);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
