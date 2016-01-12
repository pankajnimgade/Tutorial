package listview.test.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ProductInfo> productInfos;
    private ArrayAdapter<ProductInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {

        listView = (ListView)findViewById(R.id.CustomListViewActivity_listView_two);

        productInfos = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setText("Product_1_"+i);
            productInfos.add(productInfo);
        }

        adapter = new MyAdapter(getApplicationContext(), R.layout.single_item_custom_one, productInfos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductInfo productInfo = (ProductInfo) listView.getItemAtPosition(position);
                productInfo.setSelected(true);
                adapter.notifyDataSetChanged();
            }
        });

    }


    private class MyAdapter extends ArrayAdapter {

        private ArrayList<ProductInfo> a_productInfos;
        private Context a_context;
        private LayoutInflater a_layoutInflater;

        public MyAdapter(Context context, int resource, ArrayList<ProductInfo> a_productInfos) {
            super(context, resource, a_productInfos);
            this.a_productInfos = a_productInfos;
            this.a_context = context;
            a_layoutInflater = LayoutInflater.from(this.a_context);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                row = a_layoutInflater.inflate(R.layout.single_item_custom_one, parent, false);
                holder = new ViewHolder();
                holder.product_name = (TextView) row.findViewById(R.id.single_item_custom_one_textView);
                holder.item_LinearLayout = (LinearLayout) row.findViewById(R.id.single_item_custom_one_linearLayout);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final ProductInfo productInfo = a_productInfos.get(position);
            holder.product_name.setText(""+productInfo.getText());

            if (productInfo.isSelected) {
                holder.item_LinearLayout.setBackgroundColor(Color.parseColor("#ff44ff"));
            }else {
                holder.item_LinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }

            return row;
        }

        class ViewHolder {
            TextView product_name;
            LinearLayout item_LinearLayout;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }
    }

    private class ProductInfo {
        private String text;
        private boolean isSelected;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
