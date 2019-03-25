package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.ConferenceRoom;
import com.cmedresearch.officemaptool.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConferenceRoomController {
    private ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping("/office/{officeId}/room/{conferenceRoomId}")
    public ResponseEntity getConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId) {
        return new ResponseEntity<>(conferenceRoomService.getConferenceRoomById(officeId, conferenceRoomId), HttpStatus.OK);

    }

    @GetMapping("/office/{officeId}/rooms")
    public ResponseEntity getConferenceRooms(@PathVariable Long officeId) {
        return new ResponseEntity<>(conferenceRoomService.getAllConferenceRoomsInOffice(officeId), HttpStatus.OK);
    }

    @PostMapping("/office/{officeId}/rooms")
    public ResponseEntity createConferenceRoom(@PathVariable Long officeId, @RequestBody ConferenceRoom conferenceRoom) {
        return new ResponseEntity<>(conferenceRoomService.createConferenceRoom(officeId, conferenceRoom), HttpStatus.OK);
    }

    @PutMapping("/office/{officeId}/room/{conferenceRoomId}")
    public ResponseEntity editConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId, @RequestBody ConferenceRoom conferenceRoom) {
        return new ResponseEntity<>(conferenceRoomService.editConferenceRoom(officeId, conferenceRoomId, conferenceRoom), HttpStatus.OK);
    }

    @DeleteMapping("/office/{officeId}/room/{conferenceRoomId}")
    public ResponseEntity deleteConferenceRoom(@PathVariable Long officeId, @PathVariable Long conferenceRoomId) {
        conferenceRoomService.deleteConferenceRoom(officeId, conferenceRoomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
