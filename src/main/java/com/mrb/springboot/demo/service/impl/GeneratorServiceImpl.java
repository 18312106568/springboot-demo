package com.mrb.springboot.demo.service.impl;

import com.google.gson.Gson;

import com.mrb.springboot.demo.constant.ScType;
import com.mrb.springboot.demo.dto.TempDto;
import com.mrb.springboot.demo.service.GeneratorService;
import com.mrb.springboot.demo.utils.ZipUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    Configuration cfg;

    public void generateCode(TempDto dto, ZipOutputStream out) {
        //创建目标文件.并压缩
        for(ScType scType : ScType.values()){
            try {
                Map<String,Object> root = new HashMap();
                Template temp = cfg.getTemplate(scType.getTempName());
                root.put("bean",dto);
                File targetFile =new File(String.format(scType.getTargetName(), dto.getClassName()));
                if(!targetFile.exists()){
                    targetFile.createNewFile();
                }
                temp.process(root, new FileWriter(targetFile));
                ZipUtils.doZip(targetFile, out, targetFile.getParentFile().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
