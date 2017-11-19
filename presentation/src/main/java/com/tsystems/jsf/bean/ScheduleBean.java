package com.tsystems.jsf.bean;

import com.tsystems.model.RailWayStation;
import com.tsystems.service.interfaces.RailWayStationService;
import com.tsystems.service.interfaces.ScheduleService;
import com.tsystems.service.util.ScheduleWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "scheduleBean", eager = true)
@RequestScoped
public class ScheduleBean {

    @EJB
    private RailWayStationService railWayStationService;

    @EJB
    private ScheduleService scheduleService;

    private List<RailWayStation> stations;

    private ScheduleWrapper schedule;

    private RailWayStation station;

    @PostConstruct
    public void init() {
        stations = railWayStationService.getAllStations();
        schedule = scheduleService.getSchedule(stations.get(0));
    }

    public List<RailWayStation> getStations() {
        return stations;
    }

    public ScheduleWrapper getSchedule() {
        return schedule;
    }

    public RailWayStation getStation() {
        return station;
    }

    public void setStation(RailWayStation station) {
        this.station = station;
    }
}
