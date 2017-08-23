package constraint.layout.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;
import java.util.List;

import model.classes.MyListItem;
import recyclerview.list.activities.RecyclerViewOneActivity;

public class ConstraintListActivity extends AppCompatActivity {

    private static final String TAG = "ConstraintList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        RecyclerView listRecyclerView = (RecyclerView) findViewById(R.id.ConstraintListActivity_RecyclerView_list);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(linearLayoutManager);
        List<MyListItem> myListItems = new ArrayList<>();
        myListItems.add(new MyListItem("First_Constraint_Test", ConstraintOneActivity.class));
        Log.d(TAG, "initializeUI: "+myListItems.size());
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), myListItems);
        listRecyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        private Context context;
        private List<MyListItem> myListItems;
        private LayoutInflater layoutInflater;

        public MyAdapter(Context context, List<MyListItem> myListItems) {
            this.context = context;
            this.myListItems = myListItems;
            layoutInflater = LayoutInflater.from(this.context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_constraint_layout_list, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final MyListItem myListItem = myListItems.get(position);
            System.out.println(""+myListItem.getActivity_name());
            holder.textView.setText(""+myListItem.getActivity_name());

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(context, myListItem.getActivity_class()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return myListItems.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout linearLayout;
            private TextView textView;


            public MyViewHolder(View itemView) {
                super(itemView);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.single_item_constraint_layout_list_linear_layout);
                textView = (TextView) itemView.findViewById(R.id.single_item_constraint_layout_list_textView);
            }
        }
    }

}
