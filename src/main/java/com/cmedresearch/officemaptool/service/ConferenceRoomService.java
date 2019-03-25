package com.cmedresearch.officemaptool.service;

import com.cmedresearch.officemaptool.model.ConferenceRoom;
import com.cmedresearch.officemaptool.model.Office;
import com.cmedresearch.officemaptool.persistence.ConferenceRoomRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConferenceRoomService {
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    public ConferenceRoom getConferenceRoomById(Long officeId, Long conferenceRoomId) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomId(conferenceRoomId);
        if (conferenceRoom == null || !conferenceRoom.getOfficeId().equals(officeId)) {
            throw new RuntimeException();
        }
        return conferenceRoom;
    }
    public ConferenceRoom createConferenceRoom(Long officeId, ConferenceRoom conferenceRoom) {
        conferenceRoom.setOfficeId(officeId);
        return conferenceRoomRepository.save(conferenceRoom);
    }

    public ConferenceRoom editConferenceRoom(Long officeId, Long conferenceRoomId, ConferenceRoom newConferenceRoom) {
        ConferenceRoom conferenceRoom = getConferenceRoomById(officeId, conferenceRoomId);
        conferenceRoom.setName(newConferenceRoom.getName());
        conferenceRoom.setTopLeftX(newConferenceRoom.getTopLeftX());
        conferenceRoom.setTopLeftY(newConferenceRoom.getTopLeftY());
        conferenceRoom.setBottomRightX(newConferenceRoom.getBottomRightX());
        conferenceRoom.setBottomRightY(newConferenceRoom.getBottomRightY());
        conferenceRoom.setNoOfSeats(newConferenceRoom.getNoOfSeats());
        conferenceRoom.setFeatures(newConferenceRoom.getFeatures());
        return conferenceRoomRepository.save(conferenceRoom);
    }

    public void deleteConferenceRoom(Long officeId, Long conferenceRoomId) {
        getConferenceRoomById(officeId, conferenceRoomId);
        conferenceRoomRepository.deleteByConferenceRoomId(conferenceRoomId);
    }

    public List<ConferenceRoom> getAllConferenceRoomsInOffice(Long officeId) {
        return IteratorUtils.toList(conferenceRoomRepository.findAllByOfficeId(officeId).iterator());
    }
}
