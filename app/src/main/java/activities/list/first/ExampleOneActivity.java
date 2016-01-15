package activities.list.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

public class ExampleOneActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        editText = (EditText) findViewById(R.id.ExampleOneActivity_editText);
        button = (Button) findViewById(R.id.ExampleOneActivity_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!isValidText(text)) {
                    Toast.makeText(getApplicationContext(), "Kindly Provide a valid Customer Name", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    return;
                }
                Log.d("TAG", "" + text);

            }
        });
    }


    public boolean isValidText(String text) {
        if (text == null) {
            return false;
        }
        if (text.trim().contentEquals("")) {
            return false;
        }

        if (text.trim().length() < 1) {
            return false;
        }

        if (text.trim().toLowerCase().contentEquals("null")) {
            return false;
        }
        return true;
    }

}
