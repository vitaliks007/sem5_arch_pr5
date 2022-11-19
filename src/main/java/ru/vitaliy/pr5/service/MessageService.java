package ru.vitaliy.pr5.service;

import ru.vitaliy.pr5.entity.dto.MessageInputDTO;
import ru.vitaliy.pr5.entity.dto.OutputDTO;

public interface MessageService {
    OutputDTO send(MessageInputDTO messageInputDTO);
    void sendAllMessages(String sessionId);
}
