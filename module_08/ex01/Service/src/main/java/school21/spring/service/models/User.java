package school21.spring.service.models;

import java.util.Objects;

public class User {


    private Long userId;
    private String userEmail;

    public User(Long userId, String email){

        this.userId = userId;
        this.userEmail = email;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    @Override
    public String toString() {
        return String.format("User: %s = %s, %s = %s",
                "userId", this.userId,
                "email", this.userEmail);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        User user = (User) obj;

        return this.userId == user.userId
                && this.userId.equals(user.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.userId, this.userEmail);

    }
}
