package com.ohgiraffers.rest.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

    /* ResponseEntity란?
     * 결과 데이터 http 상태 코드를 직접 제어할 수 있는 클래스이다.
     * HttpStatus, HttpHeader, HttpBody를 제어할 수 있다.
     * */

    private List<UserDTO> users;

    public ResponseEntityTestController() {
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new java.util.Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new java.util.Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new java.util.Date()));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        HttpHeaders headers = new HttpHeaders();
        // Content-Type : "application/json;charset=UTF-8"
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회성공!", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공!", responseMap));
    }

    @PostMapping("/users")
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser) { //?는 any 라는 의미. 좋은 방법은 아니지만..

        System.out.println("newUser = " + newUser);

        int lastUserNo = users.get(users.size() - 1).getNo();
        newUser.setNo(lastUserNo + 1);

        users.add(newUser);

        return ResponseEntity
                .created(URI.create("/entity/users/" + users.get(users.size() - 1).getNo()))          // 201번 응답코드
                .build();
    }

    @PostMapping("user/{userNo}")
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO modifyInfo, @PathVariable int userNo) {

        UserDTO foundUser =
                users.stream()
                        .filter(user -> user.getNo() == userNo)
                        .collect(Collectors.toList())
                        .get(0);
        foundUser.setId(modifyInfo.getId());
        foundUser.setPwd(modifyInfo.getPwd());
        foundUser.setName(modifyInfo.getName());

        return ResponseEntity
                .created(URI.create("/entity/users/" + userNo))
                .build();
    }

    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo) {

        UserDTO foundUser = users.stream()
                .filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList())
                .get(0);
        users.remove(foundUser);

        return ResponseEntity
                .noContent()        //204
                .build();

    }

}



    //  /boards/{boardNo}/replies       컨텍스트를 보드와 리플라이 묶으면 맞고,

    /*
    /users             // 전체 유저 조회
    /users/{식별번호}   //한명유저 조회
    /users/{userId}   //이건 안됨. 무조건 데이터 식별하는 값을 넣어줘야 함. 식별값은 No 한가지밖에 없음. 따라서 userNo로 해야함.
    /users?enrollDate=20200101
     */