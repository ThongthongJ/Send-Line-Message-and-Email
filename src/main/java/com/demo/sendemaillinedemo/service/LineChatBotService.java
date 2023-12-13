package com.demo.sendemaillinedemo.service;

import com.demo.sendemaillinedemo.model.LineMessagingModel;
import com.demo.sendemaillinedemo.model.MessageModel;
import com.demo.sendemaillinedemo.model.request.BotMessageRequest;
import com.google.gson.Gson;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@LineMessageHandler
public class LineChatBotService {

    //    @Autowired
//    private LineMessagingClient lineMessagingClient;
    @Value("${line.bot.channel-token}")
    private String botToken;
    @Value("${line.message.url}")
    private String messageURL;

    public void sendBotMessage(BotMessageRequest request) {
        log.info("request : {}", request);
        RestTemplate restTemplate = new RestTemplate();

//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("to", request.getTokenTo());
//        body.add("message", request.getMessage());

        var body = LineMessagingModel.builder()
                .to(request.getTokenTo())
                .messages(List.of(MessageModel.builder()
                        .type("text")
                        .text(request.getMessage())
                        .build()))
                .build();
        Gson gson = new Gson();
        log.info("body >> : {}", gson.toJson(body));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + botToken);

        HttpEntity<LineMessagingModel> requestHttp = new HttpEntity<>(body, headers);

        log.info("url : {}", messageURL);
        restTemplate.postForObject(messageURL, requestHttp, String.class);
    }

//    @EventMapping
//    public void handleTextMessage(MessageEvent<TextMessageContent> event) {
//        // Process the text message content
//        String userMessage = event.getMessage().getText();
//        String response = generateResponse(userMessage);
//
//        // Reply to the user
//        replyToUser(event.getReplyToken(), response);
//    }
//
//    private String generateResponse(String userMessage) {
//        // Your logic to generate a response based on the user's message
//        return "You said: " + userMessage;
//    }
//
//    private void replyToUser(String replyToken, String response) {
//        // Use LineMessagingClient to send a reply message
//        ReplyMessage replyMessage = new ReplyMessage(replyToken, new TextMessage(response));
//
//        // Send the reply message
//        lineMessagingClient.replyMessage(replyMessage);
//    }
}
