package com.example.applicationservice.dao;

import com.example.applicationservice.domain.DigitalDocument;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("digitalDocumentDao")
public class DigitalDocumentDao extends AbstractHibernateDao<DigitalDocument>{

    public DigitalDocumentDao(){
        setClazz(DigitalDocument.class);
    }
}
