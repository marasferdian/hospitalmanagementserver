package com.hospitalmanagement.api;

import com.hospitalmanagement.model.Appointment;
import com.hospitalmanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AppointmentController {
    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    private static List<String> getAuthorityList(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private static boolean hasAuthority(Authentication authentication, String authorityName) {
        return getAuthorityList(authentication).contains(authorityName);
    }

    public static void addLinks(Appointment appointment, Authentication authentication) {
        appointment.removeLinks();
        //TODO: add links after implementing methods
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity getAppointment(@PathVariable Long appointmentId, Authentication authentication)
    {
        Appointment appointment=appointmentService.getAppointment(appointmentId);
        addLinks(appointment,authentication);
        return new ResponseEntity<>(appointment,HttpStatus.OK);
    }

    @GetMapping("/appointments")
    public ResponseEntity getAppointments(Authentication authentication)
    {
        List<Appointment> appointments=appointmentService.getAppointments();
        for(Appointment appointment:appointments)
            addLinks(appointment,authentication);
        return new ResponseEntity<>(appointments,HttpStatus.OK);
    }


}
