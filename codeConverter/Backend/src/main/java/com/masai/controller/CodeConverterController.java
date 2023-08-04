package com.masai.controller;

import com.masai.service.CodeConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CodeConverterController {

    @Autowired
    private CodeConverterService codeConverterService;

    @PostMapping("/convert")
    public ResponseEntity<String> convertCode(@RequestBody String code) {
        String convertedCode = codeConverterService.convertCode(code + " please convert this code without using any comments and extra information!");
        return ResponseEntity.ok(convertedCode);
    }
}
