package com.ohgiraffers.rest.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Controller
@RestController
@RequestMapping("/response")
public class ResponseRestController {

    /* GET : /response/hello */
    @GetMapping("/hello")
//    @ResponseBody
    public String helloworld() {

        System.out.println("hello??");

        return "hello world!";
    }

    @GetMapping("/random")
    public int getRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }

    @GetMapping("/message")
    public Message getMessage() {
        //GSON & Jackson -> Message2Converter
        return new Message(200, "메세지를 응답합니다.");
    }

    @GetMapping("/list")
    public List<String> getList() {

        return List.of(new String[]{"사과", "바나나", "복숭아"});
    }

    @GetMapping("/map")
    public Map<Integer, String> getMapping() {

        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(200, "정상응답"));
        messageList.add(new Message(404, "페이지를 찾을 수 없습니다."));
        messageList.add(new Message(505, "개발자의 잘못입니다."));

        return messageList.stream().collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage));
    }

    // produces는 response header의 content-type 설정이다.
    @GetMapping(value="/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {

        return getClass().getResourceAsStream("/static/sample.PNG").readAllBytes();
    }

    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {

        return ResponseEntity.ok(new Message(123, "hello!!"));
    }
}