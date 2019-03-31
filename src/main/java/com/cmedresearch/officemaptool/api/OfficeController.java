package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.Office;
import com.cmedresearch.officemaptool.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  private static void addLinks(Office office) {
    office.removeLinks();
    office.add(
        linkTo(methodOn(OfficeController.class).getOffice(office.getOfficeId())).withSelfRel(),
        linkTo(methodOn(OfficeController.class).editOffice(office.getOfficeId(), null)).withRel("update"),
        linkTo(methodOn(OfficeController.class).deleteOffice(office.getOfficeId())).withRel("delete"),
        linkTo(methodOn(SeatController.class).getSeats(office.getOfficeId())).withRel("seats"),
        linkTo(methodOn(SeatController.class).createSeat(office.getOfficeId(), null)).withRel("createSeat"),
        linkTo(methodOn(ConferenceRoomController.class).getConferenceRooms(office.getOfficeId())).withRel("rooms"),
        linkTo(methodOn(ConferenceRoomController.class).createConferenceRoom(office.getOfficeId(), null)).withRel("createRoom")
    );
  }

  @GetMapping("/office/{officeId}")
  public ResponseEntity getOffice(@PathVariable Long officeId) {
    Office office = officeService.getOfficeById(officeId);
    addLinks(office);
    return new ResponseEntity<>(office, HttpStatus.OK);
  }

  @GetMapping("/offices")
  public ResponseEntity getOffices() {
    List<Office> offices = officeService.getAllOffices();
    for (Office office : offices) {
      addLinks(office);
    }
    return new ResponseEntity<>(offices, HttpStatus.OK);
  }

  @PostMapping("/offices")
  public ResponseEntity createOffice(@RequestBody Office office) {
    office = officeService.createOffice(office);
    addLinks(office);
    return new ResponseEntity<>(office, HttpStatus.OK);
  }

  @PutMapping("/office/{officeId}")
  public ResponseEntity editOffice(@PathVariable Long officeId, @RequestBody Office office) {
    office = officeService.editOffice(officeId, office);
    addLinks(office);
    return new ResponseEntity<>(office, HttpStatus.OK);
  }

  @DeleteMapping("/office/{officeId}")
  public ResponseEntity deleteOffice(@PathVariable Long officeId) {
    officeService.deleteOffice(officeId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

