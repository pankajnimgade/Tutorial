package google.map.list.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nimgade.pk.mytutorialapplication.R;

public class GoogleMapSevenCameraActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    private Button source_Button;
    private Button destination_Button;
    private Button get_lat_long_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_seven_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        source_Button = (Button) findViewById(R.id.GoogleMapSevenCameraActivity_source_button);
        source_Button.setOnClickListener(this);
        destination_Button = (Button) findViewById(R.id.GoogleMapSevenCameraActivity_destination_button);
        destination_Button.setOnClickListener(this);
        get_lat_long_Button = (Button) findViewById(R.id.GoogleMapSevenCameraActivity_get_lat_long_button);
        get_lat_long_Button.setOnClickListener(this);

        MapFragment mapFragment
                = (MapFragment) getFragmentManager().findFragmentById(R.id.GoogleMapSevenCameraActivity_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.setBuildingsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
            return;
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.d("latitude",""+latLng.latitude);
                Log.d("longitude",""+latLng.longitude);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (mMap != null) {
            switch (v.getId()) {
                case R.id.GoogleMapSevenCameraActivity_source_button:
                    // lat=43.65774661382077    long=-79.37814820557833

                    break;
                case R.id.GoogleMapSevenCameraActivity_destination_button:
                    // lat=    long=
                    animate();
                    break;
                case R.id.GoogleMapSevenCameraActivity_get_lat_long_button:
// Create a LatLngBounds that includes Australia.
                    LatLngBounds AUSTRALIA = new LatLngBounds(
                            new LatLng(-44, 113), new LatLng(-10, 154));

// Set the camera to the greatest possible zoom level that includes the
// bounds
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(AUSTRALIA, 0));

                    break;
            }
        }
    }

    private void animate(){
        LatLng SYDNEY = new LatLng(-33.88,151.21);
         LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);

//        private GoogleMap map;
        // Obtain the map from a MapFragment or MapView.

// Move the camera instantly to Sydney with a zoom of 15.
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15));

// Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 4000, null);

// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
                .zoom(18)                   // Sets the zoom
                .bearing(180)                // Sets the orientation of the camera to east
                .tilt(45)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
