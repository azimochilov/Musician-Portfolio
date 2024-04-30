package Musician.Portfolio.Musician.Portfolio.api.user;


import Musician.Portfolio.Musician.Portfolio.domain.dtos.login.VerifyDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserResultDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserUpdateDto;

import Musician.Portfolio.Musician.Portfolio.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin("http://54.174.103.74:8080")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserCreationDto user){
        return ResponseEntity.ok(userService.create(user));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
        UserResultDto user = userService.update(id, userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
