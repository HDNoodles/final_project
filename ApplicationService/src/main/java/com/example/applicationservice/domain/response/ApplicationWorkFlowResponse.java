package com.example.applicationservice.domain.response;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationWorkFlowResponse {
    private ResponseStatus responseStatus;
    private ApplicationWorkFlow applicationWorkFlow;
}
