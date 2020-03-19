package jr.kings.webtoon.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tbl_webtoon")
@Data
@ToString(exclude = {"genreList","interestedList","episodeList"})
public class Webtoon {

    @Id
    private Integer wno;

    private String url;

    //웹툰제목
    private String title;
    private String titleEn;
    private String titleJp;
    
    //작가명
    private String author;
    private String authorEn;
    private String authorJp;

    // 웹툰 소개
    private String introduce;
    private String introduceEn;
    private String introduceJp;

    //업데이트 요일
    private String updateDays;

    @Column(length = 1000)
    private String thumbnailPath;

    @Column(length=1000)
    private String bannerPath;


    @CreationTimestamp
    @Column(name = "regdate", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date regdate;

    @UpdateTimestamp
    @Column(name = "updatedate", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedate;

    @OneToMany(mappedBy = "webtoon")
    @JsonIgnore
    private List<Genre> genreList;

    public Genre makeGenre(){
        
        Genre genre = new Genre();
        genre.setWebtoon(this);
        return genre;
        
    }
    
    @OneToMany(mappedBy = "webtoon")
    @JsonIgnore
    private List<Episode> episodeList;
    
    public Episode makeEpisode(){
        
        Episode episode = new Episode();
        episode.setWebtoon(this);
        return episode;
        
    }
    
    @OneToMany(mappedBy = "webtoon")
    @JsonIgnore
    private List<Interested> interestedList;
    
}