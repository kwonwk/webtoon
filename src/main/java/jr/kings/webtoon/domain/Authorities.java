package jr.kings.webtoon.domain;

import java.sql.Date;

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
 * Authorities
 */
@Entity
@Table(name = "tbl_authorities")
@Data
@ToString(exclude = {"member"})
public class Authorities{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorno;

    private String authority;

    @CreationTimestamp
    private Date regdate;

     // @OneToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
     @ManyToOne
     @JsonIgnore
     private Member member;

}