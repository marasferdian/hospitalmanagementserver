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
@Table(name = "SEAT")
public class Seat {
  @Id
  @Column(name = "SEAT_ID")
  private Integer seatId;
  @Column(name = "OFFICE_ID")
  private Integer officeId;
  @Column(name = "CENTER_X")
  private Integer centerX;
  @Column(name = "CENTER_Y")
  private Integer centerY;
  @Column(name = "EMPLOYEE_ID")
  private Integer employeeId;
}
