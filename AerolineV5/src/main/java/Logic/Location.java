/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author anton
 */
public class Location {

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getLocation_city_name() {
        return location_city_name;
    }

    public void setLocation_city_name(String location_city_name) {
        this.location_city_name = location_city_name;
    }

    public String getLocation_airport_name() {
        return location_airport_name;
    }

    public void setLocation_airport_name(String location_airport_name) {
        this.location_airport_name = location_airport_name;
    }

    public String getLocation_country() {
        return location_country;
    }

    public void setLocation_country(String location_country) {
        this.location_country = location_country;
    }

    public Location() {
    }

    public Location(int location_id, String location_city_name, String location_airport_name, String location_country) {
        this.location_id = location_id;
        this.location_city_name = location_city_name;
        this.location_airport_name = location_airport_name;
        this.location_country = location_country;
    }
    public int location_id;
    public String location_city_name ;
    public String location_airport_name ;
    public String location_country ;
}
