package recyclerview.list.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

/**
 * This code is made to show {@link RecyclerView} and how can you
 * handle touch on a item in the RecyclerView
 * <p>this answers {http://stackoverflow.com/questions/36441096/recyclerview-programmatically-click-performclick}</p>
 */
public class RecyclerViewOneActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewOneActivity_RecyclerView);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("first");
        strings.add("second");
        MyAdapter adapter = new MyAdapter(getApplicationContext(), strings);
        recyclerView.setAdapter(adapter);

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private Context context;
        private ArrayList<String> strings;
        private LayoutInflater layoutInflater;

        public MyAdapter(Context context, ArrayList<String> strings) {
            this.context = context;
            this.strings = strings;
            layoutInflater = LayoutInflater.from(this.context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.single_item_recycler_view_one, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final String text = this.strings.get(position);
            holder.textView.setText("" + text);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+text, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout linearLayout;
            private TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.single_item_recycler_view_one_linear_layout);
                textView = (TextView) itemView.findViewById(R.id.single_item_recycler_view_one_textView);
            }
        }
    }
}
