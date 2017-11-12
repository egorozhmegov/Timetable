package com.tsystems.rest.controller;

import com.tsystems.service.interfaces.RailWayStationService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/timetable/stations")
public class RailWayStationController {

    @EJB
    private RailWayStationService railWayStationService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getStations() {

        return Response.status(200).entity(railWayStationService.getAllStations()).build();
    }
}
