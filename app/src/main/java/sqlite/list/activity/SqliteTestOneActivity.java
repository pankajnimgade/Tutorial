package sqlite.list.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.nimgade.pk.mytutorialapplication.R;

public class SqliteTestOneActivity extends AppCompatActivity {

    private Button save_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        save_Button = (Button) findViewById(R.id.SqliteTestOneActivity_button);
        save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyReader myReader = new MyReader(getApplicationContext());
                myReader.open();
                myReader.insert("Jack", "Sam");
                myReader.close();
            }
        });
    }

    private static class MyReader {
        private static final String Table_Name = "TestOne_Table";
        private final Context mCtx;
        private SQLiteDatabase mDb;
        private InternalHelper mDbHelper;

        public MyReader(Context mCtx) {
            this.mCtx = mCtx;
        }

        public MyReader open() {
            this.mDbHelper = new InternalHelper(this.mCtx);
            this.mDb = mDbHelper.getWritableDatabase();
            return this;
        }

        public void insert(String first, String second) {
            ContentValues values = new ContentValues();
            values.put("first_column_name", first);
            values.put("second_column_name", second);
            mDb.insert(Table_Name, null, values);
        }


        public void close() {
            if (mDbHelper != null) {
                mDbHelper.close();
            }
        }

        private static class InternalHelper extends SQLiteOpenHelper {

            private static final String DATABASE_NAME = "database_name.sqlite";
            private static final int DATABASE_VERSION = 1;

            public InternalHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
            }

            public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
            }
        }
    }

}
