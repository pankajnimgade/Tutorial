package services.list.activity;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.nimgade.pk.mytutorialapplication.R;

public class ServiceTestOneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test_one);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public class MyService extends IntentService {

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        public MyService() {
            super("hello");
        }


        @Override
        protected void onHandleIntent(Intent intent) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            long endTime = System.currentTimeMillis() + 5*1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                }
            }

        }
    }

}
