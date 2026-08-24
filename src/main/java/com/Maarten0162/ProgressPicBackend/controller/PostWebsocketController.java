package com.Maarten0162.ProgressPicBackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PostWebsocketController {
    @MessageMapping("/post/create")
    @SendTo("/topic/feed")
    public String testMessage(
            String message
    ) {
        return "Server received: " + message;
    }
}
