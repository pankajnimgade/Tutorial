package activities.list.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.nimgade.pk.mytutorialapplication.R;

/**
 * This activity gives you an idea about how to apply justify alignment to the TextView
 */
public class JustifyAlignmentActivity extends AppCompatActivity {


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
        /**
         * This is the TextView to which you would be implementing justify alignment
         */
        WebView webView = (WebView) findViewById(R.id.JustifyAlignmentActivity_textView);

        /**
         * pick some random text, I took it from Head First Java
         */
        String some_random_text = "If you really want to learn, and you want to learn more quickly and more deeply,\n" +
                "pay attention to how you pay attention. Think about how you think, Learn how\n" +
                "you learn.\n\n" +
                "Most of us did not take courses on metacognition or learning theory when we were\n" +
                "growing up. We were expected to learn, but rarely taught to learn.\n" +
                "But we assume that ifyou're holding this book, you want to learn Java. And you\n" +
                "probably don't want to spend a lot of time.";

        String text = "<html><body>" + "<p align=\"justify\" >";
        text += some_random_text;
        text += "</body></html>";
        webView.loadData(text, "text/html; charset=UTF-8", null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
