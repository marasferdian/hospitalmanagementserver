package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficeController {
    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService)
    {
        this.officeService = officeService;
    }

    @GetMapping("/office/{officeId}")
    public ResponseEntity getOffice(@PathVariable Integer officeId) {
        return new ResponseEntity<>(officeService.getAllOffice(officeId), HttpStatus.OK);
    }
}
