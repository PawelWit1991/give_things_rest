package pl.pw.give_things_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pw.give_things_rest.model.Location;
import pl.pw.give_things_rest.repository.LocationRepository;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;


    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Location update(Location location) {
        return locationRepository.findById(location.getId()).map(locationdb -> {

            if (location != null) {
                locationdb.setName(location.getName());
            }
            return locationRepository.save(location);
        }).orElse(new Location());
    }

    public Location findById(long id) {
        return locationRepository.findById(id).orElse(new Location());
    }


    public void delete(long id) {
        locationRepository.deleteById(id);
    }
}
