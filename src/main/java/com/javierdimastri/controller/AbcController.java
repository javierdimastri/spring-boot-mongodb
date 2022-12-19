package com.javierdimastri.controller;

import com.javierdimastri.model.Abc;
import com.javierdimastri.service.AbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abc")
public class AbcController {
    @Autowired
    private AbcService abcService;
    @GetMapping
    public ResponseEntity <List<Abc>> fetchAllAbc () {
        List<Abc> fetchedData = abcService.getAllAbc();
        return ResponseEntity.ok().body(fetchedData);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity createAbc(@RequestBody Abc payload) {
        Abc createdAbc = abcService.saveAbc(payload.getName(), payload.getDescription());
        return new ResponseEntity<>(createdAbc , HttpStatus.CREATED);
    }

}
