package model.classes;

import android.app.Application;

import java.util.ArrayList;

import activities.list.first.JustifyAlignmentActivity;
import activities.list.first.TestActivity;

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
        myListItems.add(new MyListItem("Justify Alignment", JustifyAlignmentActivity.class));
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
