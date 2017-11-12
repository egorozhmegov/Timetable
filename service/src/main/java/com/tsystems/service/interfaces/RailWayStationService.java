package com.tsystems.service.interfaces;

import com.tsystems.model.RailWayStation;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RailWayStationService {

    /**
     * Get all stations.
     *
     * @return List<RailWayStation>
     */
    List<RailWayStation> getAllStations();
}
