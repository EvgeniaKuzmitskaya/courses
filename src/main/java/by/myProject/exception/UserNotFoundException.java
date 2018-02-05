package by.myProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class UserNotFoundException extends RuntimeException {

    private Long id;

    public UserNotFoundException(long id) {
        super("User not found with id " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }



}
