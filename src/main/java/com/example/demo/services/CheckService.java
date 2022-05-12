package com.example.demo.services;

import com.example.demo.modules.Check;
import com.example.demo.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicTreeUI;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
/*
  This class implements the business logic.
 */

@Service
public class CheckService {

    private CheckRepository checkRepository;

    @Autowired
    public CheckService(CheckRepository repository) {
        this.checkRepository = repository;
    }

    public List<Check> getChecks(){
        return checkRepository.findAll();
    }

    public Check findByName(String name) {
        Optional<Check> result = checkRepository.findById(name);

        Check theCheck = null;

        if (result.isPresent()) {
            theCheck = result.get();
        } else {
            throw new RuntimeException("Check not found " + name);
        }
        return theCheck;
    }

    public void save(Check theCheck) {
        checkRepository.save(theCheck);
    }

    @Transactional // with this annotation you don't have to save the check
    public void updateCheckProperties(String name, String docSource, String attribute) {
        Check theCheck = checkRepository.getById(name);
        if(docSource != null && docSource.length() > 0
                && !Objects.equals(theCheck.getDocSource(),docSource)){
            theCheck.setDocSource(docSource);
        }
        if(attribute != null && attribute.length() > 0
                && !Objects.equals(theCheck.getCheckAttribute(),attribute)) {
            theCheck.setCheckAttribute(attribute);
        }
//        checkRepository.save(theCheck);
    }

    public void deleteByName(String name) {
        checkRepository.deleteById(name);
    }
}
