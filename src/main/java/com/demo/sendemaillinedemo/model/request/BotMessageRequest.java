package com.demo.sendemaillinedemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BotMessageRequest {
    private String tokenTo;
    private String message;
}
