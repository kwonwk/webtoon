package jr.kings.webtoon.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

/**
 * Scrap
 */

@Entity
@Table(name = "tbl_scrap")
@Data
@ToString(exclude = {"albumFileList","member"})
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumno;

    // id, scrapPath 삭제
    private String albumName;

    @OneToMany(mappedBy = "scrap"
                ,cascade = CascadeType.ALL
                ,orphanRemoval = true)
    private List<AlbumFile> albumFileList;

    public Scrap(){
        this.albumFileList = new ArrayList<>();
    }

    public void addAlbumFile(AlbumFile albumFile){

        albumFile.setScrap(this);
        this.albumFileList.add(albumFile);

    }

    public void replaceAlbumFile(AlbumFile albumFile){

        this.albumFileList.clear();
        albumFile.setScrap(this);
        this.albumFileList.add(albumFile);

    }
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}