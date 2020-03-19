package jr.kings.webtoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
@AllArgsConstructor
public class SecurityController {

    @Autowired
    MemberService memberService;

    // 회원가입
    @PostMapping(value = "/joinMember", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addMember(@RequestBody Member member) {

        log.info(member + "");
        memberService.memberRegister(member);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }



}