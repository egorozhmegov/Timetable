package com.tsystems.dao.implementation;

import com.tsystems.dao.interfaces.ScheduleDao;
import com.tsystems.model.Schedule;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {

    @PersistenceContext(unitName = "RailwayDS")
    private EntityManager entityManager;

    /**
     * Get station departure schedule by id.
     *
     * @param stationId long
     * @param weekDay int
     * @return List<Schedule>
     */
    @Override
    public List<Schedule> getStationDepartSchedule(long stationId, int weekDay){
        String sqlQuery =
                "SELECT s FROM Schedule AS s " +
                        "WHERE s.station.id = " + stationId + " " +
                        "AND s.departureDay = " + weekDay + " " +
                        "ORDER BY s.departureTime";
        Query query = entityManager.createQuery(sqlQuery);
        try{
            return query.getResultList();
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Get station arrival schedule by id.
     *
     * @param stationId long
     * @param weekDay int
     * @return List<Schedule>
     */
    @Override
    public List<Schedule> getStationArriveSchedule(long stationId, int weekDay){
        String sqlQuery =
                "SELECT s FROM Schedule AS s " +
                        "WHERE s.station.id = " + stationId + " " +
                        "AND s.arrivalDay = " + weekDay + " " +
                        "ORDER BY s.arrivalTime";
        Query query = entityManager.createQuery(sqlQuery);
        try{
            return query.getResultList();
        } catch (Exception e){
            return null;
        }
    }
}
