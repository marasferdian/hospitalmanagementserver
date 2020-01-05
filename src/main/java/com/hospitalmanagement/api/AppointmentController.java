package com.hospitalmanagement.api;

import com.hospitalmanagement.exception.NotAllowedException;
import com.hospitalmanagement.model.Appointment;
import com.hospitalmanagement.model.User;
import com.hospitalmanagement.service.AppointmentService;
import com.hospitalmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AppointmentController {
    private AppointmentService appointmentService;
    private UserService userService;

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
        appointment.add(linkTo(methodOn(AppointmentController.class).getAppointment(appointment.getAppointmentId(),authentication)).withSelfRel());
        if(hasAuthority(authentication,"SECRETAR")) {

            appointment.add(linkTo(methodOn(AppointmentController.class).createAppointment(appointment,authentication)).withRel("create"));
            appointment.add(linkTo(methodOn(AppointmentController.class).editAppointment(appointment.getAppointmentId(),appointment,authentication)).withRel("edit"));
        }

    }

    @GetMapping("/appointments/{appointmentId}")
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

    @PostMapping("/appointment")
    public ResponseEntity createAppointment(@RequestBody Appointment appointment, Authentication authentication)
    {
        Appointment createdAppointment;
        createdAppointment=appointmentService.createAppointment(appointment.getPacientId(),appointment.getMedicId(),appointment.getDate());
        addLinks(createdAppointment,authentication);
        return new ResponseEntity<>(authentication,HttpStatus.OK);
    }

    @PutMapping("/appointments/{appointmentId}")
    public ResponseEntity editAppointment(@PathVariable Long appointmentId,  @RequestBody Appointment appointment,Authentication authentication)
    {
        if(hasAuthority(authentication,"SECRETAR") || hasAuthority(authentication,"ADMIN"))
        {
            appointment=appointmentService.editAppointment(appointmentId,appointment);
            addLinks(appointment,authentication);
            return new ResponseEntity<>(appointment,HttpStatus.OK);
        }
        else
            throw new NotAllowedException();
    }
    @GetMapping("/appointments/my-appointment")
    public ResponseEntity getAppointmentByPacientId(Authentication authentication)
    {
        Appointment foundAppointment=appointmentService.findAppointmentByUsername(authentication.getName());
        addLinks(foundAppointment,authentication);
        return new ResponseEntity<>(foundAppointment,HttpStatus.OK);
    }

    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity deleteAppointment(@PathVariable Long appointmentId,Authentication authentication)
    {

            appointmentService.deleteAppointment(appointmentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
