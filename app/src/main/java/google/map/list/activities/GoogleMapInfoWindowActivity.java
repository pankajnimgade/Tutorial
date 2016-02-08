package google.map.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nimgade.pk.mytutorialapplication.R;

public class GoogleMapInfoWindowActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Button perth_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_info_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        perth_Button = (Button) findViewById(R.id.GoogleMapInfoWindowActivity_Perth_button);
        perth_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleMap != null) {
                    LatLng sydney = new LatLng(-34, 151);
                    googleMap.addMarker(new MarkerOptions().position(sydney).title("Sydney").snippet("Population: 4,137,400"));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(14).bearing(10).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 4000, null);
                }
            }
        });

        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.GoogleMapInfoWindowActivity_map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.getUiSettings().setMapToolbarEnabled(false); // this removes the toolbar which can navigate to the google map

        LatLng sydney = new LatLng(-34, 151);
        LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
        googleMap.addMarker(new MarkerOptions().position(MELBOURNE).title("MELBOURNE").snippet("Population: 4,137,400"));

        LatLng ADELAIDE = new LatLng(-34.928621, 138.599959);
        Marker adelaide_Marker =
                this.googleMap.addMarker(new MarkerOptions().position(ADELAIDE).title("ADELAIDE").snippet("Population: 4,137,400"));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(MELBOURNE).zoom(14).bearing(10).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 4000, null);

    }
}
