package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.Seat;
import com.cmedresearch.officemaptool.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatController {
  private SeatService seatService;

  @Autowired
  public SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

  @GetMapping("/office/{officeId}/seats")
  public ResponseEntity getSeats(@PathVariable Long officeId) {
    return new ResponseEntity<>(seatService.getAllSeatsInOffice(officeId), HttpStatus.OK);
  }

  @GetMapping("/office/{officeId}/seat/{seatId}")
  public ResponseEntity getSeat(@PathVariable Long officeId, @PathVariable Long seatId) {
    return new ResponseEntity<>(seatService.getSeatById(officeId, seatId), HttpStatus.OK);
  }

  @PostMapping("/office/{officeId}/seats")
  public ResponseEntity createSeat(@PathVariable Long officeId, @RequestBody Seat seat) {
    return new ResponseEntity<>(seatService.createSeatInOffice(officeId, seat), HttpStatus.OK);
  }

  @PutMapping("/office/{officeId}/seat/{seatId}")
  public ResponseEntity editSeat(@PathVariable Long officeId, @PathVariable Long seatId, @RequestBody Seat seat) {
    return new ResponseEntity<>(seatService.editSeat(officeId, seatId, seat), HttpStatus.OK);
  }

  @DeleteMapping("/office/{officeId}/seat/{seatId}")
  public ResponseEntity deleteSeat(@PathVariable Long officeId, @PathVariable Long seatId) {
    seatService.deleteSeat(officeId, seatId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
