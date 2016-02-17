package miscellaneous.list.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.nimgade.pk.mytutorialapplication.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReadFromAssetActivity extends AppCompatActivity {

    private Button read_Button;
    int[] force_l = null;
    int[] force_r = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_from_asset);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        read_Button = (Button) findViewById(R.id.ReadFromAssetActivity_start_reading_button);
        read_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ReadFromAsset().execute();
            }
        });
    }

    private class ReadFromAsset extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("text.txt")));
                String mLine = null;
                int count = 0;
                while ((mLine = reader.readLine()) != null) {
                    String[] integer_Strings = mLine.split(" ");
                    //System.out.println(Arrays.deepToString(integer_Strings));
                    if (count == 0) {
                        force_l = new int[integer_Strings.length];
                        for (int i = 0; i < integer_Strings.length; i++) {
                            force_l[i] = Integer.parseInt(integer_Strings[i]);
                        }
                        count++;
                    } else if (count == 1) {
                        force_r = new int[integer_Strings.length];
                        for (int i = 0; i < integer_Strings.length; i++) {
                            force_r[i] = Integer.parseInt(integer_Strings[i]);
                        }
                    }

                }

                System.out.println(Arrays.toString(force_l));
                System.out.println(Arrays.toString(force_r));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
