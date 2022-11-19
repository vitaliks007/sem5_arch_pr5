package ru.vitaliy.pr5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.vitaliy.pr5.entity.dto.MessageInputDTO;
import ru.vitaliy.pr5.entity.dto.OutputDTO;
import ru.vitaliy.pr5.service.MessageService;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/webs")
    @SendTo("/topic/messages")
    public OutputDTO send(MessageInputDTO messageInputDTO) {
        return messageService.send(messageInputDTO);
    }

    @MessageMapping("/get_all")
    public void sendAll(@Header("simpSessionId") String sessionId) {
        messageService.sendAllMessages(sessionId);
    }
}
