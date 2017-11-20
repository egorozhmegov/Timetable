package com.tsystems.service.implementation;

import com.tsystems.dao.interfaces.ScheduleDao;
import com.tsystems.model.RailWayStation;
import com.tsystems.service.exception.RailwayStationNotFoundServiceException;
import com.tsystems.service.exception.ScheduleServiceInvalidDataException;
import com.tsystems.service.interfaces.RailWayStationService;
import com.tsystems.service.interfaces.ScheduleService;
import com.tsystems.service.util.ScheduleWrapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDate;

import com.tsystems.service.util.WeekDays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless(name = "scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    private final static Logger LOG = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @EJB
    private ScheduleDao scheduleDao;

    @EJB
    private RailWayStationService railWayStationService;

    /**
     * Return arrival and departure schedule by station title.
     *
     * @param stationTitle String
     * @return List<ScheduleWrapper>
     */
    @Override
    public ScheduleWrapper getSchedule(String stationTitle) {
        if(stationTitle == null
                || stationTitle.isEmpty()){
            LOG.error(String.format("Invalid station title: %s.", stationTitle));
            throw new ScheduleServiceInvalidDataException(String.format("Invalid station title: %s.", stationTitle));
        }

        RailWayStation station = railWayStationService.getStationByTitle(stationTitle);

        if (station == null){
            LOG.error(String.format("Station with title: %s not found.", stationTitle));
            throw new RailwayStationNotFoundServiceException(
                    String.format("Station with title: %s not found.", stationTitle));
        }

        ScheduleWrapper scheduleWrapper = new ScheduleWrapper();

        int weekDay = WeekDays
                .valueOf(LocalDate.now().getDayOfWeek().name()).ordinal() + 1;
        long stationId = station.getId();

        scheduleWrapper.setArrivalSchedule(scheduleDao.getStationArriveSchedule(stationId, 5));
        scheduleWrapper.setDepartureSchedule(scheduleDao.getStationDepartSchedule(stationId, 5));

        return scheduleWrapper;
    }
}
