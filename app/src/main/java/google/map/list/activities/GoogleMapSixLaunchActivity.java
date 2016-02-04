package google.map.list.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.nimgade.pk.mytutorialapplication.R;

public class GoogleMapSixLaunchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button launch_google_map_Button;
    private Button san_francisco_Button;
    private Button san_francisco_restaurant_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_six_launch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        launch_google_map_Button = (Button) findViewById(R.id.GoogleMapSixLaunchActivity_launch_google_map_button);
        launch_google_map_Button.setOnClickListener(this);
        san_francisco_Button = (Button) findViewById(R.id.GoogleMapSixLaunchActivity_san_francisco_button);
        san_francisco_Button.setOnClickListener(this);
        san_francisco_restaurant_Button = (Button) findViewById(R.id.GoogleMapSixLaunchActivity_san_francisco_restaurant_button);
        san_francisco_restaurant_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GoogleMapSixLaunchActivity_launch_google_map_button:
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                //If the system cannot identify an app that can respond to the intent, your app may crash.
                // For this reason, you should first verify that a receiving application is installed
                // before you present one of these intents to a user.
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    // Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }
                break;

            case R.id.GoogleMapSixLaunchActivity_san_francisco_button:
                Uri gmmIntentUri_1 = Uri.parse("geo:37.7749,-122.4194");
                Intent mapIntent_1 = new Intent(Intent.ACTION_VIEW, gmmIntentUri_1);
                mapIntent_1.setPackage("com.google.android.apps.maps");
                if (mapIntent_1.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent_1);
                }

                break;
            case R.id.GoogleMapSixLaunchActivity_san_francisco_restaurant_button:
                Uri gmmIntentUri_2 = Uri.parse("geo:37.7749,-122.4194?z=10&q=restaurants");
                Intent mapIntent_2 = new Intent(Intent.ACTION_VIEW, gmmIntentUri_2);
                mapIntent_2.setPackage("com.google.android.apps.maps");
                if (mapIntent_2.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent_2);
                }

                break;
        }
    }
}
