package com.example.applicationservice.service;

import com.example.applicationservice.dao.ApplicationWorkFlowDao;
import com.example.applicationservice.domain.ApplicationWorkFlow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ApplicationWorkFlowServiceTest {

    @Mock
    private ApplicationWorkFlowDao applicationWorkFlowDao;

    @InjectMocks
    private ApplicationWorkFlowService applicationWorkFlowService;


    @Test
    void test_getAllApplicationWorkFlow_success() {
        List<ApplicationWorkFlow> expected = new ArrayList<>();
        expected.add(new ApplicationWorkFlow(999, "999", now(), now(), "test1", "test1"));
        expected.add(new ApplicationWorkFlow(998, "998", now(), now(), "test2", "test2"));
        expected.add(new ApplicationWorkFlow(997, "997", now(), now(), "test3", "test3"));
        Mockito.when(applicationWorkFlowDao.getAllApplicationWorkFlow()).thenReturn(expected);
        assertEquals(expected, applicationWorkFlowService.getAllApplicationWorkFlow());
    }



    @Test
    void test_getAllApplicationWorkFlow_successWhenEmpty() {
        List<ApplicationWorkFlow> expected = new ArrayList<>();
        Mockito.when(applicationWorkFlowDao.getAllApplicationWorkFlow()).thenReturn(expected);
        assertEquals(expected, applicationWorkFlowService.getAllApplicationWorkFlow());
    }

    @Test
    void test_getApplicationWorkFlowById_success(){
        ApplicationWorkFlow expected = new ApplicationWorkFlow(999, "999", now(), now(), "test1", "test1");
        Mockito.when(applicationWorkFlowDao.getApplicationWorkFlowById(999)).thenReturn(expected);
        assertEquals(expected, applicationWorkFlowDao.getApplicationWorkFlowById(999));
    }

//    Auto generate id and dates, cannot compare
//    @Test
//    void test_createNewApplicationWorkFlow_success(){
//        ApplicationWorkFlow expected = new ApplicationWorkFlow(999,"test1", "test1");
//        ApplicationWorkFlow actual = expected;
//        applicationWorkFlowService.createNewApplicationWorkFlow(expected);
//        applicationWorkFlowService.createNewApplicationWorkFlow(actual);
//        Mockito.verify(applicationWorkFlowService, times(2)).createNewApplicationWorkFlow(expected);
//        assertEquals(expected, actual);
//    }

}
