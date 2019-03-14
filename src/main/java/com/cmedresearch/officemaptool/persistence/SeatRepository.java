package com.cmedresearch.officemaptool.persistence;

import com.cmedresearch.officemaptool.model.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {
  Iterable<Seat> findAllByOfficeId(Integer officeId);
  Seat findBySeatId(Integer seatId);
}
