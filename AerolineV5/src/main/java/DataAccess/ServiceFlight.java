/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;
import Logic.Flight;
import Logic.Plane;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
public class ServiceFlight extends Service {
    private static final String INSERTFLIGHT = "{call lab01_proc_ins_flight(?,?,?)}";
    private static final String LISTFLIGHT = "{?=call lab01_fun_list_flights()}";
    private static final String DELETEFLIGHT = "{call lab01_proc_del_flight(?)}";
    private static final String UPDATEFLIGHT = "{call lab01_proc_del_flight(?,?,?)}";
    
        
    public void insertFlight(Flight flight) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTFLIGHT);

            pstmt.setInt(1, flight.getFlight_id());
            pstmt.setInt(2, flight.getFlight_plane().getPlane_id());
            pstmt.setString(3, flight.getFlight_from());
            pstmt.setString(4, flight.getFlight_to());
            pstmt.setString(5, flight.getFlight_time());
            pstmt.setInt(6, flight.getFlight_price());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public ArrayList<Flight> listFlights() throws GlobalException, NoDataException, SQLException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Flight flight = null;
        Plane plane = null;
        ArrayList<Flight> coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTFLIGHT);
            pstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);	
            boolean a = pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next()) {
                rs.getInt(2);
                flight = new Flight(
                        rs.getInt(1),
                        plane,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
                coleccion.add(flight);
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
    
    public void deleteFlight(int flight_id) throws GlobalException, NoDataException, SQLException {
            try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;
        ResultSet rs = null;
        Flight flight = null;

        try {
            pstmt = conexion.prepareCall(DELETEFLIGHT);
            pstmt.setInt(1, flight_id);
            pstmt.execute();
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
    }
   
    public void updateFlight(Flight flight) throws GlobalException, NoDataException, SQLException {
            try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conexion.prepareCall(UPDATEFLIGHT);
            
            pstmt.setInt(1, flight.getFlight_id());
            pstmt.setInt(2, flight.getFlight_plane().getPlane_id());
            pstmt.setString(3, flight.getFlight_from());
            pstmt.setString(4, flight.getFlight_to());
            pstmt.setString(5, flight.getFlight_time());
            pstmt.setInt(6, flight.getFlight_price());
            
            pstmt.execute();
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
    }
        

}
