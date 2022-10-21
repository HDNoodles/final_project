package com.example.applicationservice.dao;

import com.example.applicationservice.domain.DigitalDocument;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("digitalDocumentDao")
public class DigitalDocumentDao extends AbstractHibernateDao<DigitalDocument>{

    public DigitalDocumentDao(){
        setClazz(DigitalDocument.class);
    }

//    public List<DigitalDocument> getDigitalDocumentById(Integer digitalDocument_id) {
//        List<DigitalDocument> list = new ArrayList<>();
//        list.add(findById(digitalDocument_id));
//        return list;
//    }
}
