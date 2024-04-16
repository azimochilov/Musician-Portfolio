package Musician.Portfolio.Musician.Portfolio.api.user;


import Musician.Portfolio.Musician.Portfolio.domain.dtos.login.LoginResponseDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.login.LoginUserDto;
import Musician.Portfolio.Musician.Portfolio.service.secure.LoginManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    private final LoginManagerService loginManagerService;


    @PostMapping
    public LoginResponseDto login(@RequestBody LoginUserDto loginUserDto) {
        log.info("Login user : username {}", loginUserDto.getUsername());
        return loginManagerService.attemptLogin(loginUserDto.getUsername(), loginUserDto.getPassword());
    }
}
