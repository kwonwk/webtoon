package jr.kings.webtoon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jr.kings.webtoon.service.BoardService;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardService
 */

@Controller
@RequestMapping("/noLangToon")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public void boardList(Model model, Pageable page){
        
        log.info("boardList.......");

        List<Object[]> boardListResult = boardService.getBoardList(page);

        model.addAttribute("boardListResult", boardListResult);

    }

    @GetMapping("/boardRead")
    public void boardRead(Integer boardno,Model model){
        
        log.info("boardRead......");

        log.info(""+boardno);

        List<Object[]> boardAttachResult = boardService.readBoardAttach(boardno);

        model.addAttribute("boardAttachResult", boardAttachResult);

    }
    
}