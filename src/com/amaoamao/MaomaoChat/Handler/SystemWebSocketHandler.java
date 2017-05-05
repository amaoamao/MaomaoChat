/*
 * Copyright (c) 2017 Peter Mao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amaoamao.MaomaoChat.Handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SystemWebSocketHandler implements WebSocketHandler {
    private static ArrayList<WebSocketSession> users = new ArrayList<>();
    private Map<String, String> userMaps = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        System.out.println("ConnectionEstablished");
        String i = session.getId();
        System.out.println(i + " user size:" + users.size());
        users.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session,
                              WebSocketMessage<?> message) throws Exception {
        String schatMessage = (String) message.getPayload();
        System.out.println(schatMessage);


    }

    @Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        removeUser(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
        System.out.println("ConnectionClosed");
        removeUser(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void removeUser(WebSocketSession session) {
        users.remove(session);
        userMaps.remove(session.getId());
    }
}
