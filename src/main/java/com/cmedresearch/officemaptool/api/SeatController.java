package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {
  private SeatService seatService;

  @Autowired
  public SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

  @GetMapping("/office/{officeId}/seats")
  public ResponseEntity getSeats(@PathVariable Integer officeId) {
    return new ResponseEntity<>(seatService.getAllSeatsInOffice(officeId), HttpStatus.OK);
  }
}
