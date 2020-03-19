package jr.kings.webtoon.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

/**
 * Board
 */

@Entity
@Table(name = "tbl_board")
@Data
@ToString(exclude = {"member","replyList","attachList"})
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer boardno;
    
    @Column(length = 100)
    public String title;

    @Column(length = 1000)
    public String content;
    
    @CreationTimestamp
    public LocalDateTime regdate;
    
    @CreationTimestamp
    public LocalDateTime updatedate;

    @ManyToOne(fetch = FetchType.LAZY)
    public Member member;

    @OneToMany(mappedBy = "board")
    public List<Reply> replyList;

    @OneToMany(mappedBy = "board"
                ,cascade = CascadeType.ALL
                ,orphanRemoval = true)
    public List<Attach> attachList;

    public Board(){
        this.attachList = new ArrayList<>();
    }

    public void addAttach(Attach attach){

        attach.setBoard(this);
        this.attachList.add(attach);

    }

    public void replaceAttach(Attach attach){

        this.attachList.clear();
        attach.setBoard(this);
        this.attachList.add(attach);

    }
    
}