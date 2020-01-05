package com.hospitalmanagement.service;


import com.hospitalmanagement.exception.NotAvailableException;
import com.hospitalmanagement.exception.NotFoundException;
import com.hospitalmanagement.model.Appointment;
import com.hospitalmanagement.model.User;
import com.hospitalmanagement.persistence.AppointmentRepository;
import com.hospitalmanagement.persistence.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        /*Appointment appointment=new Appointment();
        appointment.setPacientId((long) 100);
        appointment.setMedicId((long)68);
        Calendar myCal=new GregorianCalendar(2020,Calendar.APRIL,26);
        java.util.Date myDate=myCal.getTime();
        Date sqlDate=new Date(myDate.getTime());
        appointment.setDate(sqlDate);
        appointmentRepository.save(appointment);*/

    }

    private Appointment fetchAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty()) {
            throw new NotFoundException();
        }
        return appointment.get();
    }

    public Appointment getAppointment(Long id) {
        return fetchAppointment(id);
    }

    public List<Appointment> getAppointments() {
        return IteratorUtils.toList(appointmentRepository.findAll().iterator());
    }

    public Appointment createAppointment(Long pacientId, Long medicId, Date date) {
        List<Appointment> currentAppointments = getAppointments();
        Optional<User> medic = userRepository.findById(medicId);
        if (medic.isEmpty())
            throw new NotFoundException();
        for (Appointment appointment : currentAppointments) {
            if (appointment.getMedicId().equals(medicId) && appointment.getDate().equals(date))
                throw new NotAvailableException();
        }

        Appointment newAppointment = new Appointment();
        newAppointment.setDate(date);
        newAppointment.setMedicId(medicId);
        newAppointment.setPacientId(pacientId);
        return appointmentRepository.save(newAppointment);


    }

    public void deleteAppointment(Long appointmentId) {
        fetchAppointment(appointmentId);
        appointmentRepository.deleteByAppointmentId(appointmentId);
    }

    public Appointment editAppointment(Long appointmentId, Appointment appointment) {
        Appointment foundAppointment = fetchAppointment(appointmentId);
        foundAppointment.setPacientId(appointment.getPacientId());
        foundAppointment.setMedicId(appointment.getMedicId());
        foundAppointment.setDate(appointment.getDate());
        return foundAppointment;
    }
    public Appointment findAppointmentByPacientId(Long pacientId)
    {
        List<Appointment> appointments=getAppointments();
        for(Appointment appointment:appointments)
        {
            if((appointment.getPacientId()).equals(pacientId))
                return appointment;
        }
        throw new NotFoundException();
    }
    public Appointment findAppointmentByUsername(String username)
    {
        Optional <User> userOptional =userRepository.findByUsername(username);
        if(userOptional.isEmpty())
            throw new NotFoundException();
        User foundUser=userOptional.get();
        Optional<Appointment> foundAppointment=appointmentRepository.findByPacientId(foundUser.getUserId());
        if(foundAppointment.isEmpty())
        {
            throw new NotFoundException();
        }
        return foundAppointment.get();
    }
}
