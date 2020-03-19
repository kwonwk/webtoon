package jr.kings.webtoon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

/**
 * File
 */
@Entity
@Table(name = "tbl_webtoon_file")
@Data
@ToString(exclude = "episode")
public class WebtoonFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fno;

    //merged 파일 경로 ???
    @Column(length=1000)
    private String image;

    //ocr한 데이터 경로
    @Column(length=1000)
    private String ocrjson;

    @ManyToOne
    @JsonIgnore
    private Episode episode;

}   