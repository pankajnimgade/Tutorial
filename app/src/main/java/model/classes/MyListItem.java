package model.classes;

/**
 * This helps in Making individual list item which can be feed to
 * ListView or RecyclerView to generate desired list.
 * This is just an example, and not a standard
 * Created by Pankaj Nimgade on 04-01-2016.
 */
public class MyListItem {

    private String activity_name;

    private Class activity_class;

    public MyListItem(String activity_name, Class activity_class) {
        this.activity_name = activity_name;
        this.activity_class = activity_class;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public Class getActivity_class() {
        return activity_class;
    }

    public void setActivity_class(Class activity_class) {
        this.activity_class = activity_class;
    }

    /**
     * @return name of the Activity which describes what it does
     */
    @Override
    public String toString() {
        return this.activity_name;
    }
}
