package com.tsystems.dao.implementation;

import com.tsystems.dao.interfaces.RailWayStationDao;
import com.tsystems.model.RailWayStation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "railWayStationDao")
public class RailWayStationDaoImpl implements RailWayStationDao{

    @PersistenceContext(unitName = "RailwayDS")
    private EntityManager entityManager;

    @Override
    public List<RailWayStation> getAllStations() {
        return entityManager.createQuery("SELECT r FROM RailWayStation r").getResultList();
    }
}
