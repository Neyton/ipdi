package com.karash.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
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

    @PostMapping
    @ResponseBody
    public String getData(@RequestBody ProblemDTO data) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(double.class, new MyDoubleSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(this.problemService.getSolution(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
