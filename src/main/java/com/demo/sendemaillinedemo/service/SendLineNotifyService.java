package com.demo.sendemaillinedemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

@Service
@Slf4j
public class SendLineNotifyService {
    @Value("${line.notify.url}")
    private String lineNotifyURL;
    @Value("${line.notify.token}")
    private String lineNotifyToken;

    public LinkedHashMap<String, Object> sendLineNotifyMessages(String msg) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("message", msg);
        return callLineNotify(map);
    }

    public LinkedHashMap<String, Object> sendLineNotifySticker(String msg, int stickerPackageId, int stickerId) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("message", msg);
        map.add("stickerPackageId", stickerPackageId);
        map.add("stickerId", stickerId);
        return callLineNotify(map);
    }

    public LinkedHashMap<String, Object> sendLineNotifyImagePath(String msg, String imagePath) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("message", msg);
        map.add("imageThumbnail", imagePath);
        map.add("imageFullsize", imagePath);
        return callLineNotify(map);
    }

    public LinkedHashMap<String, Object> sendLineNotifyImage(String msg, MultipartFile file) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("message", msg);
        map.add("imageFile", file.getResource());
        return callLineNotify(map);
    }

    @SuppressWarnings("unchecked")
    private LinkedHashMap<String, Object> callLineNotify(MultiValueMap<String, Object> map) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        if (map.get("imageFile") != null) {
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        } else {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        headers.add("Authorization", "Bearer " + lineNotifyToken);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(lineNotifyURL, request, LinkedHashMap.class);
    }
}


