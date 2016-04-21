package services.list.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nimgade.pk.mytutorialapplication.R;

/**
 * There are two type of service <br>
 * Started Service<br>
 * Bound Service<br>
 * <p>following service is an example of started service</p>
 */
public class ServiceThreeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
    }


    /**
     * this service would be an example of Started Service type
     */
    public static class ExampleService extends Service {

        /*A started service is one that another component starts by calling startService(), resulting in a call to the service's onStartCommand() method of this service*/
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onCreate() {
            super.onCreate();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }

}

/**
 * Traditionally, there are two classes you can extend to create a started service:
 *
 * Service
 *
 * This is the base class for all services. When you extend this class,
 * it's important that you create a new thread in which to do all the service's work,
 * because the service uses your application's main thread, by default,
 * which could slow the performance of any activity your application is running.
 *
 *
 *
 * IntentService
 *
 * This is a subclass of Service that uses a worker thread to handle all start requests,one at a time.
 * This is the best option if you don't require that your service handle multiple requests simultaneously.
 * All you need to do is implement onHandleIntent(),
 * which receives the intent for each start request so you can do the background work.
 */
