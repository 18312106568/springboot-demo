/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.springboot.demo.constant;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import lombok.Data;

/**
 *
 * @author MRB
 */
public enum ScType {
    
    GCMALL_API_ISERVICE("gcmall_api/IService.ftl","gcmall_api/I%sService.java"),
    GCMALL_WEB_CLASS("gcmall_web/classes.ftl","gcmall_web/classes-%s.xml"),
    GCMALL_WEB_CLUSTER("gcmall_web/cluster-config.ftl","gcmall_web/cluster-config-%s.xml"),
    GCMALL_WEB_SERVICE("gcmall_web/service.ftl","gcmall_web/%sService.java"),
    GCMALL_WEB_SERVICEIMPL("gcmall_web/serviceImpl.ftl","gcmall_web/%sServiceImpl.java")
    ;
        
    private String tempName;
    
    private String targetName;
    
    private ScType(String tempName,String targetName){
        this.tempName = tempName;
        //转换为classpath下的路径
        try {
            this.targetName =  ResourceUtils.getURL("classpath:gcmall/").getPath()+"\\"+targetName;
        } catch (FileNotFoundException e) {
            this.targetName = targetName;
            e.printStackTrace();
        }
    }

    public String getTempName() {
        return tempName;
    }

    public String getTargetName() {
        return targetName;
    }
    
    
}
