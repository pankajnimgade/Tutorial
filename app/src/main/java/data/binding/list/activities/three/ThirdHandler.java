package data.binding.list.activities.three;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Pankaj Nimgade on 03-05-2016.
 */
public class ThirdHandler {

    public void onClickOne(View view) {
        Toast.makeText(view.getContext().getApplicationContext(), "One", Toast.LENGTH_SHORT).show();
    }

    public void onClickTwo(View view) {
        Toast.makeText(view.getContext().getApplicationContext(), "Two", Toast.LENGTH_SHORT).show();
    }

}
