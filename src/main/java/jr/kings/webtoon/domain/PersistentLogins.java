package jr.kings.webtoon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Table(name = "persistent_logins")
@Entity(name = "persistent_logins")
public class PersistentLogins {

    @Id
    @Column(name = "series")
    private String series;

    @Column(name = "username", unique = true)
    private String userId;

    @Column(name = "token")
    private String token;

    @CreationTimestamp
    @Column(name = "last_used", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date lastUsed;

    public PersistentLogins() {
    }

    public PersistentLogins(String userId, String series, String token) {
        this.userId = userId;
        this.series = series;
        this.token = token;
    }
}