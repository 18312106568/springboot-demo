package com.mrb.springboot.demo;


import com.google.gson.Gson;

import com.mrb.springboot.demo.dto.TempDto;
import com.mrb.springboot.demo.respository.UserRespotory;
import com.mrb.springboot.demo.service.GeneratorService;

import javax.persistence.Column;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author MRB
 */
@SpringBootApplication
public class DemoApplication {
    public static void  main (String args[]){
        SpringApplication.run(DemoApplication.class, args);
    }


    @RestController
    static class DemoController{
        @Autowired
        UserRespotory userRespotory;

        @Autowired
        StringRedisTemplate stringRedisTemplate;

        @RequestMapping("/demo")
        public String helloWord(){

            return userRespotory.getOne(1).toString();
        }

        @RequestMapping("/redis-get")
        public String redisCache(){
            return stringRedisTemplate.opsForValue().get("1");
        }
    }

    @Slf4j
    @Controller
    static class DefaultController{

        @Autowired
        GeneratorService generatorService;

        private Gson gson = new Gson();

        @GetMapping("/default")
        public String index(){
            System.out.println("欢迎来到动态页面！");
            return "default";
        }

        @RequestMapping("/downloadCode")
        public void downloadCode(HttpServletResponse response,String entity){

            log.info("start generator code {}",entity);
            TempDto dto = gson.fromJson(entity,TempDto.class);
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=gcmall.zip" );

                ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
                generatorService.generateCode(dto,out);
                out.finish();
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }


    @Data
    @Entity
    @Table(name = "tb_user")
    public  static class User{
        @Id
        @Column(name="id")
        private Integer id;
        @Column(name="name")
        private String name;

    }
}
