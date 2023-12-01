package net.javaguides.sms.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PasswordResetRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @ManyToOne (targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    @Column(nullable = false)
    private LocalDateTime ExpiryDate;
    @Column(nullable = false)
    private boolean used;


    public PasswordResetRequest(String token, User user, LocalDateTime ExpiryDate, boolean used) {
        this.token = token;
        this.user = user;
        this.ExpiryDate = ExpiryDate;
        this.used = used;
    }


    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }


    public boolean isExpired() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isAfter(ExpiryDate);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
