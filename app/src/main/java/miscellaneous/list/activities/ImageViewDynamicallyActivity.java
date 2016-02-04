package miscellaneous.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nimgade.pk.mytutorialapplication.R;
import com.squareup.picasso.Picasso;

public class ImageViewDynamicallyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_dynamically);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ImageViewDynamicallyActivity_linear_layout);

        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);

        linearLayout.addView(imageView);

        Picasso.with(getApplicationContext()).load("http://image.3bmeteo.com/images/newarticles/w_663/immagine-nasa-visible-infrared-imaging-radiometer-suite-viirs-3bmeteo-66721.jpg").into(imageView);

    }

}
