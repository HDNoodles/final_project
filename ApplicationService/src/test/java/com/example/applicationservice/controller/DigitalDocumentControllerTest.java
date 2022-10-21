package com.example.applicationservice.controller;

import com.example.applicationservice.dao.DigitalDocumentDao;
import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.domain.response.DigitalDocumentResponse;
import com.example.applicationservice.security.JwtFilter;
import com.example.applicationservice.security.JwtProvider;
import com.example.applicationservice.service.ApplicationWorkFlowService;
import com.example.applicationservice.service.DigitalDocumentService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@WebMvcTest(controllers = DigitalDocumentController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class DigitalDocumentControllerTest {

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private DigitalDocumentService digitalDocumentService;

//    @Mock
//    private DigitalDocumentDao digitalDocumentDao;

    @Autowired
    private MockMvc mockMvc;

//    @Before
//    public void setup() {
//        DigitalDocumentController digitalDocumentController = new DigitalDocumentController();
//        this.mockMvc = MockMvcBuilders.standaloneSetup(digitalDocumentController).build();
//    }

    @Test
    public void test_createNewApplicationWorkFlow() throws Exception{

        DigitalDocument digitalDocument = new DigitalDocument(999, "OPT", "yes", "path1", "test1", "test1");
        digitalDocumentService.createNewDigitalDocument(digitalDocument);
        Mockito.verify(digitalDocumentService, times(1)).createNewDigitalDocument(digitalDocument);

        Gson gson = new Gson();
        String expectedJson = gson.toJson(digitalDocument);
        System.out.println(expectedJson);

        mockMvc.perform(MockMvcRequestBuilders.post("/digitalDocument")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }


//    @Test
//    public void getApplicationWorkFlowByEmployeeId() throws Exception {
//        List<DigitalDocument> expected = new ArrayList<>();
//        expected.add(new DigitalDocument(999, "OPT", "Yes", "path1", "test1", "test1"));
//        Mockito.when(digitalDocumentDao.getDigitalDocumentById(999)).thenReturn(expected);
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.get("/digitalDocument/999")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        Gson gson = new Gson();
//        DigitalDocument actual = gson.fromJson(result.getResponse().getContentAsString(), DigitalDocument.class);
//        assertEquals(expected.toString(), actual.toString());
//    }
}
