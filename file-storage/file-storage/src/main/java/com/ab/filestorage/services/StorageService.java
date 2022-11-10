package com.ab.filestorage.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file){

        File fileObj = ConvertMultipartFiletoFile(file);
        String fileName = System.currentTimeMillis()+"-"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;

    }

    public byte[] downloadFile(String fileName)  {
     S3Object s3Object= s3Client.getObject(bucketName,fileName);
     S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
     try {
        byte[] content= IOUtils.toByteArray(s3ObjectInputStream);
        return content;
     }catch(IOException e){
         System.out.println(e);
     }
     return null;
    }

    public String deleteFile(String fileName){
        s3Client.deleteObject(bucketName,fileName);
        return fileName+" removed....";
    }

    public File ConvertMultipartFiletoFile(MultipartFile file) {

        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos =new FileOutputStream((convertedFile))){
            fos.write(file.getBytes());

        } catch (IOException e) {
            System.out.println(e);
        }
        return convertedFile;
    }
}
