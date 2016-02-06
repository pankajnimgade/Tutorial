package google.map.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nimgade.pk.mytutorialapplication.R;

public class GoogleMapTenMarkersActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    static final LatLng PERTH = new LatLng(-31.90, 115.86);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_ten_markers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.GoogleMapTenMarkersActivity_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        CameraPosition cameraPosition = new CameraPosition.Builder().target(PERTH).zoom(14).bearing(150).tilt(45).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 4000,null);
        Marker marker = googleMap.addMarker(new MarkerOptions().position(PERTH).flat(false));
    }
}
