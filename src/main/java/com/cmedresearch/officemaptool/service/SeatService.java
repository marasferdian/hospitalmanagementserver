package com.cmedresearch.officemaptool.service;

import com.cmedresearch.officemaptool.model.Seat;
import com.cmedresearch.officemaptool.persistence.SeatRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
  private SeatRepository seatRepository;

  @Autowired
  public SeatService(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  public Seat getSeatById(Integer seatId) {
    Seat seat = seatRepository.findBySeatId(seatId);
    if (seat == null) {
      throw new RuntimeException();
    }
    return seat;
  }

  public List<Seat> getAllSeatsInOffice(Integer officeId) {
    return IteratorUtils.toList(seatRepository.findAllByOfficeId(officeId).iterator());
  }
}
