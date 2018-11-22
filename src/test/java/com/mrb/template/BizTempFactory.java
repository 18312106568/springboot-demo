/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrb.template;

import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
/**
 *
 * @author MRB
 */
public class BizTempFactory {
    @Autowired
    Configuration cfg;
    
    public void createTemplate(){
    }
    
}
