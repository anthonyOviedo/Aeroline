/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.ServiceTicket;
import DataAccess.ServiceUser;
import Logic.Ticket;
import Logic.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anton
 */
public class Public {

    public String processRequest(String message) throws JsonProcessingException, SQLException, GlobalException, NoDataException {
        // switch mieo aqui
        // mensajec con el formato {action = "listPlanes", object = [{},...] })
        Map<String, Object> result = new ObjectMapper().readValue(message, HashMap.class);
        String action = (String) result.get("action");
        Object obj = null;
        switch (action) {
            case "buyTicket":
                obj = result.get("object");
                return buyTicket(obj.toString());

        }
        return "transaccion fallida";
    }

    private String buyTicket(String jsonTicket) throws GlobalException, NoDataException {
        Gson gson = new Gson();
        ServiceTicket serviceTicket = new ServiceTicket();
        Ticket ticket = gson.fromJson(jsonTicket, Ticket.class);
        serviceTicket.insertTicket(ticket);
        return "Ticket adquirido";    }

}
