package com.shabab.simplws;


import com.google.gson.Gson;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {




   // List sessions = new CopyOnWriteArrayList<>();
List<WebSocketSession > sessions=new ArrayList<>();




    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

Map<String,Object> map= session.getAttributes();


        System.out.println("uri="+session.getUri());
        System.out.println("uri="+session.getRemoteAddress());



        int id  =  Integer.parseInt(new AntPathMatcher()
                .extractPathWithinPattern( "/{id}/**", session.getUri().getPath() ));


        System.out.println("here="+message.getPayload());




Session.synchronizedMapObject.remove(id);
List<WebSocketSession >webSocketSessions=new ArrayList<>(Session.synchronizedMapObject.values());


        for (WebSocketSession socketSession:webSocketSessions){

            socketSession.sendMessage(new TextMessage(message.getPayload()+""+System.currentTimeMillis()));

        }
        Session.synchronizedMapObject.put(id,session);


        /*for (WebSocketSession webSocketSession : sessions) {
            Map value = new Gson().fromJson(message.getPayload(), Map.class);
            webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
        }*/
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        //the messages will be broadcasted to all users.
        System.out.println("id="+session.getId());


        System.out.println("uri="+session.getUri());
        System.out.println("uri="+session.getRemoteAddress());
        String url = "/name/{id}";
        int id  =  Integer.parseInt(new AntPathMatcher()
                .extractPathWithinPattern( "/{id}/**", session.getUri().getPath() ));





        System.out.println("connected");
      //  sessions.add(session);
        session.sendMessage(new TextMessage("You are now connected to the server. This is the first message from server....."));

        Session.synchronizedMapObject.put(id,session);
//Session.sessions.add(session);

    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection Closed！"+status);
        sessions.remove(session);
    }




}