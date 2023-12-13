package com.demo.sendemaillinedemo.controller;

import com.demo.sendemaillinedemo.service.SendLineNotifyService;
import io.micrometer.common.util.StringUtils;
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
}
