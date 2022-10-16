package com.example.applicationservice.service;

import com.example.applicationservice.dao.ApplicationWorkFlowDao;
import com.example.applicationservice.domain.ApplicationWorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationWorkFlowService {

    private ApplicationWorkFlowDao applicationWorkFlowDao;

    @Autowired
    @Qualifier("applicationDao")
    public void setApplicationWorkFlowDao(ApplicationWorkFlowDao applicationWorkFlowDao) {
        this.applicationWorkFlowDao = applicationWorkFlowDao;
    }

    public List<ApplicationWorkFlow> getAllapplicationWorkFlow() {
//        return applicationWorkFlowDao.getAll();
        return applicationWorkFlowDao.getAllApplicationWorkFlow();
    }
}
