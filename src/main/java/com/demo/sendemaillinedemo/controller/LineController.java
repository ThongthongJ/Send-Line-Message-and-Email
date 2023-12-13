package com.demo.sendemaillinedemo.controller;

import com.demo.sendemaillinedemo.model.request.BotMessageRequest;
import com.demo.sendemaillinedemo.service.LineChatBotService;
import com.demo.sendemaillinedemo.service.SendLineNotifyService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequestMapping("line")
public class LineController {
    @Autowired
    private SendLineNotifyService sendLineNotifyService;
    @Autowired
    private LineChatBotService lineChatBotService;


    @GetMapping("/sendMessages")
    public LinkedHashMap<String, Object> sendLineNotifyMessages(@RequestParam(value = "msg", required = true) String msg) {
        return sendLineNotifyService.sendLineNotifyMessages(msg);
    }

    @GetMapping("/sendSticker")
    public LinkedHashMap<String, Object> sendLineNotifySticker(
            @RequestParam(value = "msg", required = true) String msg,
            @RequestParam(value = "stickerPackageId", required = true) String stickerPackageId,
            @RequestParam(value = "stickerId", required = true) String stickerId) {
        Integer stkPackId = StringUtils.isEmpty(stickerPackageId) ? null : Integer.parseInt(stickerPackageId);
        Integer stkId = StringUtils.isEmpty(stickerId) ? null : Integer.parseInt(stickerId);
        return sendLineNotifyService.sendLineNotifySticker(msg, stkPackId, stkId);
    }

    @GetMapping("/sendImagePath")
    public LinkedHashMap<String, Object> sendLineNotifyImagePath(
            @RequestParam(value = "msg", required = true) String msg,
            @RequestParam(value = "imagePath", required = true) String imagePath) {
        return sendLineNotifyService.sendLineNotifyImagePath(msg, imagePath);
    }

    @PostMapping("/sendImageFile")
    public LinkedHashMap<String, Object> sendLineNotifyImage(
            @RequestParam(value = "msg", required = true) String msg,
            @RequestParam("file") MultipartFile file) {
        return sendLineNotifyService.sendLineNotifyImage(msg, file);
    }

    @PostMapping("/botmessage")
    public void lineBotMessage(@RequestBody BotMessageRequest request) {
        lineChatBotService.sendBotMessage(request);
    }

//    @GetMapping("/chatbot")
//    public void lineChatBOT(@RequestParam(value = "msg") String message) {
//        TextMessageContent textMessageContent = new TextMessageContent("id", message);
//        Source source = new UserSource("U29f2f88b8823d24af793597570bae72a");
//        Instant timestamp = Instant.now();
//        String replyToken = "I don't know about this Token!";
//        MessageEvent<TextMessageContent> messageEvent = new MessageEvent<>(replyToken, source, textMessageContent, timestamp);
//
//        lineChatBotService.handleTextMessage(messageEvent);
//    }
}
