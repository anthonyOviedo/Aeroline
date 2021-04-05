/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Logic.Flight;
import Logic.Purchase;
import Logic.User;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
public class ServicePurchase extends Service {
    private static final String INSERTPURCHASE = "{call lab01_proc_ins_purchase(?,?,?)}";
    private static final String LISTPURCHASE = "{?=call lab01_fun_list_purchases()}";
    private static final String DELETEPURCHASE = "{call lab01_proc_del_purchase(?)}";
    private static final String UPDATEPURCHASE = "{call lab01_proc_del_purchase(?,?,?)}";

    private static ServicePurchase servicePurchase = new ServicePurchase();

    public void insertPurchase(Purchase purchase) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTPURCHASE);

            pstmt.setInt(1, purchase.getPurchase_id());
            pstmt.setInt(2, purchase.getPurchase_user());
            pstmt.setInt(3, purchase.getPurchase_ticket());
            pstmt.setString(4, purchase.getPurchase_date());

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

    public ArrayList<Purchase> listPurchases() throws GlobalException, NoDataException, SQLException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Purchase purchase = null;
        Flight flight = null;
        User user = null;
        ArrayList<Purchase> coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTPURCHASE);
            pstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            boolean a = pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                purchase = new Purchase(

                );
                coleccion.add(purchase);
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

    public void deletePurchase(int purchase_id) throws GlobalException, NoDataException, SQLException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;
        ResultSet rs = null;
        Purchase purchase = null;

        try {
            pstmt = conexion.prepareCall(DELETEPURCHASE);
            pstmt.setInt(1, purchase_id);
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

    public void updatePurchase(Purchase purchase) throws GlobalException, NoDataException, SQLException {
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
            pstmt = conexion.prepareCall(UPDATEPURCHASE);

            pstmt.setInt(1, purchase.getPurchase_id());
            pstmt.setInt(2, purchase.getPurchase_user());
            pstmt.setInt(3, purchase.getPurchase_ticket());
            pstmt.setString(4, purchase.getPurchase_date());

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
