package com.karash.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karash.DTO.ProblemDTO;
import com.karash.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Neyton on 05.2017.
 */

@Controller
@RequestMapping("/api")
public class MainController {
    @Autowired
    private ProblemService problemService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping
    @ResponseBody
    public String getData(@RequestBody ProblemDTO data) {
        try {
            return mapper.writeValueAsString(this.problemService.getSolution(data));
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
