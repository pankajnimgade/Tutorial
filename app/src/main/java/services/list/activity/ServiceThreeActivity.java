package services.list.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

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
     * this service would be an example of Started Service type<br>
     * <p>If, however, you require your service to perform multi-threading
     * (instead of processing start requests through a work queue),
     * then you can extend the Service class to handle each intent.
     */
    public static class ExampleService extends Service {

        private Looper mServiceLooper;
        private ServiceHandler mServiceHandler;

        // Handler that receives messages from the thread
        private final class ServiceHandler extends Handler {
            public ServiceHandler(Looper looper) {
                super(looper);
            }

            @Override
            public void handleMessage(Message msg) {
                // Normally we would do some work here, like download a file.
                // For our sample, we just sleep for 5 seconds.
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // Restore interrupt status.
                    Thread.currentThread().interrupt();
                }
                // Stop the service using the startId, so that we don't stop
                // the service in the middle of handling another job
                stopSelf(msg.arg1);
            }
        }


        /*A started service is one that another component starts by calling startService(), resulting in a call to the service's onStartCommand() method of this service*/
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

            // For each start request, send a message to start a job and deliver the
            // start ID so we know which request we're stopping when we finish the job
            Message msg = mServiceHandler.obtainMessage();
            msg.arg1 = startId;
            mServiceHandler.sendMessage(msg);

            // If we get killed, after returning from here, restart
            return START_STICKY;

        }

        @Override
        public void onCreate() {
            // Start up the thread running the service.  Note that we create a
            // separate thread because the service normally runs in the process's
            // main thread, which we don't want to block.  We also make it
            // background priority so CPU-intensive work will not disrupt our UI.
            HandlerThread thread = new HandlerThread("ServiceStartArguments",
                    Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();

            // Get the HandlerThread's Looper and use it for our Handler
            mServiceLooper = thread.getLooper();
            mServiceHandler = new ServiceHandler(mServiceLooper);

        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            //We don't provide binding, so return null
            return null;
        }
    }

}

/**
 * Traditionally, there are two classes you can extend to create a started service:
 * <p/>
 * Service
 * <p/>
 * This is the base class for all services. When you extend this class,
 * it's important that you create a new thread in which to do all the service's work,
 * because the service uses your application's main thread, by default,
 * which could slow the performance of any activity your application is running.
 * <p/>
 * <p/>
 * <p/>
 * IntentService
 * <p/>
 * This is a subclass of Service that uses a worker thread to handle all start requests,one at a time.
 * This is the best option if you don't require that your service handle multiple requests simultaneously.
 * All you need to do is implement onHandleIntent(),
 * which receives the intent for each start request so you can do the background work.
 */
