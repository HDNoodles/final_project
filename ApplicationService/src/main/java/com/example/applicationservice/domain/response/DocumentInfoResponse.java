package com.example.applicationservice.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class DocumentInfoResponse {
    private ResponseStatus responseStatus;
    private String path;
    private String fileName;
    private Date lastModificationDate;

}