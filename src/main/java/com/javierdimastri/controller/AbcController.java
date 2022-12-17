package com.javierdimastri.controller;

import com.javierdimastri.model.Abc;
import com.javierdimastri.service.AbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AbcController {
    @Autowired
    private AbcService abcService;

    @GetMapping("/abc")
    public ResponseEntity <List<Abc>> fetchAllAbc () {
        List<Abc> fetchedData = abcService.getAllAbc();
        return ResponseEntity.ok().body(fetchedData);
    }
}
