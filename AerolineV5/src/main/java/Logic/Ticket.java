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

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getTicket_flight() {
        return ticket_flight;
    }

    public void setTicket_flight(int ticket_flight) {
        this.ticket_flight = ticket_flight;
    }

    public int getTicket_user() {
        return ticket_user;
    }

    public void setTicket_user(int ticket_user) {
        this.ticket_user = ticket_user;
    }

    public String getTicket_duration_time() {
        return ticket_duration_time;
    }

    public void setTicket_duration_time(String ticket_duration_time) {
        this.ticket_duration_time = ticket_duration_time;
    }

    public int getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(int ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getTicket_seat() {
        return ticket_seat;
    }

    public void setTicket_seat(String ticket_seat) {
        this.ticket_seat = ticket_seat;
    }

    public Ticket(){}

    public Ticket(int ticket_id, int ticket_flight, int ticket_user, String ticket_duration_time, int ticket_price, String ticket_seat) {
        this.ticket_id = ticket_id;
        this.ticket_flight = ticket_flight;
        this.ticket_user = ticket_user;
        this.ticket_duration_time = ticket_duration_time;
        this.ticket_price = ticket_price;
        this.ticket_seat = ticket_seat;
    }
    
        public int ticket_id;
        public int ticket_flight;
        public int ticket_user;
        public String ticket_duration_time;
        public int ticket_price;
        public String ticket_seat;

}
