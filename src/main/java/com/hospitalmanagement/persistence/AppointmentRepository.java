package com.hospitalmanagement.persistence;

import com.hospitalmanagement.model.Appointment;
import com.hospitalmanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    Optional<Appointment> findById(Long appointmentId);

    void deleteByAppointmentId(Long appointmentId);
}
