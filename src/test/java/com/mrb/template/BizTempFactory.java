/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.template;

import com.google.gson.Gson;
import com.mrb.springboot.demo.DemoApplication;
import com.mrb.springboot.demo.constant.ScType;
import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author MRB
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class BizTempFactory {
    @Autowired
    Configuration cfg;
    
    @Test
    public void createTemplate() {
        Gson gson = new Gson();
        String packageName = "category.whitelist";
        String className = "CategoryWhitelist";
        String classComment = "验号白名单";
        Field field1 = new Field("Integer","bizType",false);
        Field field2 = new Field("Long","cid",true);
        List<Field> fieldList = new ArrayList();
        fieldList.add(field1);
        fieldList.add(field2);
        TempClass bean = new TempClass();
        bean.setPackageName(packageName);
        bean.setClassName(className);
        bean.setClassComment(classComment);
        bean.setSearchList(fieldList);
        bean.setAddList(fieldList);
        bean.setDeleteList(fieldList);
        
        //System.out.println(gson.toJson(bean));
        for(ScType scType : ScType.values()){
            try {
                Map<String,Object> root = new HashMap();
                Template temp = cfg.getTemplate(scType.getTempName());
                root.put("bean",bean);
                File targetFile = new File(String.format(scType.getTargetName(), className));
                if(!targetFile.exists()){
                    targetFile.createNewFile();
                }
                temp.process(root, new FileWriter(targetFile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    
    @Test
    public void ZipFile(){
        String className = "CategoryWhitelist";
        try{
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(new File("/gcmall/sc.zip")));
            for(ScType scType : ScType.values()){
                 File inFile = new File(String.format(scType.getTargetName(), className));
                doZip(inFile, out, inFile.getParent());
            }
            out.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            
        }
        
    }
    
    private static void doZip(File inFile, ZipOutputStream out, String dir) throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);
        
        int len = 0 ;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }
    
    @Data
    public static class TempClass{
         private String packageName;
         private String className;
         private String classComment;
         private List<Field> searchList;
         private List<Field> addList;
         private List<Field> deleteList;
    }
    
    @Data
    @AllArgsConstructor
    public static class Field{
        private String type;
        private String name;
        private Boolean isMust;
    }
    
}
