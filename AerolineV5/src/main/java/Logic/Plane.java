/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import javax.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anton
 */
public class Plane {

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public String getPlane_name() {
        return plane_name;
    }

    public void setPlane_name(String plane_name) {
        this.plane_name = plane_name;
    }

    public int getPlane_seats() {
        return plane_seats;
    }

    public void setPlane_seats(int plane_seats) {
        this.plane_seats = plane_seats;
    }

    public Plane() {
    }

    public Plane(int plane_id, String plane_name, int plane_seats) {
        this.plane_id = plane_id;
        this.plane_name = plane_name;
        this.plane_seats = plane_seats;
    }

    public int plane_id;
    public String plane_name;
    public int plane_seats;

}
