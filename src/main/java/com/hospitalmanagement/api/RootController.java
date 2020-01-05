package com.hospitalmanagement.api;

import com.hospitalmanagement.model.Appointment;
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
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RootController {

    @GetMapping("")
    public ResponseEntity getRootObject(Authentication authentication) {


        ResourceSupport res = new ResourceSupport();
        res.add(linkTo(methodOn(UserController.class).getUsers(authentication)).withRel("users"));
        res.add(linkTo(methodOn(UserController.class).createUser(null, authentication)).withRel("createUser"));
        res.add(linkTo(methodOn(AppointmentController.class).createAppointment(null,authentication)).withRel("createAppointment"));


        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    private static List<String> getAuthorityList(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private static boolean hasAuthority(Authentication authentication, String authorityName) {
        return getAuthorityList(authentication).contains(authorityName);
    }

}
