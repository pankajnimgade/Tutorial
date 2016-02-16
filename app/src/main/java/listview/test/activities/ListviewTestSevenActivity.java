package listview.test.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

public class ListViewTestSevenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_test_seven);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        ListView listView = (ListView) findViewById(R.id.ListViewTestSevenActivity_listView);
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Item item = new Item("A");
            items.add(item);
        }
        MyAdapter adapter = new MyAdapter(getApplicationContext(), R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }

    private class MyAdapter extends ArrayAdapter {

        private Context a_context;
        private ArrayList<Item> items;
        private LayoutInflater a_layoutInflater;

        public MyAdapter(Context context, int resource, ArrayList<Item> items) {
            super(context, resource, items);
            this.a_context = context;
            this.items = items;
            a_layoutInflater = LayoutInflater.from(this.a_context);
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                row = a_layoutInflater.inflate(R.layout.single_item_listview_seven, parent, false);
                holder = new ViewHolder();
                holder.button = (Button) row.findViewById(R.id.ListViewTestSevenActivity_text_button);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final Item item = items.get(position);
            System.out.println("" + item.getText());

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Item current_item : items) {
                        current_item.setText("A");
                    }
                    item.setText("B");
                    notifyDataSetChanged();
                }
            });
            holder.button.setText("" + item.getText());
            return row;
        }

        private class ViewHolder {
            Button button;
        }
    }

    private class Item {
        String text;

        public Item(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


}
