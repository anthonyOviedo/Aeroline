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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_lastnames() {
        return user_lastnames;
    }

    public void setUser_lastnames(String user_lastnames) {
        this.user_lastnames = user_lastnames;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_workphone() {
        return user_workphone;
    }

    public void setUser_workphone(String user_workphone) {
        this.user_workphone = user_workphone;
    }

    public String getUser_personalphone() {
        return user_personalphone;
    }

    public void setUser_personalphone(String user_personalphone) {
        this.user_personalphone = user_personalphone;
    }

    public User() {
    }

    public User(int user_id, String user_name, String user_password, String user_type, String user_lastnames, String user_email, String user_birthday, String user_address, String user_workphone, String user_personalphone) {
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
    public int user_id;
    public String user_name;
    public String user_password;
    public String user_type;
    public String user_lastnames;
    public String user_email;
    public String user_birthday;
    public String user_address;
    public String user_workphone;
    public String user_personalphone;
}
