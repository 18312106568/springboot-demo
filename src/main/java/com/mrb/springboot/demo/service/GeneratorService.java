package com.mrb.springboot.demo.service;

import com.mrb.springboot.demo.dto.TempDto;

import org.springframework.stereotype.Service;

import java.util.zip.ZipOutputStream;

public interface GeneratorService {
    void generateCode(TempDto dto, ZipOutputStream outputStream);
}
