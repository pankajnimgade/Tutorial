package services.list.activity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

public class ServiceTestOneActivity extends AppCompatActivity {

    private Button start_service_Button;
    private Button stop_service_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        start_service_Button = (Button) findViewById(R.id.ServiceTestOneActivity_start_service_button);
        stop_service_Button = (Button) findViewById(R.id.ServiceTestOneActivity_stop_service_button);
        start_service_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        stop_service_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public class MyServiceTestOne extends IntentService {

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         * <p>
         * Used to name the worker thread, important only for debugging.
         */
        public MyServiceTestOne() {
            super("hello");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(getApplicationContext(), "Start the Service", Toast.LENGTH_SHORT).show();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        protected void onHandleIntent(Intent intent) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            long endTime = System.currentTimeMillis() + 5 * 1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                }
            }

        }


        @Override
        public void onDestroy() {
            Toast.makeText(getApplicationContext(), "Destroy the service", Toast.LENGTH_SHORT).show();
            super.onDestroy();
        }
    }

}
