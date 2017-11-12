package com.tsystems.dao.interfaces;

import com.tsystems.model.RailWayStation;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RailWayStationDao {

    /**
     * Get all stations.
     *
     * @return List<RailWayStation>
     */
    List<RailWayStation> getAllStations();
}
