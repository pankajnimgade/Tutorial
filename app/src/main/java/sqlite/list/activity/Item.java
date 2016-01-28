package sqlite.list.activity;

/**
 * Created by Pankaj Nimgade on 28-01-2016.
 */
public class Item {

    private String project_ID;
    private String project_Title;
    private String project_City;
    private String project_Type;
    private String project_Image;
    private String project_Status;


    public Item() {

    }

    public String getProject_ID() {
        return project_ID;
    }

    public void setProject_ID(String project_ID) {
        this.project_ID = project_ID;
    }

    public String getProject_Title() {
        return project_Title;
    }

    public void setProject_Title(String project_Title) {
        this.project_Title = project_Title;
    }

    public String getProject_City() {
        return project_City;
    }

    public void setProject_City(String project_City) {
        this.project_City = project_City;
    }

    public String getProject_Type() {
        return project_Type;
    }

    public void setProject_Type(String project_Type) {
        this.project_Type = project_Type;
    }

    public String getProject_Image() {
        return project_Image;
    }

    public void setProject_Image(String project_Image) {
        this.project_Image = project_Image;
    }

    public String getProject_Status() {
        return project_Status;
    }

    public void setProject_Status(String project_Status) {
        this.project_Status = project_Status;
    }
}
