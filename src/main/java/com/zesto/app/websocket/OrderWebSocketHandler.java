package com.zesto.app.websocket;

import com.zesto.app.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class OrderWebSocketHandler extends TextWebSocketHandler {

    private final DataService dataService;

    @Autowired
    public OrderWebSocketHandler(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        dataService.addSession(session);
        System.out.println("⚡ WebSocket connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received WS Message: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        dataService.removeSession(session);
        System.out.println("🔥 WebSocket connection closed: " + session.getId());
    }
}
