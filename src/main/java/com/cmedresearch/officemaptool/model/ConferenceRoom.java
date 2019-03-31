package com.cmedresearch.officemaptool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONFERENCE")
public class ConferenceRoom extends ResourceSupport {
    @Id
    @Column(name = "CONFERENCE_ROOM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long conferenceRoomId;
    @Column(name = "OFFICE_ID")
    private Long officeId;
    @Column(name = "TOP_LEFT_X")
    private Integer topLeftX;
    @Column(name = "TOP_LEFT_Y")
    private Integer topLeftY;
    @Column(name = "BOTTOM_RIGHT_X")
    private Integer bottomRightX;
    @Column(name = "BOTTOM_RIGHT_Y")
    private Integer bottomRightY;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NO_OF_SEATS")
    private Integer noOfSeats;
    @Column(name = "FEATURES")
    private String features;
}

