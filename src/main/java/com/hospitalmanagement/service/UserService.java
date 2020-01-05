package com.hospitalmanagement.service;


import com.hospitalmanagement.exception.NotFoundException;
import com.hospitalmanagement.model.User;
import com.hospitalmanagement.persistence.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private User fetchUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        return user.get();
    }

    public User getUser(Long id) {
        return fetchUser(id);
    }

    public List<User> getUsers() {
        return IteratorUtils.toList(userRepository.findAll().iterator());
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(Long userId, User user) {
        User foundUser = fetchUser(userId);
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setJob(user.getJob());
        return foundUser;

    }
    public Long getIdByUsername(String username)
    {
        Optional<User> userOptional=userRepository.findByUsername(username);
        if(userOptional.isEmpty())
        {
            throw new NotFoundException();
        }
        User foundUser=userOptional.get();
        return foundUser.getUserId();
    }
}
