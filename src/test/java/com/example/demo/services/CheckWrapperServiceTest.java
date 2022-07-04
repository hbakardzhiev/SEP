package com.example.demo.services;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.modules.Action;
import com.example.demo.modules.ActionNameString;
import com.example.demo.modules.Check;
import com.example.demo.modules.CheckAndActionName;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.SheetSourceRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CheckWrapperService.class})
@ExtendWith(SpringExtension.class)
class CheckWrapperServiceTest {
    @MockBean
    private ActionService actionService;

    @MockBean
    private AdminRepository adminRepository;

    @MockBean
    private CheckRepository checkRepository;

    @Autowired
    private CheckWrapperService checkWrapperService;

    @MockBean
    private SheetSourceRepository sheetSourceRepository;

    /**
     * Method under test: {@link CheckWrapperService#extractCheck(CheckAndActionName)}
     */
    @Test
//    @Disabled("TODO: Complete this test")
    void testExtractCheck() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.demo.modules.ActionNameString.getActionName()" because the return value of "com.example.demo.modules.CheckAndActionName.getActionName()" is null
        //       at com.example.demo.services.CheckWrapperService.extractCheck(CheckWrapperService.java:45)
        //   In order to prevent extractCheck(CheckAndActionName)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   extractCheck(CheckAndActionName).
        //   See https://diff.blue/R013 to resolve this issue.

        this.checkWrapperService.extractCheck(new CheckAndActionName());
    }

    /**
     * Method under test: {@link CheckWrapperService#extractCheck(CheckAndActionName)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExtractCheck2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.demo.modules.Check.setActionType(com.example.demo.modules.Action)" because "tempCheck" is null
        //       at com.example.demo.modules.Action.add(Action.java:54)
        //       at com.example.demo.services.CheckWrapperService.extractCheck(CheckWrapperService.java:47)
        //   In order to prevent extractCheck(CheckAndActionName)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   extractCheck(CheckAndActionName).
        //   See https://diff.blue/R013 to resolve this issue.

        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");
        when(this.actionService.findByName((String) any())).thenReturn(action);

        CheckAndActionName checkAndActionName = new CheckAndActionName();
        checkAndActionName.setActionName(new ActionNameString());
        this.checkWrapperService.extractCheck(checkAndActionName);
    }

    /**
     * Method under test: {@link CheckWrapperService#extractCheck(CheckAndActionName)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExtractCheck3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at com.example.demo.Util.getUsernameFromPrincipal(Util.java:47)
        //       at com.example.demo.services.CheckWrapperService.addAuthor(CheckWrapperService.java:64)
        //       at com.example.demo.services.CheckWrapperService.save(CheckWrapperService.java:58)
        //       at com.example.demo.services.CheckWrapperService.extractCheck(CheckWrapperService.java:49)
        //   In order to prevent extractCheck(CheckAndActionName)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   extractCheck(CheckAndActionName).
        //   See https://diff.blue/R013 to resolve this issue.

        Action action = mock(Action.class);
        doNothing().when(action).add((Check) any());
        doNothing().when(action).setAction((String) any());
        doNothing().when(action).setChecks((java.util.List<Check>) any());
        doNothing().when(action).setDescription((String) any());
        doNothing().when(action).setValueType((String) any());
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");
        when(this.actionService.findByName((String) any())).thenReturn(action);

        CheckAndActionName checkAndActionName = new CheckAndActionName();
        checkAndActionName.setActionName(new ActionNameString());
        this.checkWrapperService.extractCheck(checkAndActionName);
    }

    /**
     * Method under test: {@link CheckWrapperService#save(Check)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at com.example.demo.Util.getUsernameFromPrincipal(Util.java:47)
        //       at com.example.demo.services.CheckWrapperService.addAuthor(CheckWrapperService.java:64)
        //       at com.example.demo.services.CheckWrapperService.save(CheckWrapperService.java:58)
        //   In order to prevent save(Check)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   save(Check).
        //   See https://diff.blue/R013 to resolve this issue.

        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActionType(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");
        this.checkWrapperService.save(check);
    }

    /**
     * Method under test: {@link CheckWrapperService#save(Check)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at com.example.demo.Util.getUsernameFromPrincipal(Util.java:47)
        //       at com.example.demo.services.CheckWrapperService.addAuthor(CheckWrapperService.java:64)
        //       at com.example.demo.services.CheckWrapperService.save(CheckWrapperService.java:58)
        //   In order to prevent save(Check)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   save(Check).
        //   See https://diff.blue/R013 to resolve this issue.

        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");
        Check check = mock(Check.class);
        doNothing().when(check).setActionType((Action) any());
        doNothing().when(check).setAttribute((String) any());
        doNothing().when(check).setAuthor((Long) any());
        doNothing().when(check).setComments((String) any());
        doNothing().when(check).setDocSource((String) any());
        doNothing().when(check).setName((String) any());
        doNothing().when(check).setValue((String) any());
        check.setActionType(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");
        this.checkWrapperService.save(check);
    }
}

