package com.example.applicationservice.controller;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.domain.response.AllApplicationWorkFlowResponse;
import com.example.applicationservice.domain.response.ApplicationWorkFlowResponse;
import com.example.applicationservice.domain.response.ResponseStatus;
import com.example.applicationservice.service.ApplicationWorkFlowService;
import org.springframework.web.bind.annotation.*;

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
                .filter(a -> a.getApplicationWorkFlow_id() == id)
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

    //POST createNewApplicationWorkflow
    @PostMapping()
    public ApplicationWorkFlowResponse createNewApplicationWorkFlow(
        @RequestBody ApplicationWorkFlow applicationWorkFlow
    ){
        applicationWorkFlowService.createNewApplicationWorkFlow(applicationWorkFlow);

        return ApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("New ApplicationWorkFlow created")
                                .build()
                )
                .applicationWorkFlow(applicationWorkFlow)
                .build();
    }


    //PATCH updateStatusApplicationWorkflow
    @PatchMapping("/{id}")
    public ApplicationWorkFlowResponse updateStatusApplicationWorkflow(@PathVariable Integer id, @RequestBody ApplicationWorkFlow awf){
        applicationWorkFlowService.updateUserStatus(id, awf);
        return ApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! ApplicationWorkFlow status changed and comment added")
                                .build()
                )
                .applicationWorkFlow(getApplicationWorkFlowById(id).getApplicationWorkFlow())
                .build();
    }
}
