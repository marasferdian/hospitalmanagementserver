package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.ConferenceRoom;
import com.cmedresearch.officemaptool.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

  private static void addLinks(Long officeId, ConferenceRoom room, Authentication authentication) {
    room.removeLinks();
    room.add(
        linkTo(methodOn(ConferenceRoomController.class).getConferenceRoom(officeId, room.getConferenceRoomId(), authentication)).withSelfRel()
    );
    if (authentication != null) {
      room.add(
          linkTo(methodOn(ConferenceRoomController.class).editConferenceRoom(officeId, room.getConferenceRoomId(), null, authentication)).withRel("update"),
          linkTo(methodOn(ConferenceRoomController.class).deleteConferenceRoom(officeId, room.getConferenceRoomId())).withRel("delete")
      );
    }
  }

  @GetMapping("/office/{officeId}/room/{conferenceRoomId}")
  public ResponseEntity getConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId, Authentication authentication) {
    ConferenceRoom room = conferenceRoomService.getConferenceRoomById(officeId, conferenceRoomId);
    addLinks(officeId, room, authentication);
    return new ResponseEntity<>(room, HttpStatus.OK);
  }

  @GetMapping("/office/{officeId}/rooms")
  public ResponseEntity getConferenceRooms(@PathVariable Long officeId, Authentication authentication) {
    List<ConferenceRoom> rooms = conferenceRoomService.getAllConferenceRoomsInOffice(officeId);
    for (ConferenceRoom room : rooms) {
      addLinks(officeId, room, authentication);
    }
    return new ResponseEntity<>(rooms, HttpStatus.OK);
  }

  @PostMapping("/office/{officeId}/rooms")
  public ResponseEntity createConferenceRoom(@PathVariable Long officeId, @RequestBody ConferenceRoom conferenceRoom, Authentication authentication) {
    conferenceRoom = conferenceRoomService.createConferenceRoom(officeId, conferenceRoom);
    addLinks(officeId, conferenceRoom, authentication);
    return new ResponseEntity<>(conferenceRoom, HttpStatus.OK);
  }

  @PutMapping("/office/{officeId}/room/{conferenceRoomId}")
  public ResponseEntity editConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId, @RequestBody ConferenceRoom conferenceRoom, Authentication authentication) {
    conferenceRoom = conferenceRoomService.editConferenceRoom(officeId, conferenceRoomId, conferenceRoom);
    addLinks(conferenceRoomId, conferenceRoom, authentication);
    return new ResponseEntity<>(conferenceRoom, HttpStatus.OK);
  }

  @DeleteMapping("/office/{officeId}/room/{conferenceRoomId}")
  public ResponseEntity deleteConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId) {
    conferenceRoomService.deleteConferenceRoom(officeId, conferenceRoomId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
