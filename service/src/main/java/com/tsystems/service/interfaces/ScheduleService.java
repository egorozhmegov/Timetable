package com.tsystems.service.interfaces;

import com.tsystems.model.RailWayStation;
import com.tsystems.service.util.ScheduleWrapper;
import javax.ejb.Local;

@Local
public interface ScheduleService {

    /**
     * Return arrival and departure schedule by station title.
     *
     * @param station RailWayStation
     * @return ScheduleWrapper
     */
    ScheduleWrapper getSchedule(RailWayStation station);
}
