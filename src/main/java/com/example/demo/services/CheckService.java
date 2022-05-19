package com.example.demo.services;

import com.example.demo.modules.Check2;
import com.example.demo.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Check2> findAll(){
        return checkRepository.findAll();
    }

    public Check2 findByName(String name) {
        Optional<Check2> result = checkRepository.findById(name);

        Check2 theCheck = null;

        if (result.isPresent()) {
            theCheck = result.get();
        }
        /*else {
            throw new RuntimeException("Check not found " + name);
        }*/
        return theCheck;
    }

    public void save(Check2 theCheck) {
        checkRepository.save(theCheck);
    }

    // For update, we only need save
    // since the info to be changed will be retrieved from front-end,
    // and afterwards, we just save it, just like when adding a new check.
   /*  public void updateCheckProperties(String name) {
        Check theCheck = checkRepository.getById(name);
        checkRepository.save(theCheck);
       if(docSource != null && docSource.length() > 0
                && !Objects.equals(theCheck.getDocSource(),docSource)){
            theCheck.setDocSource(docSource);
        }
        if(attribute != null && attribute.length() > 0
                && !Objects.equals(theCheck.getCheckAttribute(),attribute)) {
            theCheck.setCheckAttribute(attribute);
        }
    }*/


    public void deleteByName(String name) {
        checkRepository.deleteById(name);
    }
}
