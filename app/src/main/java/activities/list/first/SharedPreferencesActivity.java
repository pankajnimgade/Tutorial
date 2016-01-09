package activities.list.first;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText count_editText;
    private TextView count_textView;
    private Button save_read_button;

    private static final String FILE_NAME = "text";
    private static final String COUNT_KEY = "COUNT_KEY";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefrences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        initializeUI();

        String count_Integer = sharedPreferences.getString(COUNT_KEY, "");

        count_textView.setText("" + count_Integer);
        count_editText.setText("" + count_Integer);


    }

    private void initializeUI() {
        count_editText = (EditText) findViewById(R.id.SharedPreferencesActivity_editText);
        count_textView = (TextView) findViewById(R.id.SharedPreferencesActivity_textView);
        save_read_button = (Button) findViewById(R.id.SharedPreferencesActivity_button);
        save_read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count_text = count_editText.getText().toString();
                if (count_text != null && count_text.length() > 0) {
                    count_textView.setText("" + count_text);
                    editor.putString(COUNT_KEY, count_text).apply();
                } else {
                    Toast.makeText(getApplicationContext(), "Kindly provide a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
