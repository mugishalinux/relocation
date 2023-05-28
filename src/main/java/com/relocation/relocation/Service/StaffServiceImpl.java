package com.relocation.relocation.Service;

import com.relocation.relocation.Model.Staff;
import com.relocation.relocation.Repository.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    staffRepository staffRepository;

    @Override
    public Staff registerStaff(Staff staff){ return staffRepository.save(staff); }

    @Override
    public Staff updateStaff(Staff staff){
        Staff theStaff = findStaffByUsername(staff);
        if (theStaff != null){
            theStaff.setUsername(staff.getUsername());
            theStaff.setPassword(staff.getPassword());
            theStaff.setAccountType(staff.getAccountType());
            return staffRepository.save(theStaff);
        }else {
            return registerStaff(staff);
        }
    }

    @Override
    public void deleteStaff(Staff staff){ staffRepository.delete(staff); }

    @Override
    public List<Staff> staffList(){ return staffRepository.findAll(); }

    @Override
    public Staff findStaffByUsername(Staff staff){
        return staffRepository.findById(staff.getUsername()).get();
    }
}
