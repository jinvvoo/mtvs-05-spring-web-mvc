package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/first/*")
@SessionAttributes("id")
public class FirstController {

    /* /first/regist */
    @GetMapping("/regist")
    public void regist() {
    }

    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {
        // WebRequest 는 스프링을 사용한거지 서블릿을 사용한게 아님
        // HttpServletRequest request

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 " + price + "원으로 등록하였습니다!!";
        model.addAttribute("message", message);

        return "first/messagePrinter";

        // 굳이 이렇게 사용하진 않음
        // 꼭 request에 있는걸 사용하고 싶을 때 사용하는 코드
    }

    @GetMapping("modify")
    public void modify() {
    }


    @PostMapping("modify")
    public String modify(Model model, @RequestParam(required = false, name = "name") String modifyName,
                         @RequestParam(defaultValue = "0") int modifyPrice) {

        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 가격을 변경하였습니다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @PostMapping("modify2")
    public String modifyMenu2(Model model, @RequestParam Map<String, String> parameters) {

        String modifyName = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = "메뉴의 이름은" + modifyName + "(으)로, 가격을 " + modifyPrice + "원으로 변경하였습니다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search() {
    }


    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu) { // @ModelAttribute("menu") 삭제가능
//    public String searchMenu(@ModelAttribute MenuDTO menu) {

        System.out.println("menu = " + menu);

        return "first/searchResult";

    }

    @GetMapping("login")
    public void login() {
    }

    @PostMapping("login1")
//    public String sessionTest1(HttpServletRequest request, @RequestParam String id) {

    //        HttpSession session = request.getSession();

    public String sessionTest1(HttpSession session, @RequestParam String id) {


        session.setAttribute("id", id);

        return "first/loginResult";
    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session) {

        session.invalidate();

        return "first/loginResult";
    }

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){

        model.addAttribute("id", id);

        return "first/loginResult";
    }

    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus){

        sessionStatus.setComplete();

        return "first/loginResult";
    }

    @GetMapping("body")
    public void body() {}


    @PostMapping("body")
    public void bodyTest(@RequestBody String body, @RequestHeader("content-type") String contentType,
                         @CookieValue(value="JSESSIONID", required = false ) String sessionId) {

        System.out.println("body = " + body);
        System.out.println("contentType = " + contentType);
        System.out.println("sessionId = " + sessionId);
    }
}


