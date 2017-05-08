package com.karash.services;

import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;

public interface ProblemService {
    VehicleRoutingProblemSolution getSolution(String pojo);
}
