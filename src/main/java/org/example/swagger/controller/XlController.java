package org.example.swagger.controller;

import lombok.RequiredArgsConstructor;
import org.example.swagger.service.XlService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class XlController {
    private final XlService xlService;

    @PostMapping(value = "/min", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> getMinFromFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("n") int n) {
        int result = xlService.min(file, n);
        return ResponseEntity.ok(result);
    }

}
