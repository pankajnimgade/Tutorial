package network.calls.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonObject;
import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequestActivity extends AppCompatActivity {

    private Button post_Button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Posting...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);

        post_Button = (Button) findViewById(R.id.PostRequestActivity_button);
        post_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
    }


    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                /**
                 * Kindly change this url string with your own, where you want to post your json data
                 */
                URL url = new URL("http://www.bomatec.be/android_login_api/login.php");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                // when you are posting do make sure you assign appropriate header
                // In this case POST.

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                // like this you can create your JOSN object which you want to send
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", "dddd@gmail.com");
                jsonObject.addProperty("password", "password");

                // And this is how you will write to the URL
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
