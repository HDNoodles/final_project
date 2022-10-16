package com.example.applicationservice.controller;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.domain.response.AllApplicationWorkFlowResponse;
import com.example.applicationservice.domain.response.ApplicationWorkFlowResponse;
import com.example.applicationservice.domain.response.ResponseStatus;
import com.example.applicationservice.service.ApplicationWorkFlowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("applicationWorkFlow")
public class ApplicationWorkFlowController {

    private ApplicationWorkFlowService applicationWorkFlowService;

    public ApplicationWorkFlowController(ApplicationWorkFlowService applicationWorkFlowService) {
        this.applicationWorkFlowService = applicationWorkFlowService;
    }

    @GetMapping
    public AllApplicationWorkFlowResponse getAllApplicationWorkFlow(){
        return AllApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Returning all ApplicationWorkFlow")
                                .build()
                )
                .applicationWorkFlowList(applicationWorkFlowService.getAllapplicationWorkFlow())
                .build();
    }

    @GetMapping("/{id}")
    public ApplicationWorkFlowResponse getApplicationWorkFlowById(@PathVariable Integer id) {
        Optional<ApplicationWorkFlow> optional = applicationWorkFlowService.getAllapplicationWorkFlow().stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        if (optional.isPresent()) {
            return ApplicationWorkFlowResponse.builder()
                    .responseStatus(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Success! ApplicationWorkFlow was found")
                                    .build()
                    )
                    .applicationWorkFlow(optional.get())
                    .build();
        } else {
            return ApplicationWorkFlowResponse.builder()
                    .responseStatus(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Oops, ApplicationWorkFlow not found")
                                    .build()
                    )
                    .build();
        }
    }

}