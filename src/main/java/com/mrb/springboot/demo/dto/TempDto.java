package com.mrb.springboot.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class TempDto {
    private String packageName;
    private String className;
    private String classComment;
    private List<Field> searchList;
    private List<Field> addList;
    private List<Field> deleteList;
}
