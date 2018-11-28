package com.mrb.springboot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field{
    private String type;
    private String name;
    private Boolean isMust;
}
