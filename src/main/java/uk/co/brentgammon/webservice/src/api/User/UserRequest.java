package uk.co.brentgammon.webservice.src.api.User;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UserRequest {

    @NotNull
    String name;

    @NotNull
    String email;
}
