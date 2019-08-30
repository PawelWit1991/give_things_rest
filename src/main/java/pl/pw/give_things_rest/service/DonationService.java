package pl.pw.give_things_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pw.give_things_rest.model.Donation;
import pl.pw.give_things_rest.repository.DonationRepository;
import pl.pw.give_things_rest.repository.ItemRepository;
import pl.pw.give_things_rest.repository.ProfileRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class DonationService {


    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public Donation save(Donation donation){
       donation.setItem(itemRepository.save(donation.getItem()));
       donation.setProfile(profileRepository.save(donation.getProfile()));
        return donationRepository.save(donation);
    }

    public Donation getById(Long id) {
        return donationRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
    }

//    public Donation update(Donation donation){
//
//        return donationRepository.findById(donation.getId()).map(donationdb ->{
//
//            if(donation.getAddress()!=null){
//                donationdb.setAddress(donation.getAddress());
//            }
//            if(donation.getCity()!=null){
//                donationdb.setCity(donation.getCity());
//            }
//
//    }
}
