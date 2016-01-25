package excel.test.module.classes;

import java.io.Serializable;

/**
 * Created by Pankaj Nimgade on 22-01-2016.
 */
public class Station implements Serializable {

    static final long serialVersionUID = 42L;

    private SubwayLine subwayLine;
    private String stationDetails;
    private String station_Name;
    private String station_ID;
    /**
     * Direction of train in vertical pattern
     */
    private String first_Train_Northbound;
    private String last_Train_Northbound;
    private String first_Train_Southbound;
    private String last_Train_Southbound;

    /**
     * direction of train in horizontal pattern
     */
    private String first_Train_Eastbound;
    private String last_Train_Eastbound;
    private String first_Train_Westbound;
    private String last_Train_Westbound;

    private boolean accessible;
    private boolean presto_Enabled;
    private boolean token_Vending_Machine;
    private boolean passenger_Pick_up_and_Drop_off;
    private boolean bicycle_Repair_Stop;
    private boolean washrooms;
    private boolean wi_fi_Enabled;
    private boolean pass_Vending_Machine;

    private String platform_Type;
    private String station_Overview;
    private String parking;

    /**
     * coordinates for station location this is used in the Google maps
     */
    private String latitude;
    private String longitude;

    public Station() {
    }

    public SubwayLine getSubwayLine() {
        return subwayLine;
    }

    public void setSubwayLine(SubwayLine subwayLine) {
        this.subwayLine = subwayLine;
    }

    public String getStationDetails() {
        return stationDetails;
    }

    public void setStationDetails(String stationDetails) {
        this.stationDetails = stationDetails;
    }

    public String getStation_Name() {
        return station_Name;
    }

    public void setStation_Name(String station_Name) {
        this.station_Name = station_Name;
    }

    public String getStation_ID() {
        return station_ID;
    }

    public void setStation_ID(String station_ID) {
        this.station_ID = station_ID;
    }

    public String getFirst_Train_Northbound() {
        return first_Train_Northbound;
    }

    public void setFirst_Train_Northbound(String first_Train_Northbound) {
        this.first_Train_Northbound = first_Train_Northbound;
    }

    public String getLast_Train_Northbound() {
        return last_Train_Northbound;
    }

    public void setLast_Train_Northbound(String last_Train_Northbound) {
        this.last_Train_Northbound = last_Train_Northbound;
    }

    public String getFirst_Train_Southbound() {
        return first_Train_Southbound;
    }

    public void setFirst_Train_Southbound(String first_Train_Southbound) {
        this.first_Train_Southbound = first_Train_Southbound;
    }

    public String getLast_Train_Southbound() {
        return last_Train_Southbound;
    }

    public void setLast_Train_Southbound(String last_Train_Southbound) {
        this.last_Train_Southbound = last_Train_Southbound;
    }

    public String getFirst_Train_Eastbound() {
        return first_Train_Eastbound;
    }

    public void setFirst_Train_Eastbound(String first_Train_Eastbound) {
        this.first_Train_Eastbound = first_Train_Eastbound;
    }

    public String getLast_Train_Eastbound() {
        return last_Train_Eastbound;
    }

    public void setLast_Train_Eastbound(String last_Train_Eastbound) {
        this.last_Train_Eastbound = last_Train_Eastbound;
    }

    public String getFirst_Train_Westbound() {
        return first_Train_Westbound;
    }

    public void setFirst_Train_Westbound(String first_Train_Westbound) {
        this.first_Train_Westbound = first_Train_Westbound;
    }

    public String getLast_Train_Westbound() {
        return last_Train_Westbound;
    }

    public void setLast_Train_Westbound(String last_Train_Westbound) {
        this.last_Train_Westbound = last_Train_Westbound;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public boolean isPresto_Enabled() {
        return presto_Enabled;
    }

    public void setPresto_Enabled(boolean presto_Enabled) {
        this.presto_Enabled = presto_Enabled;
    }

    public boolean isToken_Vending_Machine() {
        return token_Vending_Machine;
    }

    public void setToken_Vending_Machine(boolean token_Vending_Machine) {
        this.token_Vending_Machine = token_Vending_Machine;
    }

    public boolean isPassenger_Pick_up_and_Drop_off() {
        return passenger_Pick_up_and_Drop_off;
    }

    public void setPassenger_Pick_up_and_Drop_off(boolean passenger_Pick_up_and_Drop_off) {
        this.passenger_Pick_up_and_Drop_off = passenger_Pick_up_and_Drop_off;
    }

    public boolean isBicycle_Repair_Stop() {
        return bicycle_Repair_Stop;
    }

    public void setBicycle_Repair_Stop(boolean bicycle_Repair_Stop) {
        this.bicycle_Repair_Stop = bicycle_Repair_Stop;
    }

    public boolean isWashrooms() {
        return washrooms;
    }

    public void setWashrooms(boolean washrooms) {
        this.washrooms = washrooms;
    }

    public boolean isWi_fi_Enabled() {
        return wi_fi_Enabled;
    }

    public void setWi_fi_Enabled(boolean wi_fi_Enabled) {
        this.wi_fi_Enabled = wi_fi_Enabled;
    }

    public boolean isPass_Vending_Machine() {
        return pass_Vending_Machine;
    }

    public void setPass_Vending_Machine(boolean pass_Vending_Machine) {
        this.pass_Vending_Machine = pass_Vending_Machine;
    }

    public String isPlatform_Type() {
        return platform_Type;
    }

    public void setPlatform_Type(String platform_Type) {
        this.platform_Type = platform_Type;
    }

    public String getStation_Overview() {
        return station_Overview;
    }

    public void setStation_Overview(String station_Overview) {
        this.station_Overview = station_Overview;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "station_Name: " + station_Name + "\nstation_ID: " + station_ID + "\nfirst_Train_Northbound:  " + first_Train_Northbound +
                "\nfirst_Train_Southbound: " + first_Train_Southbound + "\nlast_Train_Northbound: " + last_Train_Northbound +
                "\nlast_Train_Southbound: " + last_Train_Southbound + "\nfirst_Train_Eastbound: " + first_Train_Eastbound +
                "\nfirst_Train_Westbound: " + first_Train_Westbound + "\nlast_Train_Eastbound: " + last_Train_Eastbound +
                "\nlast_Train_Westbound: " + last_Train_Westbound + "\naccessible: " + accessible + "\npresto_Enabled: " + presto_Enabled +
                "\ntoken_Vending_Machine: " + token_Vending_Machine +
                "\npassenger_Pick_up_and_Drop_off: " + passenger_Pick_up_and_Drop_off +
                "\nbicycle_Repair_Stop: " + bicycle_Repair_Stop + "\nwashrooms: " + washrooms + "\nwi_fi_Enabled: " + wi_fi_Enabled +
                "\npass_Vending_Machine: " + pass_Vending_Machine + "\nplatform_Type: " + platform_Type +
                "\nstation_Overview: " + station_Overview + "\nparking: " + parking + "\nlatitude: " + latitude +
                "\nlongitude: " + longitude + " " + stationDetails;
    }
}
