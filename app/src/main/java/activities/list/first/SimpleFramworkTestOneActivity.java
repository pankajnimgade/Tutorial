package activities.list.first;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleFramworkTestOneActivity extends AppCompatActivity {
    //    http://stackoverflow.com/questions/35080142/sax-parsing-android-how-to-get-child-nodes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_framwork_test_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        new DownloadXML().execute();
    }

    private class DownloadXML extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {


            try {
                URL url = new URL("https://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                String result = IOUtils.toString(httpURLConnection.getInputStream());
                System.out.println("" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
