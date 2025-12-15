package com.doug.pointofsale.service;

import com.doug.pointofsale.domain.UserRole;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDTO;

import java.util.List;

public interface EmployeeService {

    UserDTO createStoreEmployee(UserDTO employee,Long storeId) throws Exception;
    UserDTO createBranchEmployee(UserDTO employee,Long branchId) throws Exception;
    User updateEmployee(Long employeeId, UserDTO employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<User> findStoreEmployees(Long storeId, UserRole role);
    List<User> findBranchEmployees(Long branchId, UserRole role);




}
