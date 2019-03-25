package com.cmedresearch.officemaptool.api;

import com.cmedresearch.officemaptool.model.Office;
import com.cmedresearch.officemaptool.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OfficeController {
    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/office/{officeId}")
    public ResponseEntity getOffice(@PathVariable Long officeId) {
        return new ResponseEntity<>(officeService.getOfficeById(officeId), HttpStatus.OK);
    }

    @GetMapping("/offices")
    public ResponseEntity getOffices() {
        return new ResponseEntity<>(officeService.getAllOffices(), HttpStatus.OK);
    }

    @PostMapping("/offices")
    public ResponseEntity createOffice(@RequestBody Office office) {
        return new ResponseEntity<>(officeService.createOffice(office), HttpStatus.OK);
    }

    @PutMapping("/office/{officeId}")
    public ResponseEntity editOffice(@PathVariable Long officeId, @RequestBody Office office) {
        return new ResponseEntity<>(officeService.editOffice(officeId, office), HttpStatus.OK);
    }

    @DeleteMapping("/office/{officeId}")
    public ResponseEntity deleteOffice(@PathVariable Long officeId) {
        officeService.deleteOffice(officeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

