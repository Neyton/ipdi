package com.karash.controllers;

import com.karash.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Neyton on 05.2017.
 */

@Controller
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ProblemService problemService;

    @PostMapping
    public Object getData(@RequestBody String data) {
        return this.problemService.getSolution(data);
    }
}
