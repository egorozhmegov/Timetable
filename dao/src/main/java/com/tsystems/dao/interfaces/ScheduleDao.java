package com.tsystems.dao.interfaces;

import com.tsystems.model.Schedule;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ScheduleDao {

    /**
     * Get station departure schedule by id.
     *
     * @param stationId long
     * @param weekDay int
     * @return List<Schedule>
     */
    List<Schedule> getStationDepartSchedule(long stationId, int weekDay);

    /**
     * Get station arrival schedule by id.
     *
     * @param stationId long
     * @param weekDay int
     * @return List<Schedule>
     */
    List<Schedule> getStationArriveSchedule(long stationId, int weekDay);
}
