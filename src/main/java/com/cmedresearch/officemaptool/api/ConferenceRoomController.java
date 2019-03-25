package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceRoomController {
    private ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping("/office/{officeId}/rooms/{conferenceRoomId}")
    public ResponseEntity getConferenceRoom(@PathVariable Integer conferenceRoomId) {
        return new ResponseEntity<>(conferenceRoomService.getAllConferenceRoomInOffice(conferenceRoomId), HttpStatus.OK);

    }
}
