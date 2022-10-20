package com.example.applicationservice.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.domain.response.DocumentInfoResponse;
import com.example.applicationservice.service.DigitalDocumentService;
import com.example.applicationservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    private DigitalDocumentService digitalDocumentService;

    public FileController(FileService fileService, DigitalDocumentService digitalDocumentService) {
        this.fileService = fileService;
        this.digitalDocumentService = digitalDocumentService;
    }

    @GetMapping("file/buckets")
    public List<String> listBuckets() {
        System.out.println("calling listbuckets");
        List<Bucket> buckets = fileService.listBuckets();
        System.out.println("called listbuckets");
        List<String> bucketNames = buckets.stream().map(Bucket::getName).collect(Collectors.toList());
        System.out.println("returning listbuckets");
        return bucketNames;
    }

    @GetMapping("file/{bucketName}/{fileName}")
    public File downloadFile(@PathVariable String bucketName, @PathVariable String fileName) {
        fileService.downloadObject(bucketName, fileName);
        return new File("./" + fileName);
    }

    @PostMapping("file/{bucketName}")
    public DocumentInfoResponse uploadFile(@PathVariable String bucketName, @RequestParam String fileName, @RequestParam(value = "file") MultipartFile file) throws IOException {
        System.out.println("calling to upload");
        DigitalDocument digitalDocument = fileService.uploadObject(bucketName, fileName, file);
        digitalDocumentService.createNewDigitalDocument(digitalDocument);
        return fileService.getDocumentInfo(bucketName, fileName);
    }

    @GetMapping("file/info/{bucketName}/{fileName}")
    public DocumentInfoResponse getDocumentInfo(@PathVariable String bucketName, @PathVariable String fileName) {
        return fileService.getDocumentInfo(bucketName, fileName);
    }

}
