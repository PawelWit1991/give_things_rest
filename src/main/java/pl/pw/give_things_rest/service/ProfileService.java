package pl.pw.give_things_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pw.give_things_rest.model.Profile;
import pl.pw.give_things_rest.repository.ProfileRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile update(Profile profile) {
        return profileRepository.findById(profile.getId()).map(profildb -> {
            if (profile != null) {
                profildb.setName(profile.getName());
            }
            return profileRepository.save(profile);
        }).orElse(new Profile());
    }

    public Profile findById(long id) {
        return profileRepository.findById(id).orElse(new Profile());
    }
    

    public void delete(long id) {
        profileRepository.deleteById(id);
    }
}
