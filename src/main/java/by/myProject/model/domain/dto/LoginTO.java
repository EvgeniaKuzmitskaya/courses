package by.myProject.model.domain.dto;

import javax.validation.constraints.NotNull;

public class LoginTO {
    private String login;
    private String password;

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
