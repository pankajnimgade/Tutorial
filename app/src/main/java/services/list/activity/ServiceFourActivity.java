package services.list.activity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

public class ServiceFourActivity extends AppCompatActivity {

    private static final String TAG = "ServiceFour";

    private Button start_service_Button;
    private Button stop_service_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_four);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        start_service_Button = (Button) findViewById(R.id.ServiceFourActivity_start_service_button);
        stop_service_Button = (Button) findViewById(R.id.ServiceFourActivity_stop_service_button);

        final Intent intent = new Intent(getApplicationContext(), ServiceFourIntentService.class);
        intent.putExtra("work","some work to be done");

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

    public static class ServiceFourIntentService extends IntentService {

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * "ServiceFourIntentService" Used to name the worker thread, important only for debugging.<br>
         * <p> A constructor is required, and must call the super IntentService(String)
         * constructor with a name for the worker thread.<p/>
         */
        public ServiceFourIntentService() {
            super("ServiceFourIntentService");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(getApplicationContext(), "ServiceFourIntentService starting", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onStartCommand: start a work in a thread");
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        /**
         * The IntentService calls this method from the default worker thread with
         * the intent that started the service. When this method returns, IntentService
         * stops the service, as appropriate.
         */
        @Override
        protected void onHandleIntent(Intent intent) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000);
                Log.d(TAG, "onHandleIntent: ");
                Log.d(TAG, "onHandleIntent: "+intent.getExtras().getString("work"));
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            stopSelf();
            // as soon as this method returns the Service will stop
        }

        @Override
        public void onDestroy() {
            Toast.makeText(getApplicationContext(), "Destroy the service", Toast.LENGTH_SHORT).show();
            super.onDestroy();
        }
    }

}
