/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import Logic.Plane;
/**
 *
 * @author anton
 */
public class Flight {

    /**
     * @return the Flight_id
     */
    public int getFlight_id() {
        return Flight_id;
    }

    /**
     * @param Flight_id the Flight_id to set
     */
    public void setFlight_id(int Flight_id) {
        this.Flight_id = Flight_id;
    }

    /**
     * @return the Flight_plane
     */
    public Plane getFlight_plane() {
        return Flight_plane;
    }

    /**
     * @param Flight_plane the Flight_plane to set
     */
    public void setFlight_plane(Plane Flight_plane) {
        this.Flight_plane = Flight_plane;
    }

    /**
     * @return the flight_from
     */
    public String getFlight_from() {
        return flight_from;
    }

    /**
     * @param flight_from the flight_from to set
     */
    public void setFlight_from(String flight_from) {
        this.flight_from = flight_from;
    }

    /**
     * @return the flight_to
     */
    public String getFlight_to() {
        return flight_to;
    }

    /**
     * @param flight_to the flight_to to set
     */
    public void setFlight_to(String flight_to) {
        this.flight_to = flight_to;
    }

    /**
     * @return the flight_time
     */
    public String getFlight_time() {
        return flight_time;
    }

    /**
     * @param flight_time the flight_time to set
     */
    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }

    /**
     * @return the flight_price
     */
    public int getFlight_price() {
        return flight_price;
    }

    /**
     * @param flight_price the flight_price to set
     */
    public void setFlight_price(int flight_price) {
        this.flight_price = flight_price;
    }

    public Flight() {
    }

    public Flight(int Flight_id, Plane Flight_plane, String flight_from, String flight_to, String flight_time, int flight_price) {
        this.Flight_id = Flight_id;
        this.Flight_plane = Flight_plane;
        this.flight_from = flight_from;
        this.flight_to = flight_to;
        this.flight_time = flight_time;
        this.flight_price = flight_price;
    }
    private int Flight_id;
    private Plane Flight_plane;
    private String flight_from;
    private String flight_to;
    private String flight_time;
    private int flight_price;
    
}
