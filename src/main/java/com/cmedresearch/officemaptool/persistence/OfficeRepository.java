package com.cmedresearch.officemaptool.persistence;

import com.cmedresearch.officemaptool.model.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {
    Iterable<Office> findAllOfficeId(Integer officeId);
    Office findByOfficeId(Integer officeId);
}
