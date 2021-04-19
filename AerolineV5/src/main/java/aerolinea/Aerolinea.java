/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aerolinea;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.ServiceLocation;
import Logic.Location;
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
        Location location;
        //location = new Location(11222,123,"test_from", "test_to","01/01/2021 23:20" , 10);

        ServiceLocation serviceLocation = new ServiceLocation();
        ArrayList<Location> locations = new ArrayList<Location>();
        try {
            //serviceLocation.insertLocation(location);
            locations = serviceLocation.listLocations();
            //locations = serviceLocation.searchLocationsByID(111);
            locations = serviceLocation.listLocations();
        } catch (GlobalException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(locations.get(0));
        System.out.println("test");
    }

}
