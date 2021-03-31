/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import Logic.User;
import Logic.Flight;
/**
 *
 * @author anton
 */
public class Ticket {

    /**
     * @return the ticket_id
     */
    public int getTicket_id() {
        return ticket_id;
    }

    /**
     * @param ticket_id the ticket_id to set
     */
    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    /**
     * @return the ticket_flight
     */
    public Flight getTicket_flight() {
        return ticket_flight;
    }

    /**
     * @param ticket_flight the ticket_flight to set
     */
    public void setTicket_flight(Flight ticket_flight) {
        this.ticket_flight = ticket_flight;
    }

    /**
     * @return the ticket_user
     */
    public User getTicket_user() {
        return ticket_user;
    }

    /**
     * @param ticket_user the ticket_user to set
     */
    public void setTicket_user(User ticket_user) {
        this.ticket_user = ticket_user;
    }

    /**
     * @return the ticket_duration_time
     */
    public String getTicket_duration_time() {
        return ticket_duration_time;
    }

    /**
     * @param ticket_duration_time the ticket_duration_time to set
     */
    public void setTicket_duration_time(String ticket_duration_time) {
        this.ticket_duration_time = ticket_duration_time;
    }

    /**
     * @return the ticket_price
     */
    public int getTicket_price() {
        return ticket_price;
    }

    /**
     * @param ticket_price the ticket_price to set
     */
    public void setTicket_price(int ticket_price) {
        this.ticket_price = ticket_price;
    }

    /**
     * @return the ticket_seat
     */
    public String getTicket_seat() {
        return ticket_seat;
    }

    /**
     * @param ticket_seat the ticket_seat to set
     */
    public void setTicket_seat(String ticket_seat) {
        this.ticket_seat = ticket_seat;
    }


    public Ticket(int ticket_id, Flight ticket_flight, User ticket_user, String ticket_duration_time, int ticket_price, String ticket_seat) {
        this.ticket_id = ticket_id;
        this.ticket_flight = ticket_flight;
        this.ticket_user = ticket_user;
        this.ticket_duration_time = ticket_duration_time;
        this.ticket_price = ticket_price;
        this.ticket_seat = ticket_seat;
        
    }

    public Ticket() {
    }
        
    private int ticket_id;
    private Flight ticket_flight;
    private User ticket_user;
    private String ticket_duration_time;
    private int ticket_price;
    private String ticket_seat;

}
