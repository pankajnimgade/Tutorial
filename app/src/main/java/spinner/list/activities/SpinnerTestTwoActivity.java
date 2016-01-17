package spinner.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.nimgade.pk.mytutorialapplication.R;
import com.squareup.picasso.Picasso;

public class SpinnerTestTwoActivity extends AppCompatActivity {

    private Spinner url_Spinner;
    private Button load_image_Button;
    private ImageView url_image_ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        url_Spinner = (Spinner) findViewById(R.id.SpinnerTestTwoActivity_url_spinner);
        load_image_Button = (Button) findViewById(R.id.SpinnerTestTwoActivity_load_image_button);
        url_image_ImageView = (ImageView) findViewById(R.id.SpinnerTestTwoActivity_image_from_url_imageView);

        String[] url_array = getResources().getStringArray(R.array.url_images);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, url_array);
        url_Spinner.setAdapter(adapter);

        url_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String image_url = (String)url_Spinner.getItemAtPosition(position);
                Picasso.with(getApplicationContext()).load(image_url).into(url_image_ImageView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
