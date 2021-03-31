/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Logic.Ticket;
import Logic.Flight;
import Logic.User;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
public class ServiceTicket extends Service {
    private static final String INSERTTICKET = "{call lab01_proc_ins_ticket(?,?,?)}";
    private static final String LISTTICKET = "{?=call lab01_fun_list_tickets()}";
    private static final String DELETETICKET = "{call lab01_proc_del_ticket(?)}";
    private static final String UPDATETICKET = "{call lab01_proc_del_ticket(?,?,?)}";
    
    private static ServiceTicket serviceTicket = new ServiceTicket();
    
        
    public void insertTicket(Ticket ticket) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTTICKET);

            pstmt.setInt(1, ticket.getTicket_id());
            pstmt.setInt(2, ticket.getTicket_flight().getFlight_id() );
            pstmt.setInt(3, ticket.getTicket_user().getUser_id());
            pstmt.setString(4, ticket.getTicket_duration_time());
            pstmt.setInt(5, ticket.getTicket_price());
            pstmt.setString(6, ticket.getTicket_seat());

            
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

    public ArrayList<Ticket> listTickets() throws GlobalException, NoDataException, SQLException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList<Ticket> coleccion = new ArrayList();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTTICKET);
            pstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);	
            boolean a = pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlight_id(rs.getInt(2));
                User user = new User();
                user.setUser_id(rs.getInt(3));
                Ticket ticket = new Ticket(
                        rs.getInt(1),
                        flight,
                        user,
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)
                );
                coleccion.add(ticket);
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
    
    public void deleteTicket(Ticket ticket) throws GlobalException, NoDataException, SQLException {
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
            pstmt = conexion.prepareCall(DELETETICKET);
            pstmt.setInt(1, ticket.getTicket_id());
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
   
    public void updateTicket(Ticket ticket) throws GlobalException, NoDataException, SQLException {
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
            pstmt = conexion.prepareCall(UPDATETICKET);
            
            pstmt.setInt(1, ticket.getTicket_id());
            pstmt.setInt(2, ticket.getTicket_flight().getFlight_id() );
            pstmt.setInt(3, ticket.getTicket_user().getUser_id());
            pstmt.setString(4, ticket.getTicket_duration_time());
            pstmt.setInt(5, ticket.getTicket_price());
            pstmt.setString(6, ticket.getTicket_seat());

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
