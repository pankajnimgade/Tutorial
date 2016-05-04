package data.binding.list.activities.three;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nimgade.pk.mytutorialapplication.R;
import com.nimgade.pk.mytutorialapplication.databinding.ActivityDataBindingThreeBinding;

public class DataBindingThreeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingThreeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_three);
        ThirdHandler thirdHandler = new ThirdHandler();
        binding.setThirdHandler(thirdHandler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {

    }

}
