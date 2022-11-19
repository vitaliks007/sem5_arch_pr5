package ru.vitaliy.pr5.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class OutputDTO {
    List<MessageDTO> messages;
}
