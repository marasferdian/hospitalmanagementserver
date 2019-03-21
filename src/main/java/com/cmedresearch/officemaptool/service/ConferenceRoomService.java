package com.cmedresearch.officemaptool.service;

import com.cmedresearch.officemaptool.model.ConferenceRoom;
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

    public ConferenceRoom getConferenceRoomById(Integer conferenceRoomId) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomId(conferenceRoomId);
        if (conferenceRoom == null) {
            throw new RuntimeException();
        }
        return conferenceRoom;
    }

    public List<ConferenceRoom> getAllConferenceRoomInOffice(Integer conferenceRoomId) {
        return IteratorUtils.toList(conferenceRoomRepository.findAllByConferenceRoomId(conferenceRoomId).iterator());
    }
}
