package agk.wow.carrental.service;

import agk.wow.carrental.model.Location;
import agk.wow.carrental.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getLocations() {
        return (List<Location>) this.locationRepository.findAll();
    }
}