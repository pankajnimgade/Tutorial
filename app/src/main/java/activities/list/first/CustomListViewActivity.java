package activities.list.first;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<SingleItem> singleItems = new ArrayList<>();
        singleItems.add(new SingleItem("http://movito.nervov.com/img/ace-hd.png","first Text"));
        singleItems.add(new SingleItem("http://movito.nervov.com/img/Movito_Logo_M.png","Second Text"));
        singleItems.add(new SingleItem("http://movito.nervov.com/img/ace-hd.png","third Text"));
        singleItems.add(new SingleItem("http://movito.nervov.com/img/Movito_Logo_M.png","fourth Text"));

        ListView listView = (ListView)findViewById(R.id.CustomListViewActivity_listView);
        MyAdapter adapter = new MyAdapter(getApplicationContext(), R.layout.single_item,singleItems);
        listView.setAdapter(adapter);


    }

    private class MyAdapter extends ArrayAdapter {

        private ArrayList<SingleItem> singleItems;
        private LayoutInflater layoutInflater;
        private Context context;
        private View single_View;

        public MyAdapter(Context context, int resource, ArrayList<SingleItem> singleItems) {
            super(context, resource, singleItems);
            this.context = context;
            this.singleItems = singleItems;
            layoutInflater = LayoutInflater.from(this.context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;

            if (row == null) {
                row = layoutInflater.inflate(R.layout.single_item, parent, false);

                holder = new ViewHolder();
                holder.textView = (TextView) row.findViewById(R.id.single_item_textView);
                holder.imageView = (ImageView) row.findViewById(R.id.single_item_imageView);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final SingleItem singleItem = singleItems.get(position);
            holder.textView.setText("" + singleItem.getText());
            Picasso.with(context).load(""+singleItem.getUrl()).into(holder.imageView);

            return row;
        }

        private class ViewHolder {
            // Instance Variable (state or data)
            TextView textView;
            ImageView imageView;
        }
    }

    public class SingleItem {

        private String url;

        private String text;

        public SingleItem() {
        }

        public SingleItem(String url, String text) {
            this.url = url;
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
