package com.Maarten0162.ProgressPicBackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PostWebsocketController {
    @MessageMapping("/post/create")
    public String testMessage(
            String message
    ) {
        return "Server received: " + message;
    }
}
