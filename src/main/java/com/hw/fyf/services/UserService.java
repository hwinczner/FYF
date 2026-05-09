package com.hw.fyf.services;

import com.hw.fyf.exceptions.BadRequestException;
import com.hw.fyf.exceptions.ConflictException;
import com.hw.fyf.exceptions.MissingException;
import com.hw.fyf.models.User;
import com.hw.fyf.repo.UserRepository;
import com.hw.fyf.utils.HelperMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HelperMethods helperMethods;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void validateUser(User user) throws ConflictException {

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ConflictException("Email already exists in database");
        }else if(!(user.getEmail().matches(emailRegex))){
            throw new BadRequestException("Not Valid Email format");
        }else if(ObjectUtils.isEmpty(user)){
            throw new MissingException("Body is empty");
        }
        userRepository.save(user);
    }

    public User findById(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new MissingException("User does not exist: " + userId));

        return user;
    }
}
