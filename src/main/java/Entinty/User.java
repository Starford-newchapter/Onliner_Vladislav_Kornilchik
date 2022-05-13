package Entinty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(setterPrefix = "with", buildMethodName = "build")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    private String login;
    private String password;


    public static class UserBuilder {
        public UserBuilder() {
        }
    }
}

