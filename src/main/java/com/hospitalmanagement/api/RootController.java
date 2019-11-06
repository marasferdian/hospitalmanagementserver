package com.hospitalmanagement.api;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RootController {

    @GetMapping("")
    public ResponseEntity getRootObject(Authentication authentication) {

        ResourceSupport res = new ResourceSupport();
        res.add(linkTo(methodOn(UserController.class).getUsers(authentication)).withRel("users"));

        if (authentication != null) {
            res.add(linkTo(methodOn(UserController.class).createUser(null, authentication)).withRel("createUser"));
        }


        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
