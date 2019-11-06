package com.hospitalmanagement.api;

import com.hospitalmanagement.exception.NotAllowedException;
import com.hospitalmanagement.exception.NotFoundException;
import com.hospitalmanagement.model.User;
import com.hospitalmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public static void addLinks(User user, Authentication authentication) {


        user.removeLinks();
        user.add(linkTo(methodOn(UserController.class).getUser(user.getUserId(), authentication)).withSelfRel());
        //TODO: add link to appointment controller
        if (hasAuthority(authentication, "ADMIN")) {
            user.add(linkTo(methodOn(UserController.class).editUser(user.getUserId(), user, authentication)).withRel("edit"));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId, Authentication authentication) {
        User user = userService.getUser(userId);
        addLinks(user, authentication);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity getUsers(Authentication authentication) {
        List<User> userList = userService.getUsers();
        for (User user : userList) {
            addLinks(user, authentication);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    private static List<String> getAuthorityList(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private static boolean hasAuthority(Authentication authentication, String authorityName) {
        return getAuthorityList(authentication).contains(authorityName);
    }

    @PostMapping("/user?type={pacient}") //TODO: figure this out
    public ResponseEntity createUser(@RequestBody User user, Authentication authentication) {
        User createdUser;
        if (hasAuthority(authentication, "ADMIN")) {
            if (user.getJob().equals("MEDIC") || user.getJob().equals("SECRETAR"))
                createdUser = userService.createUser(user);
            else
                throw new NotAllowedException();
        } else if (hasAuthority(authentication, "PACIENT")) {
            if (user.getJob().equals("PACIENT"))
                createdUser = userService.createUser(user);
            else
                throw new NotAllowedException();
        } else
            throw new NotAllowedException();

        addLinks(createdUser, authentication);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity editUser(@PathVariable Long userId, @RequestBody User user, Authentication authentication) {
        if (hasAuthority(authentication, "ADMIN")) {
            user = userService.editUser(userId, user);
            addLinks(user, authentication);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else
            throw new NotAllowedException();


    }
}
