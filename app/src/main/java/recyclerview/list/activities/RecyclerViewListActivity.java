package recyclerview.list.activities;

import android.app.Activity;
import android.os.Bundle;

import com.nimgade.pk.mytutorialapplication.R;

public class RecyclerViewListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
