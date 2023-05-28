package com.relocation.relocation.Service;

import com.relocation.relocation.Model.Staff;

import java.util.List;

public interface StaffService {

    Staff registerStaff(Staff staff);

    Staff updateStaff(Staff staff);

    void deleteStaff(Staff staff);

    List<Staff> staffList();

    Staff findStaffByUsername(Staff staff);

}
