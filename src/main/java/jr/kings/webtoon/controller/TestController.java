package jr.kings.webtoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * TestController
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/bookTest")
    public void test(){
        log.info("test........");
    }
    @GetMapping("/member")
    public void member(){
        log.info("member........");
    }

    @GetMapping("/admin")
    public void admin(){
        log.info("admin........");
    }
    
    @GetMapping("/customLogout")
    public void logout(){
        log.info("logout.........");
    }
    
}