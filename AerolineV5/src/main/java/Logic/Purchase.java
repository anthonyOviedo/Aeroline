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

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getPurchase_user() {
        return purchase_user;
    }

    public void setPurchase_user(int purchase_user) {
        this.purchase_user = purchase_user;
    }

    public int getPurchase_ticket() {
        return purchase_ticket;
    }

    public void setPurchase_ticket(int purchase_ticket) {
        this.purchase_ticket = purchase_ticket;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public Purchase() {
    }

    public int purchase_id;
    public int purchase_user;
    public int purchase_ticket;
    public String purchase_date;

}
