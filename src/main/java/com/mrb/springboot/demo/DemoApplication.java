package com.mrb.springboot.demo;


import com.mrb.springboot.demo.respository.UserRespotory;
import javax.persistence.Column;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;
/**
 *
 * @author MRB
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String args[]){
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
