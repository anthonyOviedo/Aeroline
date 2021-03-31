/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Logic.Plane;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DataAccess.Service;
import java.sql.Types;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author anton
 */
public class ServicePlane extends Service{
    private static final String INSERTTPLANE = "{call lab01_proc_ins_plane(?,?,?)}";
    private static final String LISTPLANE = "{?=call lab01_fun_list_planes()}";
    private static final String DELETEPLANE = "{call lab01_proc_del_plane(?)}";
    private static final String UPDATEPLANE = "{call lab01_proc_del_plane(?,?,?)}";
    
    private static ServicePlane servicePlane = new ServicePlane();
    
        
    public void insertPlane(Plane plane) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTTPLANE);

            pstmt.setInt(1, plane.getPlane_id());
            pstmt.setString(2, plane.getPlane_name());
            pstmt.setInt(3, plane.getPlane_sits());
            
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

    public ArrayList<Plane> listPlanes() throws GlobalException, NoDataException, SQLException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Plane plane = null;
        ArrayList<Plane> coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTPLANE);
            pstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);	
            boolean a = pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next()) {
                plane = new Plane(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                coleccion.add(plane);
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
    
    public void deletePlane(int plane_id) throws GlobalException, NoDataException, SQLException {
            try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;
        ResultSet rs = null;
        Plane plane = null;

        try {
            pstmt = conexion.prepareCall(DELETEPLANE);
            pstmt.setInt(1, plane_id);
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
   
    public void updatePlane(Plane plane) throws GlobalException, NoDataException, SQLException {
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
            pstmt = conexion.prepareCall(UPDATEPLANE);
            
            pstmt.setInt(1, plane.getPlane_id());
            pstmt.setString(2, plane.getPlane_name());
            pstmt.setInt(3, plane.getPlane_sits());
            
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
