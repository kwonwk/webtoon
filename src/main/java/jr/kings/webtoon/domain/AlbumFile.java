package jr.kings.webtoon.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
 * Album
 */

@Entity
@Table(name = "tbl_album")
@Data
@ToString(exclude = "scrap")
public class AlbumFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer afno;

    // scrapno 삭제

    private String albumFilePath;
    private String albumContent;

    @CreationTimestamp
    private LocalDateTime regdate;

    @CreationTimestamp
    private LocalDateTime updatedate;

    @ManyToOne(cascade={CascadeType.ALL})
    @JsonIgnore
    private Scrap scrap;

}