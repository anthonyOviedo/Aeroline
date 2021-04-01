/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webSocketResource;

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
@ServerEndpoint("/resourceAdmin")
public class resourceAdmin {
    
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }

    private void processMessage(String msg){
       
    }
    
    @OnMessage
    public void onMessage(String message) {
        for (Session peer : peers) {
            System.out.println(peer);
        }
        System.out.println("@@@ msj :"+ message);
    }
}
