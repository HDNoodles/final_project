package com.example.applicationservice.controller;

import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.domain.response.ApplicationWorkFlowResponse;
import com.example.applicationservice.domain.response.DigitalDocumentResponse;
import com.example.applicationservice.domain.response.ResponseStatus;
import com.example.applicationservice.service.DigitalDocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("digitalDocument")
public class DigitalDocumentController {

    private DigitalDocumentService digitalDocumentService;

    public DigitalDocumentController(DigitalDocumentService digitalDocumentService) {
        this.digitalDocumentService = digitalDocumentService;
    }

    @GetMapping("/{id}")
    public DigitalDocumentResponse getApplicationWorkFlowById(@PathVariable Integer id) {
        Optional<DigitalDocument> optional = digitalDocumentService.getAllDigitalDocument().stream()
                .filter(d -> d.getId() == id)
                .findFirst();
        if (optional.isPresent()) {
            return DigitalDocumentResponse.builder()
                    .responseStatus(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Success! DigitalDocument was found")
                                    .build()
                    )
                    .digitalDocument(optional.get())
                    .build();
        } else {
            return DigitalDocumentResponse.builder()
                    .responseStatus(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Oops, DigitalDocument not found")
                                    .build()
                    )
                    .build();
        }
    }

}
