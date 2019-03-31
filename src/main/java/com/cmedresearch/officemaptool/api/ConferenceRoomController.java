package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.ConferenceRoom;
import com.cmedresearch.officemaptool.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ConferenceRoomController {
  private ConferenceRoomService conferenceRoomService;

  @Autowired
  public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
    this.conferenceRoomService = conferenceRoomService;
  }

  private static void addLinks(Long officeId, ConferenceRoom room) {
    room.removeLinks();
    room.add(
        linkTo(methodOn(ConferenceRoomController.class).getConferenceRoom(officeId, room.getConferenceRoomId())).withSelfRel(),
        linkTo(methodOn(ConferenceRoomController.class).editConferenceRoom(officeId, room.getConferenceRoomId(), null)).withRel("update"),
        linkTo(methodOn(ConferenceRoomController.class).deleteConferenceRoom(officeId, room.getConferenceRoomId())).withRel("delete")
    );
  }

  @GetMapping("/office/{officeId}/room/{conferenceRoomId}")
  public ResponseEntity getConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId) {
    ConferenceRoom room = conferenceRoomService.getConferenceRoomById(officeId, conferenceRoomId);
    addLinks(officeId, room);
    return new ResponseEntity<>(room, HttpStatus.OK);
  }

  @GetMapping("/office/{officeId}/rooms")
  public ResponseEntity getConferenceRooms(@PathVariable Long officeId) {
    List<ConferenceRoom> rooms = conferenceRoomService.getAllConferenceRoomsInOffice(officeId);
    for (ConferenceRoom room : rooms) {
      addLinks(officeId, room);
    }
    return new ResponseEntity<>(rooms, HttpStatus.OK);
  }

  @PostMapping("/office/{officeId}/rooms")
  public ResponseEntity createConferenceRoom(@PathVariable Long officeId, @RequestBody ConferenceRoom conferenceRoom) {
    conferenceRoom = conferenceRoomService.createConferenceRoom(officeId, conferenceRoom);
    addLinks(officeId, conferenceRoom);
    return new ResponseEntity<>(conferenceRoom, HttpStatus.OK);
  }

  @PutMapping("/office/{officeId}/room/{conferenceRoomId}")
  public ResponseEntity editConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId, @RequestBody ConferenceRoom conferenceRoom) {
    conferenceRoom = conferenceRoomService.editConferenceRoom(officeId, conferenceRoomId, conferenceRoom);
    addLinks(conferenceRoomId, conferenceRoom);
    return new ResponseEntity<>(conferenceRoom, HttpStatus.OK);
  }

  @DeleteMapping("/office/{officeId}/room/{conferenceRoomId}")
  public ResponseEntity deleteConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId) {
    conferenceRoomService.deleteConferenceRoom(officeId, conferenceRoomId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
