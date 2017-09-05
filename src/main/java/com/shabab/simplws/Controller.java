package com.shabab.simplws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {



    @RequestMapping(value = "/v1/getMSG/{houseId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String findImages(@PathVariable("houseId") long houseId) {
        JsonObject jsonObject = new JsonObject();

        try {


            List<WebSocketSession > webSocketSessions=new ArrayList<>(Session.synchronizedMapObject.values());


            for (WebSocketSession socketSession:webSocketSessions){

                socketSession.sendMessage(new TextMessage("server notify"+""+System.currentTimeMillis()));

            }




         /* for (WebSocketSession socketSession:Session.sessions){

              socketSession.sendMessage(new TextMessage("send ,session in="+System.currentTimeMillis()));

          }*/



            jsonObject.addProperty("result", true);

            return new Gson().toJson(jsonObject);

        } catch (Exception ex) {

            jsonObject.addProperty("result", false);
            return new Gson().toJson(jsonObject);


        }

    }

}
