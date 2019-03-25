package com.cmedresearch.officemaptool.service;

import com.cmedresearch.officemaptool.model.Office;
import com.cmedresearch.officemaptool.persistence.OfficeRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OfficeService {
    private OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Office getOfficeById(Long officeId) {
        Office office = officeRepository.findByOfficeId(officeId);
        if (office == null) {
            throw new RuntimeException();
        }
        return office;
    }

    public Office createOffice(Office office) {
        return officeRepository.save(office);
    }

    public Office editOffice(Long officeId, Office newOffice) {
        Office office = getOfficeById(officeId);
        office.setName(newOffice.getName());
        office.setCountry(newOffice.getCountry());
        office.setCity(newOffice.getCity());
        office.setAddress(newOffice.getAddress());
        return officeRepository.save(office);
    }

    public void deleteOffice(Long officeId) {
        getOfficeById(officeId);
        officeRepository.deleteByOfficeId(officeId);
    }

    public List<Office> getAllOffices() {
        return IteratorUtils.toList(officeRepository.findAll().iterator());
    }
}
