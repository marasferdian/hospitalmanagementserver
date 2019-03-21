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

    public Office getOfficeById(Integer officeId) {
        Office office = officeRepository.findByOfficeId(officeId);
        if (office == null) {
            throw new RuntimeException();
        }
        return office;
    }

    public List<Office>getAllOffice(Integer officeId) {
        return IteratorUtils.toList(officeRepository.findAllOfficeId(officeId).iterator());
    }
}
