package Musician.Portfolio.Musician.Portfolio.service.user;


import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserResultDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserUpdateDto;

import java.util.List;

public interface IUserService {
    UserResultDto create(UserCreationDto userDto);
    List<UserResultDto> getAll();
    UserResultDto getUserByUsername(String username);
    UserResultDto getById(Long id);
    UserResultDto update(Long id, UserUpdateDto userDto);

    void delete(Long id);
}
