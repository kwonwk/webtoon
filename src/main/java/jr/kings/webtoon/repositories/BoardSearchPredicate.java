package jr.kings.webtoon.repositories;

import com.querydsl.core.types.Predicate;

import jr.kings.webtoon.dto.SearchDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * PredicateSearchPrdicate
 */

@Slf4j
public class BoardSearchPredicate {

    public static Predicate searchSimple(SearchDTO dto){

        log.info(""+dto);

        // QBoard board = QBoard.board;

        // BooleanBuilder booleanBuilder = new BooleanBuilder();

        // if(dto.getType() == null){

        //     return booleanBuilder;

        // }

        // String type = dto.getType();
        // String keyword = dto.getKeyword();

        // if(type.equals("t")){
        //     booleanBuilder.and(board.title.contains(keyword));
        // } else if(type.equals("c")){
        //     booleanBuilder.and(board.content.contains(keyword));
        // }

        // return booleanBuilder;

        return null;
    }

}