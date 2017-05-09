package com.karash.services;

import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.karash.DTO.ProblemDTO;

public interface ProblemService {
    VehicleRoutingProblemSolution getSolution(ProblemDTO pojo);
}
