package com.shabab.simplws;

import org.springframework.web.socket.WebSocketSession;

import java.util.*;

public class Session {
static Map synchronizedMapObject = Collections.synchronizedMap(new HashMap<Integer, WebSocketSession>());
static Map synchronizedMapObjectSeekbar = Collections.synchronizedMap(new HashMap<Integer, WebSocketSession>());

 //   static   List<WebSocketSession > sessions=new ArrayList<>();


}
