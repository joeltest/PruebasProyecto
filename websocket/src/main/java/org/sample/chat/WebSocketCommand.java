/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 *
 * @author nb
 */
@ServerEndpoint(value = "/command",
        encoders = {CommandEncoder.class},
        decoders = {CommandDecoder.class})
public class WebSocketCommand {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }
    
    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void broadcastCommand(Command command, Session session) throws IOException, EncodeException {
        System.out.println("command: " + command);
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                System.out.println("Avisando a :"+peer.getId());
                peer.getBasicRemote().sendObject(command);
            }
        }
    }

//    @OnMessage
//    public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {
//        System.out.println("broadcastBinary: " + data);
//        for (Session peer : peers) {
//            if (!peer.equals(session)) {
//                peer.getBasicRemote().sendBinary(data);
//            }
//        }
//    }

    
}
