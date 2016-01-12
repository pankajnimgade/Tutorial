package model.classes;

import android.app.Application;

import java.util.ArrayList;

import activities.list.first.CustomListViewActivity;
import activities.list.first.DragTestActivityActivity;
import activities.list.first.DraggingViewsActivity;
import activities.list.first.DroppingViewActivity;
import activities.list.first.JustifyAlignmentActivity;
import activities.list.first.LoadDataActivity;
import activities.list.first.NoteListActivity;
import activities.list.first.SharedPreferencesActivity;
import activities.list.first.TestActivity;
import listview.test.activities.ListViewTestOneActivity;
import loaders.test.activities.LoadersListActivity;
import network.calls.activities.NetworkCallListActivity;

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
        myListItems.add(new MyListItem("TestActivity", TestActivity.class));
        myListItems.add(new MyListItem("Network Call", NetworkCallListActivity.class));
        myListItems.add(new MyListItem("Shared Preferences Test", SharedPreferencesActivity.class));
        myListItems.add(new MyListItem("Loader Test Test", LoadersListActivity.class));
        myListItems.add(new MyListItem("Justify Alignment", JustifyAlignmentActivity.class));
        myListItems.add(new MyListItem("ListView Test One", ListViewTestOneActivity.class));
        myListItems.add(new MyListItem("Dragging Views", DraggingViewsActivity.class));
        myListItems.add(new MyListItem("Dropping Views", DroppingViewActivity.class));
        myListItems.add(new MyListItem("Custom ListView", CustomListViewActivity.class));
        myListItems.add(new MyListItem("Load Data in spinner", LoadDataActivity.class));
        myListItems.add(new MyListItem("NoteListActivity", NoteListActivity.class));
        myListItems.add(new MyListItem("Drag View Test", DragTestActivityActivity.class));

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static ArrayList<MyListItem> getMyListItems() {
        return myListItems;
    }

    public static void setMyListItems(ArrayList<MyListItem> myListItems) {
        StartUp.myListItems = myListItems;
    }
}
