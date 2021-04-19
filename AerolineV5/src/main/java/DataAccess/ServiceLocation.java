/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Logic.Location;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
public class ServiceLocation extends Service {
    private static final String LISTLOCATIONS = "{?=call lab01_fun_list_locations}";
    public ArrayList<Location> listLocations() throws GlobalException, NoDataException, SQLException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList<Location> coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTLOCATIONS);
            pstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            boolean a = pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);

            while (rs.next()) {
                Location location = new Location(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                coleccion.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

}
