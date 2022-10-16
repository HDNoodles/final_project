package com.example.applicationservice.dao;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("applicationDao")
public class ApplicationWorkFlowDao extends AbstractHibernateDao<ApplicationWorkFlow>{

    public ApplicationWorkFlowDao(){
        setClazz(ApplicationWorkFlow.class);
    }


    public List<ApplicationWorkFlow> getAllApplicationWorkFlow() {
        List<ApplicationWorkFlow> applicationWorkFlowList = this.getAll();
        return applicationWorkFlowList.stream().collect(Collectors.toList());
    }
}
