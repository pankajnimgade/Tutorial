package activities.list.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

public class NoteEditActivity extends Activity {

    private EditText editText;
    private Button button;
    private boolean isNewElement;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        editText = (EditText) findViewById(R.id.NoteEditActivity_editText);
        button = (Button) findViewById(R.id.NoteEditActivity_button);

        Intent myIntent = getIntent();
        isNewElement = myIntent.getBooleanExtra(NoteListActivity.NEW_ELEMENT, false);

        if (!isNewElement) {
            editText.setText("" + myIntent.getStringExtra(NoteListActivity.ELEMENT_VALUE));
            position = myIntent.getIntExtra(NoteListActivity.ELEMENT_POSITION,0);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text != null && text.trim().length() > 0) {
                    Intent intent = new Intent();
                    Log.d("NoteEditActivity",""+text);
                    intent.putExtra(NoteListActivity.ELEMENT_VALUE, text);
                    intent.putExtra(NoteListActivity.NEW_ELEMENT, isNewElement);
                    if (!isNewElement) {
                        intent.putExtra(NoteListActivity.ELEMENT_POSITION, position);
                    }
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Kindly write a valid element name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
