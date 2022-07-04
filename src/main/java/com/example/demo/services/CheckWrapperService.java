package com.example.demo.services;

import com.example.demo.Util;
import com.example.demo.modules.Check;
import com.example.demo.modules.CheckAndActionName;
import com.example.demo.modules.SheetSource;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.SheetSourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CheckWrapperService {


    private ActionService actionService;

    private CheckRepository checkRepository;

    private AdminRepository adminRepository;

    private SheetSourceRepository sheetSourceRepository;

    public CheckWrapperService (ActionService actionService, CheckRepository checkRepository, AdminRepository adminRepository,
                                SheetSourceRepository sheetSourceRepository){
        this.actionService = actionService;
        this.checkRepository = checkRepository;
        this.adminRepository = adminRepository;
        this.sheetSourceRepository = sheetSourceRepository;
    }


    /**
     * Extracts the check and the action and saves/updates the check in the db. Associates the check
     * with the corresponding action name from the action table. If a new type of attribute is used
     * which is not in the sheet_source table then the attribute as htmlId is added there as well.
     *
     * @param checkAndActionName a check and its action name
     * @return TODO: make it later void when tested with front end
     */
    public Check extractCheck(@RequestBody CheckAndActionName checkAndActionName) {
        Check theCheck = checkAndActionName.getTheCheck();
        String actionName = checkAndActionName.getActionName().getActionName();

        actionService.findByName(actionName).add(theCheck);

        this.save(theCheck);
        return theCheck;
    }
    /**
     * Saves the check
     *
     * @param theCheck the check to be saved in the database
     */
    public void save(Check theCheck) {
        addAuthor(theCheck);
        createSheetSource(theCheck);
        checkRepository.save(theCheck);
    }

    private void addAuthor(Check theCheck) {
        String username = Util.getUsernameFromPrincipal();
        Long adminId = adminRepository.findAdminByUsername(username).getId();

        theCheck.setAuthor(adminId);
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
}
