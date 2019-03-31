package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.Seat;
import com.cmedresearch.officemaptool.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SeatController {
  private SeatService seatService;

  @Autowired
  public SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

  private static void addLinks(Long officeId, Seat seat) {
    seat.removeLinks();
    seat.add(
        linkTo(methodOn(SeatController.class).getSeat(officeId, seat.getSeatId())).withSelfRel(),
        linkTo(methodOn(SeatController.class).editSeat(officeId, seat.getSeatId(), null)).withRel("update"),
        linkTo(methodOn(SeatController.class).deleteSeat(officeId, seat.getSeatId())).withRel("delete")
    );
  }

  @GetMapping("/office/{officeId}/seats")
  public ResponseEntity getSeats(@PathVariable Long officeId) {
    List<Seat> seats = seatService.getAllSeatsInOffice(officeId);
    for (Seat seat : seats) {
      addLinks(officeId, seat);
    }
    return new ResponseEntity<>(seats, HttpStatus.OK);
  }

  @GetMapping("/office/{officeId}/seat/{seatId}")
  public ResponseEntity getSeat(@PathVariable Long officeId, @PathVariable Long seatId) {
    Seat seat = seatService.getSeatById(officeId, seatId);
    addLinks(officeId, seat);
    return new ResponseEntity<>(seat, HttpStatus.OK);
  }

  @PostMapping("/office/{officeId}/seats")
  public ResponseEntity createSeat(@PathVariable Long officeId, @RequestBody Seat seat) {
    seat = seatService.createSeatInOffice(officeId, seat);
    addLinks(officeId, seat);
    return new ResponseEntity<>(seat, HttpStatus.OK);
  }

  @PutMapping("/office/{officeId}/seat/{seatId}")
  public ResponseEntity editSeat(@PathVariable Long officeId, @PathVariable Long seatId, @RequestBody Seat seat) {
    seat = seatService.editSeat(officeId, seatId, seat);
    addLinks(officeId, seat);
    return new ResponseEntity<>(seat, HttpStatus.OK);
  }

  @DeleteMapping("/office/{officeId}/seat/{seatId}")
  public ResponseEntity deleteSeat(@PathVariable Long officeId, @PathVariable Long seatId) {
    seatService.deleteSeat(officeId, seatId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
