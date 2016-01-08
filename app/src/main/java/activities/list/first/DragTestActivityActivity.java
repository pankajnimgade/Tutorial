package activities.list.first;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

public class DragTestActivityActivity extends AppCompatActivity {

    private ArrayList<ImageView> source_ImageViews;
    private ArrayList<ImageView> destination_ImageViews;
    private LinearLayout source_linearLayout;
    private LinearLayout destination_linearLayout;
    private static final String TAG = "DragTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        source_ImageViews = new ArrayList<>();
        destination_ImageViews = new ArrayList<>();
        source_ImageViews.add((ImageView) findViewById(R.id.DragTestActivityActivity_imageView));
        source_ImageViews.add((ImageView) findViewById(R.id.DragTestActivityActivity_imageView2));
        source_ImageViews.add((ImageView) findViewById(R.id.DragTestActivityActivity_imageView3));
        source_ImageViews.add((ImageView) findViewById(R.id.DragTestActivityActivity_imageView4));
        source_ImageViews.add((ImageView) findViewById(R.id.DragTestActivityActivity_imageView5));
        source_ImageViews.add((ImageView) findViewById(R.id.DragTestActivityActivity_imageView6));
        source_ImageViews.add((ImageView) findViewById(R.id.DragTestActivityActivity_imageView7));

        source_linearLayout = (LinearLayout) findViewById(R.id.DragTestActivityActivity_Source_LinearLayout);
        destination_linearLayout = (LinearLayout) findViewById(R.id.DragTestActivityActivity_Destination_LinearLayout);

        source_linearLayout.setOnDragListener(new MyDragListener());
        destination_linearLayout.setOnDragListener(new MyDragListener());

        for (final ImageView imageView : source_ImageViews) {
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView);
                    imageView.startDrag(data, shadowBuilder, imageView, 0);
                    return true;
                }
            });
        }


    }

    private class MyDragListener implements View.OnDragListener {

        Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

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
                    View imageView = (ImageView) event.getLocalState();
                    ViewGroup view = (ViewGroup) imageView.getParent();
                    view.removeView(imageView); // This will remove the imageView where it was

                    LinearLayout linearLayout = (LinearLayout) v;
                    if (v.getId() == R.id.DragTestActivityActivity_Source_LinearLayout) {
                        Log.d(TAG, "This is a source location");

                    } else if (v.getId() == R.id.DragTestActivityActivity_Destination_LinearLayout) {
                        Log.d(TAG, "This is a destination");

                    }
                    linearLayout.addView(imageView); // this will add the ImageView to the new location where it was dropped.
                    imageView.setVisibility(View.VISIBLE);


                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d(TAG, "Drag has exited");
                    break;
            }

            return true;
        }


    }

}
