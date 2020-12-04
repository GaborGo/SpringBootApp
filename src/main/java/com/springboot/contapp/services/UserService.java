package com.springboot.contapp.services;

import com.springboot.contapp.exceptions.UserNotFoundException;
import com.springboot.contapp.models.User;
import com.springboot.contapp.repositories.UserRepository;
import com.springboot.contapp.services.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convertToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new UserNotFoundException("User with id: " + id + " not found!");
        return user;
    }

    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


}
