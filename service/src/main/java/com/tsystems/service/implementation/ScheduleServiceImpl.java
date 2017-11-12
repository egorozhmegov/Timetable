package com.tsystems.service.implementation;

import com.tsystems.dao.interfaces.ScheduleDao;
import com.tsystems.model.RailWayStation;
import com.tsystems.service.exception.ScheduleServiceInvalidDataException;
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

    /**
     * Return arrival and departure schedule by station title.
     *
     * @param station RailWayStation
     * @return List<ScheduleWrapper>
     */
    @Override
    public ScheduleWrapper getSchedule(RailWayStation station) {
        if(station == null
                || station.getTitle().isEmpty()){
            LOG.error(String.format("Invalid station title: %s.", station));
            throw new ScheduleServiceInvalidDataException(String.format("Invalid station title: %s.", station));
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
