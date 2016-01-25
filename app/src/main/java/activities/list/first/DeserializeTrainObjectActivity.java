package activities.list.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.nimgade.pk.mytutorialapplication.R;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import excel.test.module.classes.Station;

public class DeserializeTrainObjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deserialize_train_object);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        try {
            ArrayList<Station> stations = new ArrayList<>();

            InputStream inputStream = getAssets().open("trainLineOne.txt");
            ObjectInputStream in = new ObjectInputStream(inputStream);
            stations = (ArrayList<Station>) in.readObject();
            in.close();
            inputStream.close();

            Log.d("TAG", "initializeUI: " + stations.size());

            for (int i = 0; i < stations.size(); i++) {
                Station station = stations.get(i);
                System.out.println("" + station.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
