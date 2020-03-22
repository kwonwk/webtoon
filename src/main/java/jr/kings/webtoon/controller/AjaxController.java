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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jr.kings.webtoon.domain.AlbumFile;
import jr.kings.webtoon.domain.Episode;
import jr.kings.webtoon.domain.Interested;
import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Scrap;
import jr.kings.webtoon.domain.Webtoon;
import jr.kings.webtoon.domain.WebtoonFile;
import jr.kings.webtoon.service.EpisodeService;
import jr.kings.webtoon.service.MemberService;
import jr.kings.webtoon.service.WebtoonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RestController
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/rest")
public class AjaxController {

    @Autowired
    MemberService memberService;

    @Autowired
    EpisodeService episodeService;

    @Autowired 
    WebtoonService webtoonService;

    // 웹툰 조회창 클릭시 웹툰 이미지, OCR파일들의 경로를 보내주는 메서드.
    @GetMapping(value = "/interest/{eno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Episode getEpisode(@PathVariable("eno") Integer eno) {
        Episode episode = new Episode();
      
        List<Object[]> objArrList = episodeService.getShowEpisode(eno);
        if (objArrList.get(0)[0] instanceof Episode) {
            episode = (Episode) objArrList.get(0)[0];

            for (int i = 0; i < objArrList.size(); i++) {
                if (objArrList.get(i)[1] instanceof WebtoonFile)
                    episode.getWebtoonFileList().add((WebtoonFile) objArrList.get(i)[1]);
                // log.info(objArrList.get(i)[1]+"");
            }
        }
        return episode;
    }

    // 관심웹툰 등록
    @PostMapping(value = "/interest", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Interested interest) {

        Member member = new Member();
        member.setId("user");
        interest.setMember(member);

        log.info(interest.getWebtoon() + "");

        memberService.interestRegister(interest);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // 사용자의 관심 웹툰 목록을 가져온다
    @GetMapping(value = "/getInterestedList/{interestPage}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Interested> getInterestedList(
        @PathVariable("interestPage") Integer interestPage
    ){
        log.info("getInterestedList.........................s");
        List<Object[]> objArrList = webtoonService.getInterestedWebtoon("user", interestPage);
        List<Interested> interestList = new ArrayList<>();

        for (int i = 0; i < objArrList.size(); i++) {
            Interested interested = null;
            if (objArrList.get(i)[0] instanceof Interested){
                interested = (Interested) objArrList.get(i)[0];
                interested.setWebtoon(((Webtoon)objArrList.get(i)[1]));
                interestList.add(interested);
            }
           
        }
        
        return interestList;
    }

    // 사용자의 앨범 목록을 가져온다.
    @GetMapping(value = "/getAlbumList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Scrap> getAlbumList(){
        log.info("getAlbumList.........................s");
        return episodeService.getScrapList("user");
    }

    // 사용자의 앨범 목록을 페이징해서 가져온다.
    @GetMapping(value = "/getAlbumList/{pageNum}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Scrap> getAlbumList(@PathVariable("pageNum") Integer pageNum){
        log.info("getAlbumList.........................s");
        return episodeService.getScrapList("user", pageNum);
    }

    // 앨범 등록
    @PostMapping(value = "/registAlbum", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registAlbum(@RequestBody Scrap scrap){
        
        log.info("registAlbum......................");
        Member member = new Member();
        member.setId("user");

        scrap.setMember(member);

        memberService.albumRegister(scrap);
        
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // 앨범 삭제
    @PosttMapping(value = "/deleteAlbumArr", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAlbum(@RequestBody Integer[] albumnoArr) {
        log.info("deleteAlbumArr.............");

        for(int i=0;i<albumnoArr.length;i++){
            memberService.albumDelete(albumnoArr[i]);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // 앨범 이미지 삭제
    @PostMapping(value = "/deleteAlbumFiles", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAlbumFile(@RequestBody Integer[] afnoArr) {
        log.info("deleteAlbumFiles.............");

        for(int i=0;i<afnoArr.length;i++){
            memberService.deleteAlbumFile(afnoArr[i]);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
    // 사용자의 앨범 목록을 전부 가져온다.(페이징 X)
    @GetMapping(value = "/getAlbumFiles/{albumno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlbumFile> getAlbumFiles(
        @PathVariable("albumno") Integer albumno
        ){
        log.info("getAlbumList.........................s");

        return memberService.getAlbumFiles(albumno);
        
    }

    // 관심 웹툰 삭제
    @PostMapping(value = "/deleteInterested", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteInterested(@RequestBody Integer[] inoArr) {
        log.info("deleteInterested.............");

        for(int i=0;i<inoArr.length;i++){
            memberService.deleteInterested(inoArr[i]);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
}