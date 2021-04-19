/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Logic.User;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
public class ServiceUser extends Service {

    private static final String INSERTUSER = "{call lab01_proc_ins_user(?,?,?,?,?,?,?,?,?,?)}";
    private static final String LISTUSER = "{?=call lab01_fun_list_users()}";
    private static final String DELETEUSER = "{call lab01_proc_del_user(?)}";
    private static final String UPDATEUSER = "{call lab01_proc_del_user(?,?,?)}";
    private static final String LOGINUSER = "{?=call lab01_proc_login_user(?,?)}";

    private static ServiceUser serviceUser = new ServiceUser();
        
    public void insertUser(User user) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTUSER);

            pstmt.setInt(1, user.getUser_id());
            pstmt.setString(2, user.getUser_name());
            pstmt.setString(3, user.getUser_password());
            pstmt.setString(4, user.getUser_type());
            pstmt.setString(5, user.getUser_lastnames());
            pstmt.setString(6, user.getUser_email());
            pstmt.setString(7, user.getUser_birthday());
            pstmt.setString(8, user.getUser_address());
            pstmt.setString(9, user.getUser_workphone());
            pstmt.setString(10, user.getUser_personalphone());
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

    public ArrayList<User> listUsers() throws GlobalException, NoDataException, SQLException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        User user = null;
        ArrayList<User> coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTUSER);
            pstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            boolean a = pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                );
                coleccion.add(user);
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

    public void deleteUser(User user) throws GlobalException, NoDataException, SQLException {
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
            pstmt = conexion.prepareCall(DELETEUSER);
            pstmt.setInt(1, user.getUser_id());
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

    public void updateUser(User user) throws GlobalException, NoDataException, SQLException {
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
            pstmt = conexion.prepareCall(UPDATEUSER);

            pstmt.setInt(1, user.getUser_id());
            pstmt.setString(2, user.getUser_name());
            pstmt.setString(3, user.getUser_password());
            pstmt.setString(4, user.getUser_type());
            pstmt.setString(5, user.getUser_lastnames());
            pstmt.setString(6, user.getUser_email());
            pstmt.setString(7, user.getUser_birthday());
            pstmt.setString(8, user.getUser_address());
            pstmt.setString(9, user.getUser_workphone());
            pstmt.setString(10, user.getUser_personalphone());

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

    public User loginUser(User user) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LOGINUSER);
            pstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            pstmt.setString(2, user.getUser_email());
            pstmt.setString(3, user.getUser_password());
            boolean a = pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                );
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
        return user;

    }

}
