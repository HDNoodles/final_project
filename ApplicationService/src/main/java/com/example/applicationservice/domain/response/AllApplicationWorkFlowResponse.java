package com.example.applicationservice.domain.response;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AllApplicationWorkFlowResponse {

    private ResponseStatus responseStatus;
    private List<ApplicationWorkFlow> applicationWorkFlowList;
}
