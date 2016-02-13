package miscellaneous.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.nimgade.pk.mytutorialapplication.R;

public class ShowImageTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.SubsamplingScaleImageView_imageView);
        imageView.setImage(ImageSource.asset("ttc_ride_guide_tile_one.png"));
        imageView.setDoubleTapZoomScale(3.0f);
        imageView.setMaxScale(3.0f);
    }

}
