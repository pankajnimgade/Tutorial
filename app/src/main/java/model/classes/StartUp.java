package model.classes;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import activities.list.first.CustomListViewActivity;
import activities.list.first.DeserializeTrainObjectActivity;
import activities.list.first.DragTestActivityActivity;
import activities.list.first.DragTestTwoActivity;
import activities.list.first.DraggingViewsActivity;
import activities.list.first.DroppingViewActivity;
import activities.list.first.ExampleOneActivity;
import activities.list.first.JustifyAlignmentActivity;
import activities.list.first.LoadDataActivity;
import activities.list.first.NoteListActivity;
import activities.list.first.RandomTestActivity;
import activities.list.first.SharedPreferencesActivity;
import activities.list.first.SimpleFramworkTestOneActivity;
import activities.list.first.TestActivity;
import activities.list.first.TestLayoutActivity;
import activities.list.first.TestTextViewActivity;
import activities.list.first.TestThreadActivity;
import animation.list.test.AnimationListActivity;
import constraint.layout.activities.ConstraintListActivity;
import custom.list.activities.CustomListActivity;
import data.binding.list.activities.DataBindingListActivity;
import drag.list.activities.DragListActivity;
import google.map.list.activities.GoogleMapListActivity;
import listview.test.activities.ListViewListActivity;
import loaders.test.activities.LoadersListActivity;
import miscellaneous.list.activities.MiscellaneousListActivity;
import network.calls.activities.NetworkCallListActivity;
import recyclerview.list.activities.RecyclerViewListActivity;
import services.list.activity.ServicesListActivity;
import spinner.list.activities.SpinnerListActivity;
import sqlite.list.activity.CreateDatabase;
import sqlite.list.activity.SqliteListActivity;
import viewpager.list.activities.ViewPagerListActivity;

/**
 * Created by Pankaj Nimgade on 04-01-2016.
 */
public class StartUp extends Application {

    private static ArrayList<MyListItem> myListItems = new ArrayList<>();

    static {
        myListItems = new ArrayList<>();
        initializeList();
    }



    private static void initializeList() {
        myListItems.add(new MyListItem("ConstraintLayout", ConstraintListActivity.class));
        myListItems.add(new MyListItem("TestActivity", TestActivity.class));
        myListItems.add(new MyListItem("Network Call", NetworkCallListActivity.class));
        myListItems.add(new MyListItem("Google Map", GoogleMapListActivity.class));
        myListItems.add(new MyListItem("Services Test", ServicesListActivity.class));
        myListItems.add(new MyListItem("DataBinding Test", DataBindingListActivity.class));
        myListItems.add(new MyListItem("Shared Preferences Test", SharedPreferencesActivity.class));
        myListItems.add(new MyListItem("Loader Test Test", LoadersListActivity.class));
        myListItems.add(new MyListItem("ListView Test", ListViewListActivity.class));
        myListItems.add(new MyListItem("RecyclerView Test", RecyclerViewListActivity.class));
        myListItems.add(new MyListItem("Spinner Test", SpinnerListActivity.class));
        myListItems.add(new MyListItem("SQLite Test", SqliteListActivity.class));
        myListItems.add(new MyListItem("Custom Test", CustomListActivity.class));
        myListItems.add(new MyListItem("ViewPager List", ViewPagerListActivity.class));
        myListItems.add(new MyListItem("Miscellaneous List", MiscellaneousListActivity.class));
        myListItems.add(new MyListItem("Drag List", DragListActivity.class));
        myListItems.add(new MyListItem("Animation Test", AnimationListActivity.class));
        myListItems.add(new MyListItem("Justify Alignment", JustifyAlignmentActivity.class));
        myListItems.add(new MyListItem("RandomTestActivity", RandomTestActivity.class));
        myListItems.add(new MyListItem("Dragging Views", DraggingViewsActivity.class));
        myListItems.add(new MyListItem("Dropping Views", DroppingViewActivity.class));
        myListItems.add(new MyListItem("DragTestTwoActivity", DragTestTwoActivity.class));
        myListItems.add(new MyListItem("Custom ListView", CustomListViewActivity.class));
        myListItems.add(new MyListItem("Load Data in spinner", LoadDataActivity.class));
        myListItems.add(new MyListItem("NoteListActivity", NoteListActivity.class));
        myListItems.add(new MyListItem("Drag View Test", DragTestActivityActivity.class));
        myListItems.add(new MyListItem("Test layout", TestLayoutActivity.class));
        myListItems.add(new MyListItem("example test", ExampleOneActivity.class));
        myListItems.add(new MyListItem("test text view style", TestTextViewActivity.class));
        myListItems.add(new MyListItem("Deserialize object", DeserializeTrainObjectActivity.class));
        myListItems.add(new MyListItem("TestThreadActivity", TestThreadActivity.class));
        myListItems.add(new MyListItem("SimpleFramworkTestOneActivity", SimpleFramworkTestOneActivity.class));

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("StartUp: ","onCreate: try creating database");

        CreateDatabase createAllTables = new CreateDatabase(getApplicationContext());
        SQLiteDatabase database = createAllTables.getWritableDatabase();

    }

    public static ArrayList<MyListItem> getMyListItems() {
        return myListItems;
    }

    public static void setMyListItems(ArrayList<MyListItem> myListItems) {
        StartUp.myListItems = myListItems;
    }
}
