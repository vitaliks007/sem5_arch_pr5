package ru.vitaliy.pr5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;
import ru.vitaliy.pr5.dao.MessageDao;
import ru.vitaliy.pr5.entity.Message;
import ru.vitaliy.pr5.entity.dto.MessageDTO;
import ru.vitaliy.pr5.entity.dto.MessageInputDTO;
import ru.vitaliy.pr5.entity.dto.OutputDTO;
import ru.vitaliy.pr5.service.MessageService;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;
    private final SimpMessagingTemplate messagingTemplate;

    public OutputDTO send(MessageInputDTO messageInputDTO) {
        Message message = new Message();
        message.setText(messageInputDTO.getText());
        message.setTime(LocalDateTime.now());
        messageDao.save(message);
        OutputDTO output = new OutputDTO();
        output.setMessages(Collections.singletonList(
                new MessageDTO(message.getText(), message.getTime().toString())
        ));
        return output;
    }

    public void sendAllMessages(String sessionId) {
        List<Message> messages = messageDao.findAll();
        OutputDTO output = new OutputDTO();
        output.setMessages(messages.stream()
                .map(message -> new MessageDTO(message.getText(), message.getTime().toString()))
                .collect(Collectors.toList())
        );
        SimpMessageHeaderAccessor headerAccessor =
                SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        messagingTemplate.convertAndSend("/topic/messages-" + sessionId, output);
    }
}
