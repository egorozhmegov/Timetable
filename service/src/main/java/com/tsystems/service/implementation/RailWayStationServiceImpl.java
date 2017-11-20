package com.tsystems.service.implementation;

import com.tsystems.dao.interfaces.RailWayStationDao;
import com.tsystems.model.RailWayStation;
import com.tsystems.service.interfaces.RailWayStationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "railWayStationService")
public class RailWayStationServiceImpl implements RailWayStationService {

    /**
     * Get all stations.
     *
     * @return List<RailWayStation>
     */
    @EJB
    private RailWayStationDao railWayStationDao;

    @Override
    public List<RailWayStation> getAllStations() {
        return railWayStationDao.getAllStations();
    }

    /**
     * Get RailWayStation by title.
     *
     * @param title String.
     * @return RailWayStation.
     */
    @Override
    public RailWayStation getStationByTitle(String title){
        return railWayStationDao.getStationByTitle(title);
    }
}
