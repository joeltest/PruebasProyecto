/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.chat;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author nb
 */
public class CommandEncoder implements Encoder.Text<Command> {

    @Override
    public String encode(Command command) throws EncodeException {
        return command.getJson().toString();
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    
}
