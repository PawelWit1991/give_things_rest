package pl.pw.give_things_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pw.give_things_rest.model.Institution;
import pl.pw.give_things_rest.repository.InstitutionRepository;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    public Institution save(Institution institution) {
        return institutionRepository.save(institution);
    }

    public Institution update(Institution institution) {

        return institutionRepository.findById(institution.getId()).map(institutiondb -> {
            if (institution.getName() != null) {
                institutiondb.setName(institution.getName());
            }
            return institutionRepository.save(institutiondb);
        }).orElse(new Institution());
    }

    public void delete(long id) {
        institutionRepository.deleteById(id);
    }

    public Institution findByInstitutionId(long id) {
        return institutionRepository.findById(id).orElse(new Institution());
    }
}
