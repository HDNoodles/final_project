package com.example.applicationservice.domain.response;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.domain.DigitalDocument;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DigitalDocumentResponse {
    private ResponseStatus responseStatus;
    private DigitalDocument digitalDocument;
}
