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

    public int getFlight_id() {
        return Flight_id;
    }

    public void setFlight_id(int Flight_id) {
        this.Flight_id = Flight_id;
    }

    public int getFlight_plane() {
        return Flight_plane;
    }

    public void setFlight_plane(int Flight_plane) {
        this.Flight_plane = Flight_plane;
    }

    public String getFlight_from() {
        return flight_from;
    }

    public void setFlight_from(String flight_from) {
        this.flight_from = flight_from;
    }

    public String getFlight_to() {
        return flight_to;
    }

    public void setFlight_to(String flight_to) {
        this.flight_to = flight_to;
    }

    public String getFlight_time() {
        return flight_time;
    }

    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }

    public int getFlight_price() {
        return flight_price;
    }

    public void setFlight_price(int flight_price) {
        this.flight_price = flight_price;
    }

    public Flight() {
    }

    public Flight(int Flight_id, int Flight_plane, String flight_from, String flight_to, String flight_time, int flight_price) {
        this.Flight_id = Flight_id;
        this.Flight_plane = Flight_plane;
        this.flight_from = flight_from;
        this.flight_to = flight_to;
        this.flight_time = flight_time;
        this.flight_price = flight_price;
    }
 
    public int Flight_id;
    public int Flight_plane;
    public String flight_from;
    public String flight_to;
    public String flight_time;
    public int flight_price;
    
}
