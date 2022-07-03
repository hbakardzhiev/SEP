package com.example.demo.services;

import com.example.demo.modules.Check;
import com.example.demo.modules.SheetSource;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.SheetSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/** Service layer for Check class - holds the relevant business logic. */
@Service
public class CheckService {

  private CheckRepository checkRepository;

  private SheetSourceRepository sheetSourceRepository;

  /** Constructor to use the correct repository */
  @Autowired
  public CheckService(CheckRepository repository, SheetSourceRepository sheetSourceRepository) {
    this.sheetSourceRepository = sheetSourceRepository;
    this.checkRepository = repository;
  }

  /**
   * Returns the list of all checks in the database
   *
   * @return list of checks
   */
  public List<Check> findAll() {
    return checkRepository.findAll();
  }

  /**
   * Returns the check
   *
   * @param name the name of the check to be found
   * @return the check that has the name given as param
   */
  public Check findByName(String name) {
    Optional<Check> result = checkRepository.findById(name);

    Check theCheck = null;

    if (result.isPresent()) {
      theCheck = result.get();
    }
    /*else {
        throw new RuntimeException("Check not found " + name);
    }*/
    return theCheck;
  }

  /**
   * Saves the check
   *
   * @param theCheck the check to be saved in the database
   */
  public void save(Check theCheck) {
    createSheetSource(theCheck);
    checkRepository.save(theCheck);
  }

  private void createSheetSource(Check theCheck) {
    //  add a new attribute type in the sheet_source table if it does not exist already
    String typeOfAttribute = theCheck.getAttribute();
    SheetType sheetType = getSheetType(theCheck.getDocSource());
    if (!sheetSourceRepository.existsByHtmlIDAndSheetSourceType(typeOfAttribute, sheetType)) {
      SheetSource sheetSource =
              new SheetSource(typeOfAttribute, String.class.getTypeName(), sheetType);
      sheetSourceRepository.save(sheetSource);
    }
  }

  private SheetType getSheetType(String docSource) {
    SheetType sheetType;
    sheetType =
            switch (docSource) {
              case "Change Notice" -> SheetType.CN;
              case "Change Request" -> SheetType.CR;
              case "Engineering Change Task",
                      "Manufacturing Change Task",
                      "Master Data Change Task",
                      "Commercial Change Task" -> SheetType.CT;
              default -> SheetType.DMR;
            };
    return sheetType; // prone to mistakes everything which is not correct will be DMR
  }

  /**
   * Deletes the check that is already in the database
   *
   * @param name the name of the check to be deleted
   */
  public void deleteByName(String name) {
    checkRepository.deleteById(name);
  }
}
