package listview.test.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

public class ListViewTestSevenActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test_seven);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        listView = (ListView) findViewById(R.id.ListViewTestSevenActivity_listView);
        ArrayList<MyItem> myItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            myItems.add(new MyItem(false, "name_" + i + 1));
        }
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), R.id.ListViewTestSevenActivity_item_textView, myItems);
        listView.setAdapter(myAdapter);
    }

    public class MyAdapter extends ArrayAdapter {

        private ArrayList<MyItem> items;
        private Context a_context;
        private LayoutInflater a_layoutInflater;
        private boolean isSelectAll;

        public MyAdapter(Context context, int resource, ArrayList<MyItem> items) {
            super(context, resource, items);
            this.a_context = context;
            this.items = items;
            isSelectAll = false;
            a_layoutInflater = LayoutInflater.from(this.a_context);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                row = a_layoutInflater.inflate(R.layout.single_item_listview_seven_check_box, parent, false);
                holder = new ViewHolder();
                holder.isSelected_checkBox = (CheckBox) row.findViewById(R.id.ListViewTestSevenActivity_select_checkBox);
                holder.item_Name_textView = (TextView) row.findViewById(R.id.ListViewTestSevenActivity_item_textView);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final MyItem myItem = items.get(position);


            holder.isSelected_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        myItem.setSelected(isChecked);
                    }
                }
            });

            if (isSelectAll) {
                holder.isSelected_checkBox.setChecked(true);
            }else {
                holder.isSelected_checkBox.setChecked(myItem.isSelected());
            }
            holder.item_Name_textView.setText(""+myItem.getItem_Name());

            notifyDataSetChanged();
            return row;
        }


        public boolean isSelectAll() {
            return isSelectAll;
        }

        public void setSelectAll(boolean selectAll) {
            isSelectAll = selectAll;
        }

        public class ViewHolder {
            CheckBox isSelected_checkBox;
            TextView item_Name_textView;
        }
    }

    private class MyItem {

        private boolean isSelected;
        private String item_Name;

        public MyItem(boolean isSelected, String item_Name) {
            this.isSelected = isSelected;
            this.item_Name = item_Name;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getItem_Name() {
            return item_Name;
        }

        public void setItem_Name(String item_Name) {
            this.item_Name = item_Name;
        }
    }

}
