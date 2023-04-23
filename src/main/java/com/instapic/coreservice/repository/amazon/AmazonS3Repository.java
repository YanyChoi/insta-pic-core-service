package com.instapic.coreservice.repository.amazon;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.instapic.coreservice.repository.media.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@PropertySource("aws.yaml")
@Slf4j
public class AmazonS3Repository {

    private final AmazonS3Client amazonS3Client;

    @Value("${BUCKET_NAME}")
    private String bucketName;

    public String uploadObject(MultipartFile file, FileType fileType) throws IOException {
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + ext;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        InputStream inputStream = file.getInputStream();
        amazonS3Client.putObject(bucketName + "/" + fileType, fileName, inputStream, objectMetadata);
        log.info("S3 Storing " + fileName + " at " + bucketName + "/" + fileType);
        return fileName;
    }

    public void deleteObject(FileType fileType, String objectName) {
        amazonS3Client.deleteObject(bucketName + "/" + fileType, objectName);
    }

}
