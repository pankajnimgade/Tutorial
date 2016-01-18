package activities.list.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.Random;

public class TestTextViewActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    int[] id = {R.style.TestOne, R.style.TestTwo, R.style.TestThree, R.style.TestFour, R.style.TestFive};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_text_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        textView = (TextView) findViewById(R.id.TestTextViewActivity_textView);
        button = (Button) findViewById(R.id.TestTextViewActivity_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = (new Random()).nextInt(5);
                textView.setTextAppearance(getApplicationContext(),id[count]);
            }
        });
    }

}
