package activities.list.first;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.Random;

public class TestThreadActivity extends AppCompatActivity {

    private Button restart_Button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        restart_Button = (Button) findViewById(R.id.TestThreadActivity_restart_button);
        imageView = (ImageView) findViewById(R.id.TestThreadActivity_imageView);

        final Listener myListener = new Listener(this);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.TestThreadActivity_linear_layout);
        linearLayout.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("Height: " + linearLayout.getHeight());
                System.out.println("Width: " + linearLayout.getWidth());
            }
        });

        restart_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setImageResource(R.drawable.piccolo);
                final Thread listenerThread = new Thread(myListener);
                listenerThread.setName("My Listener Thread");
                listenerThread.start();

            }
        });

    }

    public class Listener implements Runnable {
        public Listener(Activity mainActivity) {
            this.mainActivity = mainActivity;
        }

        private Activity mainActivity;
        private ImageView myImageView;
        int newData = 0;

        @Override
        public void run() {
            while (true) {
                // Here is where my app gets the data from the server via UDP.
                // This always works, I am always getting the correct data.

                newData = (new Random()).nextInt(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                myImageView = (ImageView) mainActivity.findViewById(R.id.TestThreadActivity_imageView);
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("newData: " + newData);
                        if (newData == 1) {
                            myImageView.setImageResource(R.drawable.gohan);
                        } else if (newData == 2) {
                            myImageView.setImageResource(R.drawable.goku);
                        } else {
                            myImageView.setImageResource(R.drawable.piccolo);
                        }
                    }
                });
            }
        }
    }

}
