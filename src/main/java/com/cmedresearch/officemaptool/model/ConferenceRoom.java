package com.cmedresearch.officemaptool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONFERENCE")
public class ConferenceRoom {
    @Id
    @Column(name = "CONFERENCE_ROOM_ID")
    private Integer conferenceRoomId;
    @Column(name = "OFFICE_ID")
    private Integer officeId;
    @Column(name = "TOP_LEFT_X")
    private Integer topLeftX;
    @Column(name = "BOTTOM_RIGHT_Y")
    private Integer bottomRightY;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NO_OF_SEATS")
    private Integer noOfSeats;
    @Column(name = "FEATURES")
    private String features;
}

