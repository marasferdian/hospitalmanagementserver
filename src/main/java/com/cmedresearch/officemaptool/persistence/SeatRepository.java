package com.cmedresearch.officemaptool.persistence;

import com.cmedresearch.officemaptool.model.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {
  Iterable<Seat> findAllByOfficeId(Long officeId);
  Seat findBySeatId(Long seatId);
  Seat findByEmployeeId(Long employeeId);
  void deleteBySeatId(Long seatId);
  void deleteAllByOfficeId(Long officeId);
}
