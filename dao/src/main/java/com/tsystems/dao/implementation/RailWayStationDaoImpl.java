package com.tsystems.dao.implementation;

import com.tsystems.dao.interfaces.RailWayStationDao;
import com.tsystems.model.RailWayStation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "railWayStationDao")
public class RailWayStationDaoImpl implements RailWayStationDao{

    @PersistenceContext(unitName = "RailwayDS")
    private EntityManager entityManager;

    @Override
    public List<RailWayStation> getAllStations() {
        return entityManager.createQuery("SELECT r FROM RailWayStation r").getResultList();
    }

    /**
     * Get RailWayStation by title.
     *
     * @param title String.
     * @return RailWayStation.
     */
    @Override
    public RailWayStation getStationByTitle(String title) {
        try{
            String sqlQuery = String.format("SELECT r FROM RailWayStation AS r WHERE r.title = '%s'", title);
            Query query = entityManager.createQuery(sqlQuery);
            RailWayStation station = (RailWayStation) query.getSingleResult();
            return station;
        } catch (Throwable e){
            return null;
        }
    }
}
