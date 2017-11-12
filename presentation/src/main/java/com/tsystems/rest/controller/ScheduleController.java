package com.tsystems.rest.controller;

import com.tsystems.model.RailWayStation;
import com.tsystems.service.interfaces.RailWayStationService;
import com.tsystems.service.interfaces.ScheduleService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleController {

    @EJB
    private RailWayStationService railWayStationService;

    @GET
    @Path("/stations")
    public Response getStations() {

        return Response.status(200).entity(railWayStationService.getAllStations()).build();
    }

    @EJB
    private ScheduleService scheduleService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getSchedule() {

        return Response.status(200).entity(scheduleService.getSchedule(new RailWayStation("St.Petersburg"))).build();
    }
}
