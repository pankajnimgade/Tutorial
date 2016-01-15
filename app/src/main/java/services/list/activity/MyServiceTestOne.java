package services.list.activity;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Pankaj Nimgade on 15-01-2016.
 */
public class MyServiceTestOne extends IntentService {



    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyServiceTestOne(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
