/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aerolinea;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.ServiceFlight;
import Logic.Flight;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList; // import the ArrayList class

/**
 *
 * @author Antony
 */
public class Aerolinea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Flight flight;
        flight = new Flight(11222,123,"test_from", "test_to","01/01/2021 23:20" , 10);
        
        ServiceFlight serviceFlight = new ServiceFlight();
        ArrayList<Flight> flights = new ArrayList<Flight>();
        try {
            
            //serviceFlight.insertFlight(flight);
            flights = serviceFlight.listFlights();
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(flights.get(0));

        System.out.println("test");

    }

}
