package com.demo.sendemaillinedemo.controller;

import com.demo.sendemaillinedemo.service.SendLineNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequestMapping("line")
public class LineController {
    private final SendLineNotifyService sendLineNotifyService;

    public LineController(SendLineNotifyService sendLineNotifyService) {
        this.sendLineNotifyService = sendLineNotifyService;
    }

    @GetMapping("/sendMessages")
    public LinkedHashMap<String, Object> sendLineNotifyMessages(@RequestParam(required = true) String msg) {
        return sendLineNotifyService.sendLineNotifyMessages(msg);
    }

    @GetMapping("/sendSticker")
    public LinkedHashMap<String, Object> sendLineNotifySticker(
            @RequestParam(required = true) String msg,
            @RequestParam(required = true) int stickerPackageId,
            @RequestParam(required = true) int stickerId) throws Exception {
        return sendLineNotifyService.sendLineNotifySticker(msg, stickerPackageId, stickerId);
    }

    @GetMapping("/sendImagePath")
    public LinkedHashMap<String, Object> sendLineNotifyImagePath(
            @RequestParam(required = true) String msg,
            @RequestParam(required = true) String imagePath) throws Exception {
        return sendLineNotifyService.sendLineNotifyImagePath(msg, imagePath);
    }

    @PostMapping("/sendImage")
    public LinkedHashMap<String, Object> sendLineNotifyImage(
            @RequestParam(required = true) String msg,
            @RequestParam("file") MultipartFile file) throws Exception {
        return sendLineNotifyService.sendLineNotifyImage(msg, file);
    }
}
