/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webSocketResource;

import Control.Admin;
import Control.Public;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author anton
 */
@ServerEndpoint("/resourcePublic")
public class resourcePublic {

  private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
        System.out.println("********************************************* Conectado");
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void onMessage(String message, Session session)
            throws JsonProcessingException, GlobalException, NoDataException, IOException, SQLException {
        for (Session peer : peers) {
            System.out.println(peer);
        }
        Public pub = new Public();
        String DataResponse = pub.processRequest(message);
        session.getBasicRemote().sendText(DataResponse);
        //experimento
        onClose(session);
    }
    
}
