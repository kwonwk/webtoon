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
 * Interested
 */

@Entity
@Table(name = "tbl_interested")
@Data
@ToString(exclude = {"webtoon","member"})
public class Interested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ino;
    
    // wno,id 삭제
    private Integer deleted;
    
    @CreationTimestamp
    private LocalDateTime regdate;
    
    @ManyToOne
    private Webtoon webtoon;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}