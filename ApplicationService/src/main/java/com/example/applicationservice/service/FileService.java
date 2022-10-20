package com.example.applicationservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.domain.response.DigitalDocumentResponse;
import com.example.applicationservice.domain.response.DocumentInfoResponse;
import com.example.applicationservice.domain.response.ResponseStatus;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private AmazonS3 amazonS3;

//    @Value("${application.bucket.name}")
//    private String bucketName;

    private static final Logger LOG = LoggerFactory.getLogger(FileService.class);


    public List<Bucket> listBuckets(){
        return amazonS3.listBuckets();
    }

    public DigitalDocument uploadObject(String bucketName, String fileName, MultipartFile file) throws IOException{
        String tempFileName = UUID.randomUUID() + file.getName();
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + tempFileName);
        file.transferTo(tempFile); // Convert multipart file to File
        amazonS3.putObject(bucketName, fileName, tempFile); // Upload file
        tempFile.deleteOnExit(); //delete temp file

//        File tempFile = convertMultiPartFileToFile(file);
//        String tempFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        amazonS3.putObject(new PutObjectRequest(bucketName, tempFileName, tempFile));
//        tempFile.delete();

        return DigitalDocument.builder()
                .title(fileName)
                .path(String.valueOf(amazonS3.getUrl(bucketName, fileName)))
                .type(file.getContentType())
                .description(file.toString())
                .build();
    }

//    private File convertMultiPartFileToFile(MultipartFile file) {
//        File convertedFile = new File(file.getOriginalFilename());
//        try (FileOutputStream fos = new FileOutputStream(convertedFile)){
//            fos.write(file.getBytes());
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        return convertedFile;
//    }

    public void downloadObject(String bucketName, String objectName){
        S3Object s3object = amazonS3.getObject(bucketName, objectName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File("." + File.separator + objectName));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public DocumentInfoResponse getDocumentInfo(String bucketName, String fileName) {
        String  url = String.valueOf(amazonS3.getUrl(bucketName, fileName));
        return DocumentInfoResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Document information")
                                .build()
                )
                .path(url)
                .fileName(fileName)
                .lastModificationDate(amazonS3.getObject(bucketName, fileName).getObjectMetadata().getLastModified())
                .build();
    }
}
