package jr.kings.webtoon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * Genre
 */
@Entity
@Table(name = "tbl_genre")
@Data
@ToString(exclude = "webtoon")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gno;

    private String genre;

    @Column(name = "genre_en")
    private String genreEn;
    
    @Column(name = "genre_jp")
    private String genreJp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Webtoon webtoon;
   
}