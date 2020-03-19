package jr.kings.webtoon.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

/**
 * Reply
 */

@Entity
@Table(name = "tbl_reply")
@Data
@ToString(exclude = {"episode","member","board"})
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;

    // eno, id 삭제

    private String comment;

    @CreationTimestamp
    private LocalDateTime regdate;
   
    private Integer likes;
    private Integer dislike;

    // @JsonIgnore
    @ManyToOne
    private Episode episode;

    // @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}