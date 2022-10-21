package com.example.applicationservice.service;

import com.example.applicationservice.dao.DigitalDocumentDao;
import com.example.applicationservice.domain.DigitalDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DigitalDocumentServiceTest {

    @Mock
    private DigitalDocumentDao digitalDocumentDao;

    @InjectMocks
    private DigitalDocumentService digitalDocumentService;

    @Test
    void test_getAllDigitalDocument_success() {
        List<DigitalDocument> expected = new ArrayList<>();
        expected.add(new DigitalDocument(999, "OPT", "yes", "path1", "test1", "test1"));
        expected.add(new DigitalDocument(998, "OPT", "no", "path2",  "test2", "test2"));
        expected.add(new DigitalDocument(997, "OPT", "yes", "path3",  "test3", "test3"));
        Mockito.when(digitalDocumentDao.getAll()).thenReturn(expected);
        assertEquals(expected, digitalDocumentService.getAllDigitalDocument());
    }

    @Test
    void test_getAllDigitalDocument_successWhenEmpty() {
        List<DigitalDocument> expected = new ArrayList<>();
        Mockito.when(digitalDocumentDao.getAll()).thenReturn(expected);
        assertEquals(expected, digitalDocumentService.getAllDigitalDocument());
    }

    @Test
    void test_createNewApplicationWorkFlow_success(){
        DigitalDocument expected = new DigitalDocument(999, "OPT", "yes", "path1", "test1", "test1");
        DigitalDocument actual = expected;
        digitalDocumentService.createNewDigitalDocument(expected);
        digitalDocumentService.createNewDigitalDocument(actual);
        Mockito.verify(digitalDocumentDao, times(2)).add(expected);
        assertEquals(expected, actual);
    }

}
