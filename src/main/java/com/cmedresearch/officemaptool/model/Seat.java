package com.cmedresearch.officemaptool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SEAT")
public class Seat {
  @Id
  @Column(name = "SEAT_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long seatId;
  @Column(name = "OFFICE_ID")
  private Long officeId;
  @Column(name = "CENTER_X")
  private Integer centerX;
  @Column(name = "CENTER_Y")
  private Integer centerY;
  @Column(name = "EMPLOYEE_ID")
  private Long employeeId;
}
