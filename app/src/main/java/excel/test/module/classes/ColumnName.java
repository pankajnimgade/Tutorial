package excel.test.module.classes;

/**
 * Created by Pankaj Nimgade on 23-01-2016.
 */
public enum ColumnName {



    STATION_NAME("STATION_NAME"), _id("_id"),

    //    direction of Train in Vertical direction, get the Time
    FIRST_TRAIN_NORTHBOUND("FIRST_TRAIN_NORTHBOUND"), LAST_TRAIN_NORTHBOUND("LAST_TRAIN_NORTHBOUND"),
    FIRST_TRAIN_SOUTHBOUND("FIRST_TRAIN_SOUTHBOUND"), LAST_TRAIN_SOUTHBOUND("LAST_TRAIN_SOUTHBOUND"),

    //    direction of Train in Vertical direction, get the Time
    FIRST_TRAIN_EASTBOUND("FIRST_TRAIN_EASTBOUND"), LAST_TRAIN_EASTBOUND("LAST_TRAIN_EASTBOUND"),
    FIRST_TRAIN_WESTBOUND("FIRST_TRAIN_WESTBOUND"), LAST_TRAIN_WESTBOUND("LAST_TRAIN_WESTBOUND"),

    // boolean values to be set for these columns
    ACCESSIBLE("ACCESSIBLE"), PRESTO_ENABLED("PRESTO_ENABLED"), TOKEN_VENDING_MACHINE("TOKEN_VENDING_MACHINE"),
    PASSENGER_PICK_UP_AND_DROP_OFF("PASSENGER_PICK_UP_AND_DROP_OFF"), BICYCLE_REPAIR_STOP("BICYCLE_REPAIR_STOP"),
    WASHROOMS("WASHROOMS"), WI_FI_ENABLED("WI_FI_ENABLED"), PASS_VENDING_MACHINE("PASS_VENDING_MACHINE"),

    //String values to be set for these values
    PLATFORM_TYPE("PLATFORM_TYPE"), STATION_OVERVIEW("STATION_OVERVIEW"),
    PARKING("PARKING"), LATITUDE("LATITUDE"), LONGITUDE("LONGITUDE"),
    DETAILS("DETAILS");

    private String column_name;

    private ColumnName(String column_name) {
        this.column_name = column_name;
    }

    @Override
    public String toString() {
        return column_name;
    }
}
