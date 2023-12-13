package com.demo.sendemaillinedemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineMessagingModel {
    private String to;
    private List<MessageModel> messages;
}
