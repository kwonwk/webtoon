package jr.kings.webtoon.domain;

import java.sql.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.ToString;

/**
 * Genre
 */
@Entity
@Table(name = "tbl_episode")
@Data
@ToString(exclude = {"webtoonFileList","bookmarkList","recentWebtoonList","replyList","scoreList","webtoon"})
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eno; // PK

    //부제목 ex : 1화
    private String subtitle;
    private String subtitleEn;
    private String subtitleJp;

    //웹툰별(크롤링시) ???
    private Integer datano;
    
    //화별 썸네일
    @Column(length=1000)
    private String thumbnailPath;

    //조회수
    private Integer pageView;

    //평점
    private Double grade;

    //업로드 일자
    @CreationTimestamp
    @Column(name = "regdate", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date regdate;

    //수정 일자
    @UpdateTimestamp
    @Column(name = "updatedate", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedate;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL, orphanRemoval = true )
    // @JsonIgnore
    private List<WebtoonFile> webtoonFileList;

    public Episode(){
        this.webtoonFileList = new ArrayList<>();
    }

    public void addWebtoonFile(WebtoonFile webtoonFile){

        webtoonFile.setEpisode(this);
        this.webtoonFileList.add(webtoonFile);

    }

    @OneToMany(mappedBy = "episode")
    @JsonIgnore
    private List<Bookmark> bookmarkList;
    
    @OneToMany(mappedBy = "episode")
    @JsonIgnore
    private List<RecentWebtoon> recentWebtoonList;

    @OneToMany(mappedBy = "episode")
    @JsonIgnore
    private List<Reply> replyList;
    
    @OneToMany(mappedBy = "episode")
    @JsonIgnore
    private List<Score> scoreList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Webtoon webtoon;

}