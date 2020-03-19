package jr.kings.webtoon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * Attach
 */

@Entity
@Table(name = "tbl_attach")
@Data
@ToString(exclude = "board")
public class Attach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer attachno;

    public String fileName;
    public String uuid;
    public String filePath;

    // 이미지 여부 검사해서 섬네일 만들 수 있도록
    public boolean image;
    
    // 대표 섬네일 표시를 위함
    public boolean current;
    
    @ManyToOne
    public Board board;

}