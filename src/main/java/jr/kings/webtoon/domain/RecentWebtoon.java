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
 * RecentWebtoon
 */

@Entity
@Table(name = "tbl_recent_webtoon")
@Data
@ToString(exclude = {"episode","member"})
public class RecentWebtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recentno;

    // eno, id 삭제

    @CreationTimestamp
    private LocalDateTime regdate;
    
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Episode episode;

  
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;
    
}