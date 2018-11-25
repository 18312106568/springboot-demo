/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.template;

import com.google.gson.Gson;
import com.mrb.springboot.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        System.out.println(gson.toJson(bean));
        
//        try {
//            Map<String,Object> root = new HashMap();
//            Template temp = cfg.getTemplate("gcmall_web/service.ftl");
//            root.put("bean",bean);
//            temp.process(root, new FileWriter(new File(String.format("F:\\tmp\\%s.java", className))));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        
        
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
