package com.cmedresearch.officemaptool.persistence;

import com.cmedresearch.officemaptool.model.ConferenceRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends CrudRepository<ConferenceRoom, Integer> {
    Iterable<ConferenceRoom> findAllByConferenceRoomId(Integer conferenceRoomId);
    ConferenceRoom findByConferenceRoomId(Integer conferenceRoomId);
}
