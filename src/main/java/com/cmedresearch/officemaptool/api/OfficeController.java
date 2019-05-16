package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.Office;
import com.cmedresearch.officemaptool.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OfficeController {
  private OfficeService officeService;

  @Autowired
  public OfficeController(OfficeService officeService) {
    this.officeService = officeService;
  }

  private static void addLinks(Office office, Authentication authentication) {
    office.removeLinks();
    office.add(
        linkTo(methodOn(OfficeController.class).getOffice(office.getOfficeId(), authentication)).withSelfRel(),
        linkTo(methodOn(SeatController.class).getSeats(office.getOfficeId(), authentication)).withRel("seats"),
        linkTo(methodOn(ConferenceRoomController.class).getConferenceRooms(office.getOfficeId(), authentication)).withRel("rooms")
    );
    if (authentication != null) {
      office.add(
          linkTo(methodOn(OfficeController.class).editOffice(office.getOfficeId(), null, authentication)).withRel("update"),
          linkTo(methodOn(OfficeController.class).deleteOffice(office.getOfficeId())).withRel("delete"),
          linkTo(methodOn(SeatController.class).createSeat(office.getOfficeId(), null, authentication)).withRel("createSeat"),
          linkTo(methodOn(ConferenceRoomController.class).createConferenceRoom(office.getOfficeId(), null, authentication)).withRel("createRoom")
      );
    }
  }

  @GetMapping("/office/{officeId}")
  public ResponseEntity getOffice(@PathVariable Long officeId, Authentication authentication) {
    Office office = officeService.getOfficeById(officeId);
    addLinks(office, authentication);
    return new ResponseEntity<>(office, HttpStatus.OK);
  }

  @GetMapping("/offices")
  public ResponseEntity getOffices(Authentication authentication) {
    List<Office> offices = officeService.getAllOffices();
    for (Office office : offices) {
      addLinks(office, authentication);
    }
    return new ResponseEntity<>(offices, HttpStatus.OK);
  }

  @GetMapping("/officeByEmployeeId/{employeeId}")
  public ResponseEntity getOfficeByEmployeeId(@PathVariable Long employeeId, Authentication authentication) {
    Office office = officeService.getOfficeByEmployeeId(employeeId);
    addLinks(office, authentication);
    return new ResponseEntity<>(office, HttpStatus.OK);
  }

  @PostMapping("/offices")
  public ResponseEntity createOffice(@RequestBody Office office, Authentication authentication) {
    office = officeService.createOffice(office);
    addLinks(office, authentication);
    return new ResponseEntity<>(office, HttpStatus.OK);
  }

  @PutMapping("/office/{officeId}")
  public ResponseEntity editOffice(@PathVariable Long officeId, @RequestBody Office office, Authentication authentication) {
    office = officeService.editOffice(officeId, office);
    addLinks(office, authentication);
    return new ResponseEntity<>(office, HttpStatus.OK);
  }

  @DeleteMapping("/office/{officeId}")
  public ResponseEntity deleteOffice(@PathVariable Long officeId) {
    officeService.deleteOffice(officeId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

