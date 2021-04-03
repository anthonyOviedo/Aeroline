/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.ServicePlane;
import Logic.Plane;
import aerolinea.Aerolinea;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.std;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.ProcessBuilder.Redirect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.ws.rs.client.Entity.json;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anton
 */
public class Admin {

    public String addNewPlane(String jsonPlane) throws JsonProcessingException, GlobalException, NoDataException {
        Gson gson = new Gson();
        ServicePlane servicePlane = new ServicePlane();
        Plane plane = gson.fromJson(jsonPlane, Plane.class);
        servicePlane.insertPlane(plane);
        return "Avion Agregado";
    }

    //devuelve un array de objetos json
    public String listPlanes() throws JsonProcessingException, SQLException {
        ServicePlane servicePlane = new ServicePlane();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        try {
            // servicePlane.insertPlane(plane);
            planes = servicePlane.listPlanes();
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray myArray = new JSONArray();
        //Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(planes);
        myArray.put(jsonString);
        System.out.print(myArray);

        return jsonString;
    }

    public String deletePlane(String jsonPlane) throws JsonProcessingException, SQLException, GlobalException, NoDataException {
        Gson gson = new Gson();
        ServicePlane servicePlane = new ServicePlane();
        Plane plane = gson.fromJson(jsonPlane, Plane.class);
        servicePlane.deletePlane(plane);
        return "Avion borrado";
    }

    private String updatePlane(String jsonPlane) throws GlobalException, NoDataException, SQLException {
        Gson gson = new Gson();
        ServicePlane servicePlane = new ServicePlane();
        Plane plane = gson.fromJson(jsonPlane, Plane.class);
        servicePlane.updatePlane(plane);
        return "Avion actualizado";
    }

    public String processRequest(String message) throws JsonProcessingException, SQLException, GlobalException, NoDataException {
        //switch mieo aqui 
        //mensajec con el formato {action = "listPlanes", object = [{},...] })
        Map<String, Object> result = new ObjectMapper().readValue(message, HashMap.class);
        String action = (String) result.get("action");
        Object obj = null;
        switch (action) {
            case "listPlanes":
                return listPlanes();

            case "addNewPlane":
                obj = result.get("object");
                return addNewPlane(obj.toString());

            case "updatePlane":
                obj = result.get("object");
                return updatePlane(obj.toString());

            case "deletePlane":
                obj = result.get("object");
                return deletePlane(obj.toString());

        }
        return "transaccion fallida";
    }

}
