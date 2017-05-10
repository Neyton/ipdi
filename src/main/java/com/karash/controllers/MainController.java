package com.karash.controllers;

import com.karash.DTO.ProblemDTO;
import com.karash.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Neyton on 05.2017.
 */

@Controller
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ProblemService problemService;

    @PostMapping
    @ResponseBody
    public Object getData(@RequestBody ProblemDTO data) {
        return this.problemService.getSolution(data);
    }
}
