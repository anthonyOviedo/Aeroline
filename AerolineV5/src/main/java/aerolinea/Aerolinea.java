/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aerolinea;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.ServicePlane;
import Logic.Plane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList; // import the ArrayList class
import webSocketResource.resourceAdmin;
/**
 *
 * @author Antony
 */
public class Aerolinea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // Plane plane = new Plane(1211,"ass",315);
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
        System.out.println(planes.get(0));
        
        System.out.println("test");

    }

}
