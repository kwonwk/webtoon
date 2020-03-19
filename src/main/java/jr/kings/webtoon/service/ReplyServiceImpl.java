package jr.kings.webtoon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jr.kings.webtoon.domain.Reply;
import jr.kings.webtoon.repositories.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRegister;

    @Override
    public void replyRegister(Reply reply) {
        replyRegister.save(reply);
    }
}