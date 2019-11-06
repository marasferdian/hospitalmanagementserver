package com.hospitalmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "APPOINTMENTS")
public class Appointment extends ResourceSupport {
    @Id
    @Column(name = "APPOINTMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;


    @Column(name = "PACIENT")
    private Long pacientId;

    @Column(name = "MEDIC")
    private Long medicId;

    @Column(name = "DATE")
    private Date date;


}
