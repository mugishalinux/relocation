package com.relocation.relocation.Service;


import com.relocation.relocation.Model.Relocation;
import com.relocation.relocation.Repository.relocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelocationServiceImpl implements RelocationService {

    @Autowired
    relocationRepository relocationRepository;

    @Override
    public Relocation registerRelocation(Relocation relocation){
        return relocationRepository.save(relocation);
    }
    @Override
    public Relocation updateRelocation(Relocation relocation) {
        Relocation theRelocation = findRelocationBySerialNumber(relocation);
        if(theRelocation != null){
            theRelocation.setSerialNumber(relocation.getSerialNumber());
            theRelocation.setNewAddress(relocation.getNewAddress());
            theRelocation.setTelNo(relocation.getTelNo());
            theRelocation.setPicture(relocation.getPicture());
            theRelocation.setEligibility(relocation.getEligibility());
            theRelocation.setTxid(relocation.getTxid());
            theRelocation.setSchedule(relocation.getSchedule());
            return relocationRepository.save(theRelocation);
        }else{
            return registerRelocation(relocation);
        }
    }
    @Override
    public void deleteRelocation(Relocation relocation) {
        relocationRepository.delete(relocation);
    }

    @Override
    public List<Relocation> relocationList() {
        return relocationRepository.findAll();
    }

    @Override
    public Relocation findRelocationBySerialNumber(Relocation relocation) {
        return relocationRepository.findById(relocation.getSerialNumber()).get();
    }
}
