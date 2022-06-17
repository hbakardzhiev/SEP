package com.example.demo.configuration;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/** Configuration file for adding checks to the database. */
@Configuration
public class CheckConfiguration {
  @Bean
  CommandLineRunner commandLineCheck(CheckRepository repository) {

    return args -> {
      Check firstCheck =
          new Check("Check1", "Change Notice", "name", "", "comment to show when the check fails ");
      Action actionType = new Action("NotEmpty", "", "pls1");
      actionType.add(firstCheck);

      Check secondCheck = new Check("Check2", "Change Notice", "solution", "", "comment2");
      Action actionType2 = new Action("NotEmpty", "", "pls2");
      actionType2.add(secondCheck);

      Check check6 =
          new Check("Check6", "Change Notice", "customerApprovalRequired", "No", "comment3");
      Action actionType6 = new Action("IsEqual", "String", "pls3");
      actionType6.add(check6);

      Check thirdCheck =
          new Check("Check3", "Change Notice", "supplierApprovalRequired", "No", "comment3");
      Action actionType3 = new Action("IsEqual", "String", "pls3");
      actionType3.add(thirdCheck);

      // Change Requests checks
      Check forthCheck = new Check("Check4", "Change Request", "name", "", "comment3");
      Action actionType4 = new Action("NotEmpty", "", "pls3");
      actionType4.add(forthCheck);

      Check check5 = new Check("Check5", "Change Request", "theRequestPriority", "", "comment3");
      Action actionType5 = new Action("NotEmpty", "", "pls3");
      actionType5.add(check5);

      Check check7 =
          new Check("Check7", "Change Request", "customerApprovalRequired", "No", "comment3");
      Action actionType7 = new Action("IsEqual", "String", "pls3");
      actionType7.add(check7);

      Check check8 =
          new Check("Check8", "Change Request", "supplierApprovalRequired", "No", "comment3");
      Action actionType8 = new Action("IsEqual", "String", "pls3");
      actionType8.add(check8);

      Check check9 = new Check("Check9", "Change Task", "name", "", "comment3");
      Action actionType9 = new Action("NotEmpty", "", "pls3");
      actionType9.add(check9);

      Check check10 = new Check("Check10", "Controlled Document", "name", "", "comment3");
      Action actionType10 = new Action("HumanCheck", "", "pls3");
      actionType10.add(check10);

      Check check11 = new Check("Check11", "Controlled Document", "name", "FRU", "comment3");
      Action actionType11 = new Action("NotContains", "String", "pls3");
      actionType11.add(check11);

      Check check12 = new Check("Check12", "Controlled Document", "name", "40", "comment3");
      Action actionType12 = new Action("LengthSmallerEqual", "Integer", "pls3");
      actionType12.add(check12);

      Check check13 = new Check("Check13", "Controlled Document", "phiSendNative", "", "comment3");
      Action actionType13 = new Action("HumanCheck", "", "pls3");
      actionType13.add(check13);

      Check check14 = new Check("Check14", "Controlled Document", "phiDMR", "Yes", "comment3");
      Action actionType14 = new Action("IsEqual", "String", "pls3");
      actionType14.add(check14);

      Check check15 =
          new Check(
              "Check15", "Controlled Document", "designcategory", "3. Design Output", "comment3");
      Action actionType15 = new Action("IsEqual", "String", "pls3");
      actionType15.add(check15);

      Check check16 = new Check("Check16", "Medical Device Data", "name", "40", "comment3");
      Action actionType16 = new Action("LengthSmallerEqual", "Integer", "pls3");
      actionType16.add(check16);

      Check check17 = new Check("Check17", "Catalog Item", "name", "40", "comment3");
      Action actionType17 = new Action("LengthSmallerEqual", "Integer", "pls3");
      actionType17.add(check17);

      Check check18 = new Check("Check18", "Medical Device", "name", "40", "comment3");
      Action actionType18 = new Action("LengthSmallerEqual", "Integer", "pls3");
      actionType18.add(check18);

      Check check19 = new Check("Check19", "GS1 Packaging Sheet", "name", "40", "comment3");
      Action actionType19 = new Action("LengthSmallerEqual", "Integer", "pls3");
      actionType19.add(check19);

      Check check20 = new Check("Check20", "GS1 Packaging Sheet", "phiSendNative", "", "comment3");
      Action actionType20 = new Action("HumanCheck", "", "pls3");
      actionType20.add(check20);

      Check check21 = new Check("Check21", "GS1 Packaging Sheet", "phiDMR", "Yes", "comment3");
      Action actionType21 = new Action("IsEqual", "String", "pls3");
      actionType21.add(check21);

      Check check22 =
          new Check(
              "Check22", "GS1 Packaging Sheet", "designcategory", "3. Design Output", "comment3");
      Action actionType22 = new Action("IsEqual", "String", "pls3");
      actionType22.add(check22);

      Check check23 = new Check("Check23", "Engineered Part", "name", "", "comment3");
      Action actionType23 = new Action("NotEmpty", "", "pls3");
      actionType23.add(check23);

      Check check24 = new Check("Check24", "Engineered Part", "name", "41", "comment3");
      Action actionType24 = new Action("LengthStrictlySmaller", "Integer", "pls3");
      actionType24.add(check24);

      Check check25 =
          new Check("Check25", "Engineered Part", "phiPhantomManufacturingPart", "No", "comment3");
      Action actionType25 = new Action("IsEqual", "String", "pls3");
      actionType25.add(check25);

      Check check26 =
          new Check("Check26", "Engineered Part", "maturity", "Production/Service", "comment3");
      Action actionType26 = new Action("IsEqual", "String", "pls3");
      actionType26.add(check26);

      Check check27 = new Check("Check27", "Configurable Part", "name", "", "comment3");
      Action actionType27 = new Action("IsNotEmpty", "", "pls3");
      actionType27.add(check27);

      Check check28 = new Check("Check28", "Configurable Part", "name", "41", "comment3");
      Action actionType28 = new Action("LengthStrictlySmaller", "Integer", "pls3");
      actionType28.add(check28);

      Check check29 =
          new Check(
              "Check29", "Configurable Part", "phiPhantomManufacturingPart", "No", "comment3");
      Action actionType29 = new Action("IsEqual", "String", "pls3");
      actionType29.add(check29);

      Check check30 =
          new Check(
              "Check30", "Configurable Part", "phiPhantomManufacturingPart", "Yes", "comment3");
      Action actionType30 = new Action("IsEqual", "String", "pls3");
      actionType30.add(check30);

      Check check31 =
          new Check("Check31", "Configurable Part", "maturity", "Production/Service", "comment3");
      Action actionType31 = new Action("IsEqual", "String", "pls3");
      actionType31.add(check31);

      Check check32 = new Check("Check32", "Engineered", "name", "", "comment3");
      Action actionType32 = new Action("NotEmpty", "", "pls3");
      actionType32.add(check32);

      Check check33 = new Check("Check33", "Engineered", "name", "41", "comment3");
      Action actionType33 = new Action("LengthStrictlySmaller", "Integer", "pls3");
      actionType33.add(check33);

      // put customer Approval when equal is ready

      // Change Tasks checks

      repository.saveAll(
          List.of(
              firstCheck,
              secondCheck,
              thirdCheck,
              forthCheck,
              check5,
              check6,
              check7,
              check8,
              check9,
              check10,
              check11,
              check12,
              check13,
              check14,
              check15,
              check16,
              check17,
              check18,
              check19,
              check20,
              check21,
              check22,
              check23,
              check24,
              check25,
              check26,
              check27,
              check28,
              check29,
              check30,
              check31,
              check32,
              check33));
    };
  }
}
