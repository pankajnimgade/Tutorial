package network.calls.ativities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.google.gson.JsonObject;
import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL("http://www.bomatec.be/android_login_api/login.php");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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

                Log.d("TAG", "" + IOUtils.toString(httpURLConnection.getInputStream()));


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
