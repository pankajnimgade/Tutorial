package data.binding.list.activities.five;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.nimgade.pk.mytutorialapplication.R;
import com.nimgade.pk.mytutorialapplication.databinding.ActivityDataBindingFiveBinding;

public class DataBindingFiveActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingFiveBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_five);
        user = new User("Friend", "notFriend");
        binding.setUser(user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        toggleButton = (ToggleButton) findViewById(R.id.DataBindingFiveActivity_on_off_toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setFriend(isChecked);
            }
        });
    }

}
