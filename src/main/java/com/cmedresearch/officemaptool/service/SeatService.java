package com.cmedresearch.officemaptool.service;

import com.cmedresearch.officemaptool.model.Seat;
import com.cmedresearch.officemaptool.persistence.SeatRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeatService {
  private SeatRepository seatRepository;

  @Autowired
  public SeatService(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  public Seat getSeatById(Long officeId, Long seatId) {
    Seat seat = seatRepository.findBySeatId(seatId);
    if (seat == null || !seat.getOfficeId().equals(officeId)) {
      throw new RuntimeException();
    }
    return seat;
  }

  public List<Seat> getAllSeatsInOffice(Long officeId) {
    return IteratorUtils.toList(seatRepository.findAllByOfficeId(officeId).iterator());
  }

  public Seat createSeatInOffice(Long officeId, Seat seat) {
    seat.setOfficeId(officeId);
    if (seat.getEmployeeId() != null && seatRepository.findByEmployeeId(seat.getEmployeeId()) != null) {
      throw new RuntimeException();
    }
    return seatRepository.save(seat);
  }

  public Seat editSeat(Long officeId, Long seatId, Seat newSeat) {
    Seat seat = getSeatById(officeId, seatId);
    if (newSeat.getEmployeeId() != null && !newSeat.getEmployeeId().equals(seat.getEmployeeId()) &&
        seatRepository.findByEmployeeId(newSeat.getEmployeeId()) != null) {
      throw new RuntimeException();
    }
    seat.setCenterX(newSeat.getCenterX());
    seat.setCenterY(newSeat.getCenterY());
    seat.setEmployeeId(newSeat.getEmployeeId());
    return seatRepository.save(seat);
  }

  public void deleteSeat(Long officeId, Long seatId) {
    getSeatById(officeId, seatId);
    seatRepository.deleteBySeatId(seatId);
  }
}
