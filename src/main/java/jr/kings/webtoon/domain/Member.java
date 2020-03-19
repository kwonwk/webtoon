package jr.kings.webtoon.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Member
 */

@Entity
@Table(name = "tbl_member")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"interestedList","bookmarkList","recentWebtoonList","replyList","scoreList","alarmList","scrapList", "boardList"})
public class Member {

    @Id
    private String id;
    private String pw;
    private String phone;
    private String email;
    private String gender;
    private Date age;
    
    @CreationTimestamp
    private LocalDateTime regdate;

    // @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Authorities> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Interested> interestedList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarkList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<RecentWebtoon> recentWebtoonList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Reply> replyList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Score> scoreList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Alarm> alarmList;
    
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Scrap> scrapList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Board> boardList;


}