package com.ab.filestorage.controllers;

import com.ab.filestorage.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
           return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName){
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type" ,"application/oct-stream")
                .header("Content-disposition" ,"attachment;filename=\""+fileName+"\"")
                .body(resource);
    }
    @DeleteMapping ("/remove/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(storageService.deleteFile(fileName),HttpStatus.OK);
    }

}
