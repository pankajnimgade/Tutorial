package google.map.list.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.nimgade.pk.mytutorialapplication.R;

public class GoogleMapFiveStreetViewActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_five_street_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        handler = new Handler();

        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.GoogleMapFiveStreetViewActivity_streetViewPanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(new LatLng(-33.87365, 151.20689));
        final StreetViewPanorama final_streetViewPanorama=streetViewPanorama;


        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                long duration = 500;
                float tilt = 10;
                StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                        .zoom(final_streetViewPanorama.getPanoramaCamera().zoom)
                        .bearing(final_streetViewPanorama.getPanoramaCamera().bearing)
                        .tilt(tilt)
                        .build();
                final_streetViewPanorama.animateTo(camera, duration);
            }
        }, 5000);
    }
}
