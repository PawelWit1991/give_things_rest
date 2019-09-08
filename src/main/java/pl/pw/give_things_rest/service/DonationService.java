package pl.pw.give_things_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import pl.pw.give_things_rest.model.Donation;
import pl.pw.give_things_rest.model.User;
import pl.pw.give_things_rest.repository.DonationRepository;
import pl.pw.give_things_rest.repository.ItemRepository;
import pl.pw.give_things_rest.repository.ProfileRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.awt.print.Pageable;

@Service
@Transactional
public class DonationService {


    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public Donation save(Donation donation) {
        donation.setItem(itemRepository.save(donation.getItem()));
        donation.setProfile(profileRepository.save(donation.getProfile()));
        return donationRepository.save(donation);
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
            if (donation.getProfile() != null) {
                donationdb.setProfile(donation.getProfile());
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
            if (donation.getPickupTime() != null) {
                donationdb.setPickupTime(donation.getPickupTime());
            }
            if (donation.getUser() != null) {
                donationdb.setUser(donation.getUser());
            }
            return donationRepository.save(donation);
        }).orElse(new Donation());

    }
}