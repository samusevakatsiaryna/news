package web.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Session implements Serializable {
    private Long id;
    private Long userId; // Теперь храним user_id как примитивное поле, а не объект User
    private String sessionToken;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    // Default constructor
    public Session() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor with params
    public Session(Long userId, String sessionToken, LocalDateTime expiresAt) {
        this.userId = userId;
        this.sessionToken = sessionToken;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
    }

    // Full constructor
    public Session(Long id, Long userId, String sessionToken, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.userId = userId;
        this.sessionToken = sessionToken;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    // Getters Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
