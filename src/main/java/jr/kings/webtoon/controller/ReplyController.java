package jr.kings.webtoon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Reply;
import jr.kings.webtoon.service.EpisodeService;
import jr.kings.webtoon.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private ReplyService replyService;

    // 댓글 등록
    @PostMapping(
        value="/new", 
        consumes="application/json",
        produces=MediaType.TEXT_PLAIN_VALUE
    )
    
    public ResponseEntity<String> addReply(@RequestBody Reply reply){

        log.info("addReply......");

        Member member = new Member();
        member.setId("user");
        reply.setMember(member);

        replyService.replyRegister(reply);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 댓글 조회
    @GetMapping(value="/{eno}/{page}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Reply> getReplyList(
        @PathVariable("eno") Integer eno,
        @PathVariable("page") Integer page,
        @PathVariable("amount") Integer amount
    ){
        List<Reply> list = new ArrayList<>();
        List<Object[]> replyArr = episodeService.getShowReply(eno, page, amount);
        Member member = new Member();
        member.setId("user");
        
        for(int i=0;i<replyArr.size();i++){
            ((Reply) replyArr.get(i)[1]).setMember(member);
            list.add((Reply) replyArr.get(i)[1]);
            
        }
        return list;
    }



}