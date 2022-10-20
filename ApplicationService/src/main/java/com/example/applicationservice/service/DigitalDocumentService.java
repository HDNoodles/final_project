package com.example.applicationservice.service;

import com.example.applicationservice.dao.DigitalDocumentDao;
import com.example.applicationservice.domain.DigitalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalDocumentService {

    public DigitalDocumentDao digitalDocumentDao;

    @Autowired
    @Qualifier("digitalDocumentDao")
    public void setDigitalDocumentDao(DigitalDocumentDao digitalDocumentDao) {
        this.digitalDocumentDao = digitalDocumentDao;
    }

    public List<DigitalDocument> getAllDigitalDocument() {
        return digitalDocumentDao.getAll();
    }

    public void createNewDigitalDocument(DigitalDocument digitalDocument) {
        digitalDocumentDao.add(digitalDocument);
    }
}
