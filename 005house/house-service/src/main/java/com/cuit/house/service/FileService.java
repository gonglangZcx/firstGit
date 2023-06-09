package com.cuit.house.service;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Value("${file.path}")
    private String filePath;
/*
把上传的图片存储到对应的图片服务器。
List<MultipartFile> files 这个参数为什么是一个集合？
因为你上传图片有可能有一张图片，也有可能会上传多张图片
 List<String> 返回值是用于存储到数据库的相对路径的地址.
 */
    public List<String> getImgPath(List<MultipartFile> files){
        ArrayList<String> paths = Lists.newArrayList();
        files.forEach(file ->{
            //把文件存储到本地服务器的地址
            File localFile= null;
            try {
                localFile = saveToLocal(file,filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath.replace("//","\\"));
            paths.add(path);
        });
        return paths;
    }

    private File saveToLocal(MultipartFile file, String filePath) throws IOException {
            File newFile=new File(filePath+"/"+ Instant.now().getEpochSecond()+"/"+file.getOriginalFilename());
            if(!newFile.exists()){
                newFile.getParentFile().mkdirs();
                newFile.createNewFile();
            }
            //复制 利用cava去存入文件
            Files.write(file.getBytes(), newFile);
        return newFile;

    }
}
