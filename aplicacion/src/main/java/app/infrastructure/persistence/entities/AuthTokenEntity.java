
package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auth_tokens")
public class AuthTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true, length = 100)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;

    public AuthTokenEntity() {}

    public AuthTokenEntity(String userName, String token, Date createdAt, Date expiresAt) {
        this.userName = userName;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    // --- Getters / Setters ---
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getToken() { return token; }
    public Date getCreatedAt() { return createdAt; }
    public Date getExpiresAt() { return expiresAt; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setToken(String token) { this.token = token; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public void setExpiresAt(Date expiresAt) { this.expiresAt = expiresAt; }
}
