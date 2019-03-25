package com.cmedresearch.officemaptool.persistence;

import com.cmedresearch.officemaptool.model.ConferenceRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends CrudRepository<ConferenceRoom, Long> {
    Iterable<ConferenceRoom> findAllByOfficeId(Long officeId);
    ConferenceRoom findByConferenceRoomId(Long conferenceRoomId);
    void deleteByConferenceRoomId(Long conferenceRoomId);
    void deleteAllByOfficeId(Long officeId);
}
