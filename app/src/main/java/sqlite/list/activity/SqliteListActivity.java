package sqlite.list.activity;

import android.os.Bundle;
import android.app.Activity;

import com.nimgade.pk.mytutorialapplication.R;

public class SqliteListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
