package com.shabab.simplws;


import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SocketHandlerSeekbar extends TextWebSocketHandler {


List<WebSocketSession > sessions=new ArrayList<>();



    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

Map<String,Object> map= session.getAttributes();

        System.out.println("uri="+session.getUri());
        System.out.println("uri="+session.getRemoteAddress());
        System.out.println("seekbar value get...");

        int id  =  Integer.parseInt(new AntPathMatcher()
                .extractPathWithinPattern( "/{id}/**", session.getUri().getPath() ));

        System.out.println("here="+message.getPayload());


Session.synchronizedMapObjectSeekbar.remove(id);
List<WebSocketSession >webSocketSessions=new ArrayList<>(Session.synchronizedMapObjectSeekbar.values());


        for (WebSocketSession socketSession:webSocketSessions){

            socketSession.sendMessage(new TextMessage(message.getPayload()));

        }
        Session.synchronizedMapObjectSeekbar.put(id,session);


    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("id="+session.getId());
        System.out.println("seekbar open...");

        System.out.println("uri="+session.getUri());
        System.out.println("uri="+session.getRemoteAddress());

        int id  =  Integer.parseInt(new AntPathMatcher()
                .extractPathWithinPattern( "/{id}/**", session.getUri().getPath() ));






        System.out.println("connected");

        Session.synchronizedMapObjectSeekbar.put(id,session);


    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection Closed！"+status);
        sessions.remove(session);
    }




}