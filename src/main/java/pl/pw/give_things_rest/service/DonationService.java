package pl.pw.give_things_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import pl.pw.give_things_rest.model.Donation;
import pl.pw.give_things_rest.repository.DonationRepository;
import pl.pw.give_things_rest.repository.InstitutionRepository;
import pl.pw.give_things_rest.repository.ItemRepository;
import pl.pw.give_things_rest.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class DonationService {


    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InstitutionRepository institutionRepository;

    public Donation save(Donation donation, Long userId, Long institutionId) {

        return userRepository.findById(userId).map(user ->
            institutionRepository.findById(institutionId).map(institution -> {
                donation.setItem(itemRepository.save(donation.getItem()));
                donation.setUser(user);
                donation.setInstitution(institution);
                return donationRepository.save(donation);
            }).orElseThrow(() -> new EntityNotFoundException("Institution with id " + institutionId + " not found"))
        ).orElseThrow(() -> new EntityNotFoundException("User with with id " + userId + " not found"));

    }

    public Donation getById(Long id) {
        return donationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }


    public Page<Donation> findAll(@PageableDefault org.springframework.data.domain.Pageable pageable) {
        return donationRepository.findAll(pageable);
    }

    public Donation update(Donation donation) {

        return donationRepository.findById(donation.getId()).map(donationdb -> {

            if (donation.getAddress() != null) {
                donationdb.setAddress(donation.getAddress());
            }
            if (donation.getCity() != null) {
                donationdb.setCity(donation.getCity());
            }
            if (donation.getZip() != null) {
                donationdb.setZip(donation.getZip());
            }
            if (donation.getPhone() != null) {
                donationdb.setPhone(donation.getPhone());
            }
            if (donation.getInfo() != null) {
                donationdb.setInfo(donation.getInfo());
            }
            if (donation.getItem() != null) {
                donationdb.setItem(donation.getItem());
            }
            if (donation.getInstitution() != null) {
                donationdb.setInstitution(donation.getInstitution());
            }
            if (donation.getNumberOfBags() != null) {
                donationdb.setNumberOfBags(donation.getNumberOfBags());
            }
            if (donation.getPickupDate() != null) {
                donationdb.setPickupDate(donation.getPickupDate());
            }
            if (donation.getUser() != null) {
                donationdb.setUser(donation.getUser());
            }
            return donationRepository.save(donation);
        }).orElse(new Donation());

    }

    public void delete(Long id) {
        donationRepository.deleteById(id);
    }
}