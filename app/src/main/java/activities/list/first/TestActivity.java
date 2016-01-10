package activities.list.first;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestActivity extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        button = (Button) findViewById(R.id.TestActivity_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "this is a test Activity", Toast.LENGTH_SHORT).show();
                new MyTask().execute();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private class MyTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL("http://www.bomatec.be/android_login_api/login.php");

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.connect();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", "dddd@gmail.com");
                jsonObject.addProperty("password", "password");

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(jsonObject.toString());
                wr.flush();
                wr.close();

                Log.d("TAG",""+ IOUtils.toString(httpURLConnection.getInputStream()));


            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }


}
