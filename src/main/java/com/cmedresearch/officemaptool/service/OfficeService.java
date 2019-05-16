package com.cmedresearch.officemaptool.service;

import com.cmedresearch.officemaptool.exception.NotFoundException;
import com.cmedresearch.officemaptool.model.Office;
import com.cmedresearch.officemaptool.model.Seat;
import com.cmedresearch.officemaptool.persistence.ConferenceRoomRepository;
import com.cmedresearch.officemaptool.persistence.OfficeRepository;
import com.cmedresearch.officemaptool.persistence.SeatRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OfficeService {
  private OfficeRepository officeRepository;
  private SeatRepository seatRepository;
  private ConferenceRoomRepository conferenceRoomRepository;

  @Autowired
  public OfficeService(
      OfficeRepository officeRepository,
      SeatRepository seatRepository,
      ConferenceRoomRepository conferenceRoomRepository
  ) {
    this.officeRepository = officeRepository;
    this.seatRepository = seatRepository;
    this.conferenceRoomRepository = conferenceRoomRepository;
  }

  public Office getOfficeById(Long officeId) {
    Office office = officeRepository.findByOfficeId(officeId);
    if (office == null) {
      throw new NotFoundException();
    }
    return office;
  }

  public Office getOfficeByEmployeeId(Long employeeId) {
    Seat seat = seatRepository.findByEmployeeId(employeeId);
    if (seat == null) {
      throw new NotFoundException();
    }
    return officeRepository.findByOfficeId(seat.getOfficeId());
  }

  public Office createOffice(Office office) {
    return officeRepository.save(office);
  }

  public Office editOffice(Long officeId, Office newOffice) {
    Office office = getOfficeById(officeId);
    office.setName(newOffice.getName());
    office.setCountry(newOffice.getCountry());
    office.setCity(newOffice.getCity());
    office.setAddress(newOffice.getAddress());
    return officeRepository.save(office);
  }

  public void deleteOffice(Long officeId) {
    getOfficeById(officeId);
    seatRepository.deleteAllByOfficeId(officeId);
    conferenceRoomRepository.deleteAllByOfficeId(officeId);
    officeRepository.deleteByOfficeId(officeId);
  }

  public List<Office> getAllOffices() {
    return IteratorUtils.toList(officeRepository.findAll().iterator());
  }
}
