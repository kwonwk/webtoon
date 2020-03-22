package jr.kings.webtoon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jr.kings.webtoon.domain.Episode;
import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Webtoon;
import jr.kings.webtoon.dto.MemberDTO;
import jr.kings.webtoon.repositories.BookmarkRepository;
import jr.kings.webtoon.repositories.MemberRepository;
import jr.kings.webtoon.repositories.RecentWebtoonRepository;
import jr.kings.webtoon.service.WebtoonServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/noLangToon")
@Slf4j
public class BasisController {

    @Autowired
    private WebtoonServiceImpl webtoonServiceImpl;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RecentWebtoonRepository recentWebtoonRepository;



    @GetMapping("/main")
    public void main(Model model) {

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> todayResult = webtoonServiceImpl.getTodayWebtoon("MONDAY", page);

        List<Object[]> newResult = webtoonServiceImpl.getNewWebtoon(page);

        List<Object[]> popularResult = webtoonServiceImpl.getPopularWebtoon(page);

        model.addAttribute("todayResult", todayResult);

        model.addAttribute("newResult", newResult);

        model.addAttribute("popularResult", popularResult);

    }


    @GetMapping("/myPage")
    public void myPage(Model model) {
        
    }

    @GetMapping("/webtoonList")
    public void webtoonList(Model model) {

        log.info("webtoonList.......................");
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "wno");

        List<Object[]> webtoonListResult = webtoonServiceImpl.getWebtoonList(1, page);
        List<Episode> episode = new ArrayList<>();

        webtoonListResult.forEach(object -> {
            for (int i = 0; i < object.length; i++) {
                if (object[i] instanceof Webtoon) {
                    model.addAttribute("webtoon", object[i]);
                } else if (object[i] instanceof Episode) {
                    episode.add((Episode) object[i]);
                }
            }
            model.addAttribute("interest", object[3]);
        });

        model.addAttribute("episodeList", episode);
    }

    @GetMapping("/weeklyList")
    public void weeklyList() {
    }

    @GetMapping("/pageList")
    public void list(Model Model) {

    }

    @GetMapping("/deleteInterested")
    public String deleteInterested(Integer[] ino) {

        log.info("deleteInterested......");

        for (Integer inoList : ino) {

            log.info("inoList: " + inoList);

            webtoonServiceImpl.deleteInterestedWebtoon(ino);

        }

        return "redirect:/noLangToon/myPage";

    }

    @GetMapping("/pageMain")
    public void pageMain(Model model) {

        // LocalDate days = LocalDate.now();

        // String updateDays = days.getDayOfWeek().toString();

        // List<Object[]> WEGresult =
        // webtoonRepository.getWebtoonEpisodeGenreByTodayList(updateDays);

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> WEGresult = webtoonServiceImpl.getTodayWebtoon("MONDAY", page);

        model.addAttribute("WEGresult", WEGresult);

    }

    @GetMapping(value = "/main/{searchText}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<List<Object>> search(@PathVariable("searchText") String searchText, Model model) {

        log.info("search..............");

        String[] search = searchText.split(".json");

        log.info("search :   " + search[0]);

        return new ResponseEntity<>(webtoonServiceImpl.getSearch(search[0]), HttpStatus.OK);
    }

    // 북마크 리스트 가져오기
    @GetMapping(value = "/bookmark", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<List<Object[]>> bookmark(Model model) {

        log.info("getBookmark...............");

        return new ResponseEntity<>(bookmarkRepository.getBookMark("user1"), HttpStatus.OK);

    }

    // 북마크 삭제
    @PostMapping(value = "/bookmark", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Object[]>> bookmark(@RequestBody Integer[] bno, Model model) {

        log.info("Post........");

        log.info("" + bno);

        for (Integer bnoList : bno) {

            log.info("Post........" + bnoList);
            bookmarkRepository.deleteById(bnoList);
        }

        List<Object[]> list = bookmarkRepository.getBookMark("user1");

        // model.addAttribute("list", list);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    // 최근 본 웹툰 삭제
    @PostMapping(value = "/deleteRecentWebtoon", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Object[]>> recentWebtoonDelete(@RequestBody Integer[] recentnoArr, Model model) {

        log.info("Post........");

        for (Integer recentno : recentnoArr) {

            log.info("Post........" + recentno);
            recentWebtoonRepository.deleteById(recentno);
        }

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> list = recentWebtoonRepository.getRecentWebtoonEpisodeWebtoon("user1", page);

        // model.addAttribute("list", list);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping(value = "/recentWebtoon", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<List<Object[]>> recentWebtoon(Model model) {

        Pageable page = PageRequest.of(0, 10);

        log.info("getrecentWebtoon...............");

        // log.info(""+recentWebtoonRepository.getRecentWebtoonEpisodeWebtoon("user1",
        // page));
        List<Object[]> list = recentWebtoonRepository.getRecentWebtoonEpisodeWebtoon("user1", page);
        list.forEach(objArr -> {
            for (int i = 0; i < objArr.length; i++) {
                log.info(objArr[i] + ", " + i);
            }
        });

        return new ResponseEntity<>(recentWebtoonRepository.getRecentWebtoonEpisodeWebtoon("user1", page),
                HttpStatus.OK);
    }

    @GetMapping("/member")
    public void member() {
        log.info("..........getmember");

    }

    @PostMapping("/membership")
    public String membership(MemberDTO dto, Model model) {

        Member member = new Member();

        log.info("membership...........");

        System.out.println("membership...........");

        log.info("dto.getEmail() : " + dto.getEmail());
        log.info("dto.getEmail() : " + dto.getUserId());
        log.info("dto.getEmail() : " + dto.getPhone());
        log.info("dto.getEmail() : " + dto.getUserPw());

        member.setId(dto.getUserId());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setPw(dto.getUserPw());

        memberRepository.save(member);

        // memberRepository.joinMemberShip(dto);

        return "redirect:/noLangToon/main";
    }

}
