package miscellaneous.list.activities;

import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

public class ContextualActionModeTestOneActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextual_action_mode_test_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        textView = (TextView) findViewById(R.id.ContextualActionModeTestOneActivity_textView);
        registerForContextMenu(textView);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                startActionMode(new ActionBarCallBack());
                return true;
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //user has long pressed your TextView
        menu.add(0, v.getId(), 0, "text that you want to show in the context menu - I use simply Copy");

        //cast the received View to TextView so that you can get its text
        TextView yourTextView = (TextView) v;

        //place your TextView's text in clipboard
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(yourTextView.getText());
    }


    class ActionBarCallBack implements android.view.ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            mode.setTitle("Do it");
            getMenuInflater().inflate(R.menu.contextual_action_mode_test_one_activity, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            switch(item.getItemId()){
                case R.id.ContextualActionModeTestOneActivity_add:
                    Toast.makeText(getBaseContext(), "add this text somewhere ", Toast.LENGTH_LONG).show();
                    mode.finish();    // Automatically exists the action mode, when the user selects this action
                    break;
                case R.id.ContextualActionModeTestOneActivity_search:
                    Toast.makeText(getBaseContext(), "search this text ", Toast.LENGTH_LONG).show();
                    break;
                case R.id.ContextualActionModeTestOneActivity_sort:
                    Toast.makeText(getBaseContext(), "sort", Toast.LENGTH_LONG).show();
                    break;
                case R.id.ContextualActionModeTestOneActivity_help:
                    Toast.makeText(getBaseContext(), "help with this", Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {

        }
    }
}
