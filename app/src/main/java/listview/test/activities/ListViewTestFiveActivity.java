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
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

public class ListViewTestFiveActivity extends AppCompatActivity{

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test_five);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView)findViewById(R.id.ListViewTestFiveActivity_listView);
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Item item = new Item();
            item.setCount(i);
            items.add(item);
        }

        MyAdapter adapter = new MyAdapter(getApplicationContext(), R.layout.single_item_listview_five, items);
        listView.setAdapter(adapter);
    }

    public class MyAdapter extends ArrayAdapter{

        private ArrayList<Item> items;
        private Context a_context;
        private LayoutInflater a_layoutInflater;

        public MyAdapter(Context context, int resource, ArrayList<Item> items) {
            super(context, resource, items);
            this.a_context = context;
            this.items = items;
            a_layoutInflater = LayoutInflater.from(this.a_context);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                row = a_layoutInflater.inflate(R.layout.single_item_listview_five, parent, false);
                holder = new ViewHolder();
                holder.plus = (Button) row.findViewById(R.id.ListViewTestFiveActivity_plus_button);
                holder.textView = (TextView) row.findViewById(R.id.ListViewTestFiveActivity_count_textView4);
                holder.minus = (Button) row.findViewById(R.id.ListViewTestFiveActivity_minus_button);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final Item item = items.get(position);
            holder.textView.setText("" + item.getCount());
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int increase_count =item.getCount();
                    item.setCount(++increase_count);
                    notifyDataSetChanged();
                }
            });
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int decrease_count =item.getCount();
                    item.setCount(--decrease_count );
                    notifyDataSetChanged();

                }
            });
            return row;
        }

        public class ViewHolder{
            Button plus;
            TextView textView;
            TextView minus;
        }
    }

    private class Item {
        int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
