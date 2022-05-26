package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.modules.Admin;
import com.example.demo.services.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    /**
     * Method under test: {@link AdminController#getAdmins()}
     */
    @Test
    void testGetAdmins() throws Exception {
        when(this.adminService.getAdmins()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admins");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#getAdmins()}
     */
    @Test
    void testGetAdmins2() throws Exception {
        when(this.adminService.getAdmins()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admins");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#addAdmin(Admin)}
     */
    @Test
    void testAddAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setAdminRole("Admin Role");
        admin.setEmail("jane.doe@example.org");
        admin.setId(123L);
        admin.setPassword("iloveyou");
        admin.setUsername("janedoe");
        when(this.adminService.addAdmin((Admin) any())).thenReturn(admin);

        Admin admin1 = new Admin();
        admin1.setAdminRole("Admin Role");
        admin1.setEmail("jane.doe@example.org");
        admin1.setId(123L);
        admin1.setPassword("iloveyou");
        admin1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(admin1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admins/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"adminRole\":\"Admin"
                                        + " Role\"}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/admins"));
    }

    /**
     * Method under test: {@link AdminController#deleteAdminById(long)}
     */
    @Test
    void testDeleteAdminById() throws Exception {
        doNothing().when(this.adminService).deleteAdmin((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admins/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted"));
    }

    /**
     * Method under test: {@link AdminController#deleteAdminById(long)}
     */
    @Test
    void testDeleteAdminById2() throws Exception {
        doNothing().when(this.adminService).deleteAdmin((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/admins/delete/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted"));
    }

}

