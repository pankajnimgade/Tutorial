package data.binding.list.activities.two;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nimgade.pk.mytutorialapplication.R;
import com.nimgade.pk.mytutorialapplication.databinding.ActivitydatabindingtwoBinding;

public class DataBindingTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitydatabindingtwoBinding binding = DataBindingUtil.setContentView(this, R.layout.activitydatabindingtwo);
        Data data = new Data("First Name","Last Name", "1234483518");
        binding.setData(data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
    }

}
