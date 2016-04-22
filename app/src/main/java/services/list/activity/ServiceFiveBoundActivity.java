package services.list.activity;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.Random;

/**
 * This example demonstrates use of Bounded service<br>
 * <p/>
 * A bound service typically lives only while it serves another application component
 * and does not run in the background indefinitely.
 */
public class ServiceFiveBoundActivity extends AppCompatActivity {

    private static final String TAG = "ServiceFour";

    private Button bind_service_Button;
    private Button get_number_Button;

    ServiceFiveBounded mService;
    boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_five_bound);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        bind_service_Button = (Button) findViewById(R.id.ServiceFiveBoundActivity_bind_service_button);
        get_number_Button = (Button) findViewById(R.id.ServiceFiveBoundActivity_get_number_button);
        bind_service_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bind to ServiceFiveBounded
                Intent intent = new Intent(getApplicationContext(), ServiceFiveBounded.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        get_number_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    // Call a method from the ServiceFiveBounded.
                    // However, if this call were something that might hang, then this request should
                    // occur in a separate thread to avoid slowing down the activity performance.
                    int num = mService.getRandomNumber();
                    Toast.makeText(getApplicationContext(), "number: " + num, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,  IBinder service) {
            // We've bound to ServiceFiveBounded, cast the IBinder and get LocalService instance
            ServiceFiveBounded.LocalBinder binder = (ServiceFiveBounded.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    public static class ServiceFiveBounded extends IntentService {

        // Binder given to clients
        private final IBinder mBinder = new LocalBinder();
        // Random number generator
        private final Random mGenerator = new Random();

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         * <p/>
         * Used to name the worker thread, important only for debugging.
         */
        public ServiceFiveBounded() {
            super("ServiceFiveBounded");

        }


        @Override
        protected void onHandleIntent(Intent intent) {

        }

        /*when there are no components bound to the service, the system destroys the service
        (you do not need to stop a bound service in the way you must when the service is started through onStartCommand()).*/
        @Override
        public IBinder onBind(Intent intent) {
            return this.mBinder;
        }

        /**
         * method for clients
         */
        public int getRandomNumber() {
            return mGenerator.nextInt(100);
        }


        /**
         * Class used for the client Binder.  Because we know this service always
         * runs in the same process as its clients, we don't need to deal with IPC.
         */
        public class LocalBinder extends Binder {
            ServiceFiveBounded getService() {
                // Return this instance of LocalService so clients can call public methods
                return ServiceFiveBounded.this;
            }
        }

    }

    /*Multiple clients can bind to the service at once.
    When a client is done interacting with the service, it calls unbindService() to unbind.
    Once there are no clients bound to the service, the system destroys the service.*/

    /*If your service is used only by the local application and does not need to work across processes,
     then you can implement your own Binder class that provides your client direct access to public methods in the service.*/

}
