/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author anton
 */
public class User {

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return the user_password
     */
    public String getUser_password() {
        return user_password;
    }

    /**
     * @param user_password the user_password to set
     */
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    /**
     * @return the user_type
     */
    public String getUser_type() {
        return user_type;
    }

    /**
     * @param user_type the user_type to set
     */
    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    /**
     * @return the user_lastnames
     */
    public String getUser_lastnames() {
        return user_lastnames;
    }

    /**
     * @param user_lastnames the user_lastnames to set
     */
    public void setUser_lastnames(String user_lastnames) {
        this.user_lastnames = user_lastnames;
    }

    /**
     * @return the user_email
     */
    public String getUser_email() {
        return user_email;
    }

    /**
     * @param user_email the user_email to set
     */
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    /**
     * @return the user_birthday
     */
    public String getUser_birthday() {
        return user_birthday;
    }

    /**
     * @param user_birthday the user_birthday to set
     */
    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    /**
     * @return the user_address
     */
    public String getUser_address() {
        return user_address;
    }

    /**
     * @param user_address the user_address to set
     */
    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    /**
     * @return the user_workphone
     */
    public int getUser_workphone() {
        return user_workphone;
    }

    /**
     * @param user_workphone the user_workphone to set
     */
    public void setUser_workphone(int user_workphone) {
        this.user_workphone = user_workphone;
    }

    /**
     * @return the user_personalphone
     */
    public int getUser_personalphone() {
        return user_personalphone;
    }

    /**
     * @param user_personalphone the user_personalphone to set
     */
    public void setUser_personalphone(int user_personalphone) {
        this.user_personalphone = user_personalphone;
    }

    public User() {
    }

    public User(int user_id, String user_name, String user_password, String user_type, String user_lastnames, String user_email, String user_birthday, String user_address, int user_workphone, int user_personalphone) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_type = user_type;
        this.user_lastnames = user_lastnames;
        this.user_email = user_email;
        this.user_birthday = user_birthday;
        this.user_address = user_address;
        this.user_workphone = user_workphone;
        this.user_personalphone = user_personalphone;
    }
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_type;
    private String user_lastnames;
    private String user_email;
    private String user_birthday;
    private String user_address;
    private int user_workphone;
    private int user_personalphone;
}
