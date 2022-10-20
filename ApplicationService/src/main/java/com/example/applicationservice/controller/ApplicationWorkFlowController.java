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
                .applicationWorkFlowList(applicationWorkFlowService.getAllApplicationWorkFlow())
                .build();
    }

    @GetMapping("/{applicationWorkFlow_id}")
    public ApplicationWorkFlowResponse getApplicationWorkFlowById(@PathVariable Integer applicationWorkFlow_id) {
        Optional<ApplicationWorkFlow> optional = applicationWorkFlowService.getAllApplicationWorkFlow().stream()
                .filter(a -> a.getApplicationWorkFlow_id() == applicationWorkFlow_id)
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
    @PostMapping("/update")
    public ApplicationWorkFlowResponse updateStatusApplicationWorkflowById(@RequestBody ApplicationWorkFlow awf){
        applicationWorkFlowService.updateStatusApplicationWorkflowById(awf.getApplicationWorkFlow_id(), awf);
        return ApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! ApplicationWorkFlow status changed and comment added")
                                .build()
                )
                .applicationWorkFlow(getApplicationWorkFlowById(awf.getApplicationWorkFlow_id()).getApplicationWorkFlow())
                .build();
    }

    //getMapping getApplicationWorkFlowByEmployeeId
    @GetMapping("/employeeId/{employeeId}")
    public ApplicationWorkFlowResponse getApplicationWorkFlowByEmployeeId(@PathVariable Integer employeeId) {
        Optional<ApplicationWorkFlow> optional = applicationWorkFlowService.getAllApplicationWorkFlow().stream()
                .filter(a -> a.getEmployeeId() == employeeId)
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
