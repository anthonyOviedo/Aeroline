/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.ServicePlane;
import DataAccess.ServiceUser;
import Logic.Plane;
import Logic.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author anton
 */
public class Login {

    private String login(String jsonUser) throws JsonProcessingException, GlobalException, NoDataException {
        Gson gson = new Gson();
        ServiceUser serviceUser = new ServiceUser();
        User user = gson.fromJson(jsonUser, User.class);
        user = serviceUser.loginUser(user);
        return gson.toJson(user);
    }

    private String insertUser(String jsonUser) throws GlobalException, NoDataException {
        Gson gson = new Gson();
        ServiceUser serviceUser = new ServiceUser();
        User user = gson.fromJson(jsonUser, User.class);
        serviceUser.insertUser(user);
        return "Usuario Insertado";
    }

    public String processRequest(String message) throws JsonProcessingException, SQLException, GlobalException, NoDataException {
        // switch mieo aqui
        // mensajec con el formato {action = "listPlanes", object = [{},...] })
        Map<String, Object> result = new ObjectMapper().readValue(message, HashMap.class);
        String action = (String) result.get("action");
        Object obj = null;
        switch (action) {
            case "login":
                obj = result.get("object");
                return login(obj.toString());
            case "insertUser":
                obj = result.get("object");
                return insertUser(obj.toString());

        }
        return "transaccion fallida";
    }

}
