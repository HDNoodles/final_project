package com.example.applicationservice.dao;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public ApplicationWorkFlow getApplicationWorkFlowById(int id) {
        return findById(id);
    }

    public void updateUserStatus(Integer id, ApplicationWorkFlow awf) {
        ApplicationWorkFlow applicationWorkFlow = getApplicationWorkFlowById(id);
        applicationWorkFlow.setStatus(awf.getStatus());
        applicationWorkFlow.setComment(awf.getComment());
        // if not working try update() method below, or jdbc
    }

//    public void update(Integer id){
//        update(getApplicationWorkFlowById(id));
//    }
}
