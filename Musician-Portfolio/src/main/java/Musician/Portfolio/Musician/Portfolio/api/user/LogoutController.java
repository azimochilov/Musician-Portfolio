package Musician.Portfolio.Musician.Portfolio.api.user;


import Musician.Portfolio.Musician.Portfolio.service.secure.LogoutManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/logout")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class LogoutController {
    private final LogoutManagerService logoutManagerService;

    @GetMapping
    public void logout() {
        logoutManagerService.logout();
    }
}
