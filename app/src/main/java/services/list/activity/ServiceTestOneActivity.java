package services.list.activity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
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
        final Intent intent = new Intent(getApplicationContext(), MyServiceTestOne.class);
        start_service_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);

            }
        });

        stop_service_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }


    /**
     * The IntentService does the following:
     * <br>
     * <br>
     * Creates a default worker thread that executes all intents delivered to onStartCommand() separate from your application's main thread.
     * <br>
     * <br>
     * Creates a work queue that passes one intent at a time to your onHandleIntent() implementation, so you never have to worry about multi-threading.
     * <br>
     * <br>
     * Stops the service after all start requests have been handled, so you never have to call stopSelf().
     * <br>
     * <br>
     * Provides default implementation of onBind() that returns null.
     * <br>
     * <br>
     * Provides a default implementation of onStartCommand() that sends the intent to the work queue and then to your onHandleIntent() implementation.
     */
    public static class MyServiceTestOne extends IntentService {

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
        public IBinder onBind(Intent intent) {
            return null;
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
