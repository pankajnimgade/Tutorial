package data.binding.list.activities.five;

import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Pankaj Nimgade on 04-05-2016.
 */
public class MyHandler extends BaseObservable {

    public void onClickClick(View view) {
        Toast.makeText(view.getContext().getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

    }
}
