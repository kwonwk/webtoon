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
 * Alarm
 */

@Entity
@Table(name = "tbl_alarm")
@Data
@ToString(exclude = "member")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer ano;
    
    // id 삭제

    private String msg;
    
    @CreationTimestamp
    private LocalDateTime regdate;
    
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}