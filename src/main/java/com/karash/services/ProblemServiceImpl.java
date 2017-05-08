package com.karash.services;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.selector.SelectBest;
import com.graphhopper.jsprit.core.analysis.SolutionAnalyser;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.io.problem.VrpXMLReader;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.util.Collection;

@org.springframework.stereotype.Service
public class ProblemServiceImpl implements ProblemService {

    @Override
    public VehicleRoutingProblemSolution getSolution(String pojo) {
        JSONObject jsonObject = new JSONObject(pojo);

        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        System.out.println(org.json.XML.toString(jsonObject));
        new VrpXMLReader(vrpBuilder).read(new ByteArrayInputStream(org.json.XML.toString(jsonObject).getBytes()));
        final VehicleRoutingProblem vrp = vrpBuilder.build();
        VehicleRoutingAlgorithm vra = Jsprit.createAlgorithm(vrp);
        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
        VehicleRoutingProblemSolution solution = new SelectBest().selectSolution(solutions);
        SolutionAnalyser analyser = new SolutionAnalyser(vrp, solution, (from, to, departureTime, vehicle) -> vrp.getTransportCosts().getTransportCost(from, to, 0., null, null));

        System.out.println("tp_distance: " + analyser.getDistance());
        System.out.println("tp_time: " + analyser.getTransportTime());
        System.out.println("waiting: " + analyser.getWaitingTime());
        System.out.println("service: " + analyser.getServiceTime());
        System.out.println("#picks: " + analyser.getNumberOfPickups());
        System.out.println("#deliveries: " + analyser.getNumberOfDeliveries());
        return null;
    }


}
