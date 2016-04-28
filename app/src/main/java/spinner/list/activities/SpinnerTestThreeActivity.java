package spinner.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

public class SpinnerTestThreeActivity extends AppCompatActivity {

    private EditText number_EditText;
    private Button create_Button;
    private Spinner list_Spinner;
    static int startPoint = 0;
    private ArrayAdapter<Count> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        number_EditText = (EditText) findViewById(R.id.SpinnerTestThreeActivity_number_editText);
        create_Button = (Button) findViewById(R.id.SpinnerTestThreeActivity_create_button);
        list_Spinner = (Spinner) findViewById(R.id.SpinnerTestThreeActivity_list_spinner);

        adapter = new ArrayAdapter<Count>
                (getApplicationContext(), R.layout.simple_spinner_dropdown_item, new ArrayList<Count>());
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        list_Spinner.setAdapter(adapter);

        create_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createList();
            }
        });
    }

    private void createList() {
        String number = number_EditText.getText().toString();
        if (number == null) {
            return;
        }
        if (number.equalsIgnoreCase("")) {
            return;
        }
        if (!number.matches("\\d+")) {
            return;
        }
        int tempNumber = Integer.parseInt(number);
        startPoint = tempNumber;

        ArrayList<Count> counts = new ArrayList<>();

        for (int i = startPoint; i < startPoint+10; i++) {
            counts.add(new Count(i));
        }

        adapter.clear();
        adapter.addAll(counts);
        adapter.notifyDataSetChanged();
    }



    private class Count {

        private int number;

        public Count(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return String.valueOf(this.number);
        }
    }

}
