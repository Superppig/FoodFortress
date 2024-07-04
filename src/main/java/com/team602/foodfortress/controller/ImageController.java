package com.team602.foodfortress.controller;

import com.team602.foodfortress.entity.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("image")
public class ImageController {
    @PostMapping("/upload")
    public JsonResult uploadImages(@RequestParam(value = "file")MultipartFile file) throws IOException{
        //储存图片的地址
        String folder = "D:/temp";
        //文件夹
        File imgFolder = new File(folder);
        // 获取文件名
        String fname = file.getOriginalFilename();
        // 获取文件后缀
        String ext = "." + fname.substring(fname.lastIndexOf(".")+1);
        // 获取时间字符串
        String dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        // 生成新的文件名
        String newFileName = dateTimeFormatter + UUID.randomUUID().toString().replaceAll("-","") + ext;
        // 文件在本机的全路径
        File filePath = new File(imgFolder, newFileName);
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try{
            file.transferTo(filePath);
            // 返回文件名
            return new JsonResult(true,(Object)filePath.getName(),"上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return new JsonResult(false,"上传失败");
        }
    }
}
