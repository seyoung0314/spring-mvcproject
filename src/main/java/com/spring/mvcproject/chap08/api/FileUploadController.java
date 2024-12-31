package com.spring.mvcproject.chap08.api;

import com.spring.mvcproject.chap08.config.FileUploadConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v8")
@Slf4j
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadConfig fileUploadConfig;

    // 첨부파일 업로드 (단일 파일)
    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadSingleFile(
            @RequestParam("file") MultipartFile file
//            MultipartFile file
    ) {
        log.info("uploaded file name : {}", file.getOriginalFilename());
        log.info("uploaded file type : {}", file.getContentType());

        //저장 파일 경로 생성
        String fileName = file.getOriginalFilename();
        String filePath = fileUploadConfig.getLocation();
        File uploadedFile = new File(filePath + fileName);

        //해당 위치에 파일 저장
        try {
            file.transferTo(uploadedFile);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.internalServerError().body("실패");
        }

        return ResponseEntity.ok().body("ok");
    }

    // 첨부파일 업로드 (다중 파일)
    @PostMapping("/upload-files")
    public ResponseEntity<?> uploadMultiFile(
            @RequestParam("fileList") List<MultipartFile> fileList
    ) {
        for (MultipartFile file : fileList) {
            System.out.println("====================================");
            log.info("uploaded file name : {}", file.getOriginalFilename());
            log.info("uploaded file type : {}", file.getContentType());
            //저장 파일 경로 생성
            String fileName = file.getOriginalFilename();
            String filePath = fileUploadConfig.getLocation();
            File uploadedFile = new File(filePath + fileName);

            //해당 위치에 파일 저장
            try {
                file.transferTo(uploadedFile);
            } catch (IOException e) {
//            throw new RuntimeException(e);
                return ResponseEntity.internalServerError().body("실패");
            }
        }

        return ResponseEntity.ok().body("multi ok");
    }
}
