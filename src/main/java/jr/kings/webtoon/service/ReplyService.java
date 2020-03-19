package jr.kings.webtoon.service;

import jr.kings.webtoon.domain.Reply;


public interface ReplyService {
    
    // 웹툰 조회 페이지에서의 댓글 등록
    public void replyRegister(Reply reply);
}