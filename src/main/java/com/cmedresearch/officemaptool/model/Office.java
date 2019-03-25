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
@Table(name = "OFFICE")
public class Office{
    @Id
    @Column(name = "OFFICE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long officeId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ADDRESS")
    private String address;
}
