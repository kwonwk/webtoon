package jr.kings.webtoon.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

/**
 * Bookmark
 */

@Entity
@Table(name = "tbl_bookmark")
@Data
@ToString(exclude = {"episode","member"})
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;
    
    // eno, id 삭제
    
    @CreationTimestamp
    private LocalDateTime regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Episode episode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    
}