package Musician.Portfolio.Musician.Portfolio.domain.dtos.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponseDto {
    private String accessToken;
}