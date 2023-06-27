package com.ohgiraffers.fileupload;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   Model model){

        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);

        String root = "C:\\app-file";
        String filePath = root + "/uploadFiles";

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs(); //루드경로 딱하나 폴더만들면 mkdir, 여러개의 폴더면 mkdirs
        }

        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);
        String savedName = UUID.randomUUID().toString().replaceAll("-", "") + ext; //문자열로 변경하기 위해 tostring
        //uuid 식별가능한 고유아이디 생성하기위한 유틸
        System.out.println("savedName = " + savedName);

        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 성공!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "result";
    }
}
