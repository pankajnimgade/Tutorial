package activities.list.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

public class JustifyAlignmentActivity extends AppCompatActivity {

    private String some_random_text = "If you really want to learn, and you want to learn more quickly and more deeply,\n" +
            "pay attention to how you pay attention. Think about how you think, Learn how\n" +
            "you learn.\n\n" +
            "Most of us did not take courses on metacognition or learning theory when we were\n" +
            "growing up. We were expected to learn, but rarely taught to learn.\n" +
            "But we assume that ifyou're holding this book, you want to learn Java. And you\n" +
            "probably don't want to spend a lot of time.";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justify_alignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();

    }

    private void initializeUI() {
        textView = (TextView) findViewById(R.id.JustifyAlignmentActivity_textView);
        textView.setText("" + some_random_text);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
