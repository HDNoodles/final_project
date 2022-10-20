package com.example.applicationservice.controller;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.service.ApplicationWorkFlowService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;


@WebMvcTest(controllers = ApplicationWorkFlowController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationWorkFlowControllerTest {

    @MockBean
    private ApplicationWorkFlowService applicationWorkFlowService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_createNewApplicationWorkFlow() throws Exception{

        ApplicationWorkFlow applicationWorkFlow = new ApplicationWorkFlow(999, "test", "test");
        applicationWorkFlowService.createNewApplicationWorkFlow(applicationWorkFlow);
        Mockito.verify(applicationWorkFlowService, times(1)).createNewApplicationWorkFlow(applicationWorkFlow);

        Gson gson = new Gson();
        String expectedJson = gson.toJson(applicationWorkFlow);
        System.out.println(expectedJson);

        mockMvc.perform(MockMvcRequestBuilders.post("/applicationWorkFlow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

//    @Test
//    public void test_getApplicationWorkFlowById() throws Exception {
//        ApplicationWorkFlow expected = new ApplicationWorkFlow(999, 999, now(), now(), "test1", "test1");
//        Mockito.when(applicationWorkFlowService.getApplicationWorkFlowById(999)).thenReturn(expected);
//
//        MvcResult result = mockMvc
//        .perform(MockMvcRequestBuilders.get("/applicationWorkFlow/999")
//                .contentType(MediaType.APPLICATION_JSON))
//        .andReturn();
//
//        Gson gson = new Gson();
//        ApplicationWorkFlow actual = gson.fromJson(result.getResponse().getContentAsString(), ApplicationWorkFlow.class);
//        assertEquals(expected.toString(), actual.toString());
//    }

//    @Test
//    public void getApplicationWorkFlowByEmployeeId() throws Exception {
//        ApplicationWorkFlow expected = new ApplicationWorkFlow(999, 999, now(), now(), "test1", "test1");
//        Mockito.when(applicationWorkFlowService.getAllApplicationWorkFlow().stream()
//                .filter(a -> a.getApplicationWorkFlow_id() == 999)
//                .findFirst()).thenReturn(Optional.of(expected));
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.get("/applicationWorkFlow/employeeId/999)")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        Gson gson = new Gson();
//        ApplicationWorkFlow actual = gson.fromJson(result.getResponse().getContentAsString(), ApplicationWorkFlow.class);
//        assertEquals(expected.toString(), actual.toString());
//    }

    @Test
    public void test_updateStatusApplicationWorkflowById() throws Exception{
        ApplicationWorkFlow original = new ApplicationWorkFlow(999,"test1", "test1");
        ApplicationWorkFlow changed = new ApplicationWorkFlow( 999,"changed", "changed");
        applicationWorkFlowService.updateStatusApplicationWorkflowById(changed.getApplicationWorkFlow_id(), changed);
        Mockito.verify(applicationWorkFlowService, times(1)).updateStatusApplicationWorkflowById(changed.getApplicationWorkFlow_id(), changed);
        assertEquals(original.toString(), changed.toString());
    }
}
