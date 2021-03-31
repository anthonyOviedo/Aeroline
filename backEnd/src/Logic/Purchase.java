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
public class Purchase {

    /**
     * @return the purchase_id
     */
    public int getPurchase_id() {
        return purchase_id;
    }

    /**
     * @param purchase_id the purchase_id to set
     */
    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    /**
     * @return the purchase_user
     */
    public User getPurchase_user() {
        return purchase_user;
    }

    /**
     * @param purchase_user the purchase_user to set
     */
    public void setPurchase_user(User purchase_user) {
        this.purchase_user = purchase_user;
    }

    /**
     * @return the purchase_ticket
     */
    public Ticket getPurchase_ticket() {
        return purchase_ticket;
    }

    /**
     * @param purchase_ticket the purchase_ticket to set
     */
    public void setPurchase_ticket(Ticket purchase_ticket) {
        this.purchase_ticket = purchase_ticket;
    }

    /**
     * @return the purchase_date
     */
    public String getPurchase_date() {
        return purchase_date;
    }

    /**
     * @param purchase_date the purchase_date to set
     */
    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public Purchase() {
    }

    public Purchase(int purchase_id, User purchase_user, Ticket purchase_ticket, String purchase_date) {
        this.purchase_id = purchase_id;
        this.purchase_user = purchase_user;
        this.purchase_ticket = purchase_ticket;
        this.purchase_date = purchase_date;
    }
    private int purchase_id;
    private User purchase_user;
    private Ticket purchase_ticket;
    private String purchase_date;
}
