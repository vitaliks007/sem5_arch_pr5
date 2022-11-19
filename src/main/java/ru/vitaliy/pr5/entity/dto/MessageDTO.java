package ru.vitaliy.pr5.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {
    private String text;
    private String time;
}
