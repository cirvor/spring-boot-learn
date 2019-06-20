package com.cirvor.learn.controller;

import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @GetMapping
    public ResultData index() {
        return ResultUtils.success("file index");
    }

    /**
     * 单文件上传
     *
     * @param file 文件对象
     * @return  ResultData
     * @throws IOException IO异常
     */
    @PostMapping("single-upload")
    public ResultData uploadSingle(@RequestParam(value = "file") MultipartFile file) throws IOException {
        logger.info("[文件类型] - [{}]", file.getContentType());
        logger.info("[文件名称] - [{}]", file.getOriginalFilename());
        logger.info("[文件大小] - [{}]", file.getSize());

        //获取相对路径
        File path = new File(ResourceUtils.getURL("").getPath());
        if(!path.exists()) path = new File("");
        file.transferTo(new File(path.getAbsolutePath()+ "/upload/" + file.getOriginalFilename()));

        Map<String, String> result = new HashMap<>(8);
        result.put("contentType", file.getContentType());
        result.put("fileName", file.getOriginalFilename());
        result.put("fileSize", Long.toString(file.getSize()));
        return ResultUtils.success(result);
    }

    /**
     * 多文件上传
     *
     * @param files 文件对象数组
     * @return ResultData
     * @throws IOException IO异常
     */
    @PostMapping("multi-upload")
    public ResultData uploadMulti(@RequestParam("file") MultipartFile[] files) throws IOException {
        logger.error(Arrays.toString(files));
        if (files == null || files.length == 0) {
            return null;
        }
        List<Map<String, String>> results = new ArrayList<>();
        File path = new File(ResourceUtils.getURL("").getPath());
        if(!path.exists()) path = new File("");
        String uploadPath = path.getAbsolutePath() + "/upload/";
        for (MultipartFile file : files) {
            file.transferTo(new File(uploadPath + file.getOriginalFilename()));
            Map<String, String> map = new HashMap<>(16);
            map.put("contentType", file.getContentType());
            map.put("fileName", file.getOriginalFilename());
            map.put("fileSize", file.getSize() + "");
            results.add(map);
        }
        return ResultUtils.success(results);
    }
}
