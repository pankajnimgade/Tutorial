package data.binding.list.activities.four;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.nimgade.pk.mytutorialapplication.R;
import com.nimgade.pk.mytutorialapplication.databinding.ActivityDataBindingFourBinding;

public class DataBindingFourActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private User user;
    private ActivityDataBindingFourBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_four);
        user = new User(true);
        binding.setUser(user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        toggleButton = (ToggleButton) findViewById(R.id.DataBindingFourActivity_on_off_toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    user = new User(true);
                } else {
                    user = new User(false);
                }
                binding.setUser(user);
            }
        });
    }

}
