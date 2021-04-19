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
public class Ticket {

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getTicket_flight_code() {
        return ticket_flight_code;
    }

    public void setTicket_flight_code(int ticket_flight_code) {
        this.ticket_flight_code = ticket_flight_code;
    }

    public int getTicket_flight_back_code() {
        return ticket_flight_back_code;
    }

    public void setTicket_flight_back_code(int ticket_flight_back_code) {
        this.ticket_flight_back_code = ticket_flight_back_code;
    }

    public int getTicket_user_id() {
        return ticket_user_id;
    }

    public void setTicket_user_id(int ticket_user_id) {
        this.ticket_user_id = ticket_user_id;
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

    public Ticket(int ticket_id, int ticket_flight_code, int ticket_flight_back_code, int ticket_user_id, String ticket_duration_time, int ticket_price, String ticket_seat) {
        this.ticket_id = ticket_id;
        this.ticket_flight_code = ticket_flight_code;
        this.ticket_flight_back_code = ticket_flight_back_code;
        this.ticket_user_id = ticket_user_id;
        this.ticket_duration_time = ticket_duration_time;
        this.ticket_price = ticket_price;
        this.ticket_seat = ticket_seat;
    }

    public Ticket() {
    }
    public int ticket_id;
    public int ticket_flight_code;
    public int ticket_flight_back_code;   
    public int ticket_user_id;
    public String ticket_duration_time;
    public int ticket_price;
    public String ticket_seat;

}
