package jr.kings.webtoon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * ScoreRate
 */
@Entity
@Table(name = "tbl_score")
@Data
@ToString(exclude = {"episode","member"})
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;

    // eno, id 삭제

    //평점
    private Integer grade;

    @ManyToOne
    private Episode episode;

    @ManyToOne
    private Member member;

}