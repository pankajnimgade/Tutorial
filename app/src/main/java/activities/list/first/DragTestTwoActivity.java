package activities.list.first;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.nimgade.pk.mytutorialapplication.R;

public class DragTestTwoActivity extends AppCompatActivity {

    private LinearLayout source_linearLayout;
    private LinearLayout destination_linearLayout;
    private static final String TAG = "DragTestTwoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_test_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        source_linearLayout = (LinearLayout) findViewById(R.id.DragTestTwoActivity_Source_LinearLayout);
        destination_linearLayout = (LinearLayout) findViewById(R.id.DragTestActivityActivity_Destination_LinearLayout);

//        source_linearLayout.setOnDragListener(new MyDragListener());
        destination_linearLayout.setOnDragListener(new MyDragListener());

        source_linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source_linearLayout);
                source_linearLayout.startDrag(data, shadowBuilder, source_linearLayout, 0);
                return true;
            }
        });
    }

    private class MyDragListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d(TAG, "Drag has started");
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d(TAG, "Drag has ended");
                    v.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d(TAG, "Drag has entered");
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.d(TAG, "Drag location");
                    break;
                case DragEvent.ACTION_DROP:
                    Log.d(TAG, "Drag has dropped");
                    View source_linear_Layout = (LinearLayout) event.getLocalState();
                    LinearLayout view = (LinearLayout) source_linear_Layout.getParent();
                    view.removeView(source_linear_Layout); // This will remove the imageView where it was

                    LinearLayout linearLayout = (LinearLayout) v;
                    if (v.getId() == R.id.DragTestActivityActivity_Source_LinearLayout) {
                        Log.d(TAG, "This is a source location");

                    } else if (v.getId() == R.id.DragTestActivityActivity_Destination_LinearLayout) {
                        Log.d(TAG, "This is a destination");

                    }
                    linearLayout.addView(source_linear_Layout); // this will add the ImageView to the new location where it was dropped.
                    source_linear_Layout.setVisibility(View.VISIBLE);


                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d(TAG, "Drag has exited");
                    break;
            }

            return true;
        }


    }
}
