/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.ServiceFlight;
import DataAccess.ServiceLocation;
import DataAccess.ServicePlane;
import Logic.Flight;
import Logic.Plane;
import Logic.Location;
import aerolinea.Aerolinea;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author anton
 */
public class Admin {

    public String processRequest(String message) throws JsonProcessingException, SQLException, GlobalException, NoDataException {
        // switch mieo aqui
        // mensajec con el formato {action = "listPlanes", object = [{},...] })
        Map<String, Object> result = new ObjectMapper().readValue(message, HashMap.class);
        String action = (String) result.get("action");
        Object obj = null;
        switch (action) {
            case "listLocations":
                return listLocations();

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

            case "listFlights":
                return listFlights();

            case "addNewFlight":
                obj = result.get("object");
                return addNewFlight(obj.toString());

            case "deleteFlight":
                obj = result.get("object");
                return deleteFlight(obj.toString());
            case "updateFlight":
                obj = result.get("object");
                return updateFlight(obj.toString());

            case "searchPlanesById":
                obj = result.get("object");
                return searchPlanesByID(obj.toString());

            case "searchPlanesByName":
                obj = result.get("object");
                return searchPlanesByName(obj.toString());
            case "getFlights":
                obj =  result.get("object");
                ArrayList<String> param = (ArrayList)obj;
                return getFlights(param.get(0),param.get(1),param.get(2));

        }
        return "transaccion fallida";
    }

    private String searchPlanesByID(String token) throws SQLException, JsonProcessingException {
        ServicePlane servicePlane = new ServicePlane();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        try {
            planes = servicePlane.searchPlanesByID(Integer.parseInt(token));
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray myArray = new JSONArray();
        // Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(planes);
        myArray.put(jsonString);
        System.out.print(myArray);

        return jsonString;
    }

    private String searchPlanesByName(String token) throws SQLException, JsonProcessingException {
        ServicePlane servicePlane = new ServicePlane();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        try {
            // servicePlane.insertPlane(plane);
            planes = servicePlane.searchPlanesByName(token);
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray myArray = new JSONArray();
        // Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(planes);
        myArray.put(jsonString);
        System.out.print(myArray);

        return jsonString;
    }

    private String addNewPlane(String jsonPlane) throws JsonProcessingException, GlobalException, NoDataException {
        Gson gson = new Gson();
        ServicePlane servicePlane = new ServicePlane();
        Plane plane = gson.fromJson(jsonPlane, Plane.class);
        servicePlane.insertPlane(plane);
        return "Avion Agregado";
    }

    private String listPlanes() throws JsonProcessingException, SQLException {
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
        // Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(planes);
        myArray.put(jsonString);
        System.out.print(myArray);

        return jsonString;
    }

    private String deletePlane(String jsonPlane) throws JsonProcessingException, SQLException, GlobalException, NoDataException {
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

    private String listFlights() throws JsonProcessingException, SQLException {
        ServiceFlight serviceFlight = new ServiceFlight();
        ArrayList<Flight> flights = new ArrayList<Flight>();
        try {
            // servicePlane.insertPlane(plane);
            flights = serviceFlight.listFlights();
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray myArray = new JSONArray();
        // Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(flights);
        myArray.put(jsonString);
        System.out.print(myArray);

        return jsonString;
    }

    private String addNewFlight(String jsonFlight) throws JsonProcessingException, GlobalException, NoDataException {
        Gson gson = new Gson();
        ServiceFlight serviceFlight = new ServiceFlight();
        Flight flight = gson.fromJson(jsonFlight, Flight.class);
        serviceFlight.insertFlight(flight);
        return "Vuelo Agregado";
    }

    private String deleteFlight(String jsonFlight) throws JsonProcessingException, SQLException, GlobalException, NoDataException {
        Gson gson = new Gson();
        ServiceFlight serviceFlight = new ServiceFlight();
        Flight flight = gson.fromJson(jsonFlight, Flight.class);
        serviceFlight.deleteFlight(flight);
        return "Avion borrado";
    }

    private String updateFlight(String jsonFlight) throws GlobalException, NoDataException, SQLException {
        Gson gson = new Gson();
        ServiceFlight serviceFlight = new ServiceFlight();
        Flight flight = gson.fromJson(jsonFlight, Flight.class);
        serviceFlight.updateFlight(flight);
        return "Avion actualizado";
    }

    private String listLocations() throws JsonProcessingException, SQLException {
        ServiceLocation serviceLocation = new ServiceLocation();
        ArrayList<Location> locations = new ArrayList<Location>();
        try {
            locations = serviceLocation.listLocations();
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(locations);
        return jsonString;
    }

    private String getFlights(String src,String dest, String date) throws SQLException, JsonProcessingException {
        ServiceFlight serviceFlight = new ServiceFlight();
        ArrayList<Flight> flight = new ArrayList<Flight>();
        
        try {
            flight = serviceFlight.getFlights(src,dest,date);
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        // Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(flight);
        return jsonString;
    }

}
