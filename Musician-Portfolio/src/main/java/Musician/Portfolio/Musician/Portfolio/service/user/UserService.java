package Musician.Portfolio.Musician.Portfolio.service.user;

import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserResultDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.users.UserUpdateDto;
import Musician.Portfolio.Musician.Portfolio.domain.entities.user.Role;
import Musician.Portfolio.Musician.Portfolio.domain.entities.user.User;
import Musician.Portfolio.Musician.Portfolio.exception.NotFoundException;
import Musician.Portfolio.Musician.Portfolio.repository.role.RoleRepository;
import Musician.Portfolio.Musician.Portfolio.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserResultDto create(UserCreationDto userDto) {

        Role role = roleRepository.findByName("USER").orElseThrow(
                () ->   new NotFoundException("Not fit any role at all")
        );

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));


        User user = modelMapper.map(userDto, User.class);
        user.setRole(role);
        userRepository.save(user);


        UserResultDto userResultDto = modelMapper.map(user , UserResultDto.class);
        userResultDto.setRole(role.getName());


        return userResultDto;
    }

    @Override
    public List<UserResultDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResultDto> userResultDTOs = users.stream()
                .map(user -> modelMapper.map(user, UserResultDto.class))
                .collect(Collectors.toList());
        return userResultDTOs;
    }

    @Override
    public UserResultDto getUserByUsername(String username) {

        User user = userRepository.getUserByUsername(username).orElseThrow(
                () -> new NotFoundException("user not found with given username")
        );

        return modelMapper.map(user, UserResultDto.class);
    }

    @Override
    public UserResultDto getById(Long id) {

        User user = userRepository.getUserByUserId(id).orElseThrow(
                () -> new NotFoundException("user not found with given id")
        );

        return modelMapper.map(user, UserResultDto.class);
    }

    @Override
    public UserResultDto update(Long id, UserUpdateDto userDto) {
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        User user = userRepository.getUserByUserId(id).orElseThrow(
                () -> new NotFoundException("User not found with this id! ")
        );
        modelMapper.map(userDto,user);

        Role role = roleRepository.findByName(userDto.getRole()).orElseThrow(
                () -> new NotFoundException("Not found given role")
        );

        user.setRole(role);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);

        return modelMapper.map(user,UserResultDto.class);
    }



    @Override
    public void delete(Long id) {
        if (userRepository.findById(id).isPresent()) {

            userRepository.deleteById(id);

        } else {

            throw new NotFoundException("User with this id not found!");

        }
    }

}
