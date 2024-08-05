package com.mulei.blisscart.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class AWSService {


    private final S3Client s3Client;
    private final String bucketName;

 
    public AWSService(@Value("${aws.access.id}") String accessKeyId,
                     @Value("${aws.secret.key}") String secretKey,
                     @Value("${aws.region}") String region,
                     @Value("${amazon.s3.bucket-name}") String bucketName) {

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretKey);
        this.s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.of(region))
                .build();

        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try (InputStream inputStream = file.getInputStream()) {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()));

            return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(key)).toString();
        } catch (S3Exception e) {
            throw new IOException("Error uploading file to S3", e);
        }
    }

    public  Boolean deleteFile(String url){

        try{



            DeleteObjectRequest deleteObjectRequest=  DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(url).build();

           DeleteObjectResponse response = s3Client.deleteObject(deleteObjectRequest);
        //    System.out.println("a");
           // logger.log(Level.parse("INFO"), "response.toString()");
            System.out.println("response");
             System.out.println(response.deleteMarker());
           // logger.log(Level.parse("INFO"), response.toString());
           return response.deleteMarker();


        }catch(S3Exception e){
            return false;
        }
    }

}
