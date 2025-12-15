package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.domain.UserRole;
import com.doug.pointofsale.mapper.UserMapper;
import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDTO;
import com.doug.pointofsale.repository.BranchRepository;
import com.doug.pointofsale.repository.StoreRepository;
import com.doug.pointofsale.repository.UserRepository;
import com.doug.pointofsale.service.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(UserRepository userRepository, StoreRepository storeRepository, BranchRepository branchRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createStoreEmployee(UserDTO employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found")
        );
        Branch branch = null;
        if(employee.getRole() == UserRole.ROLE_STORE_MANAGER){
            if (employee.getBranchId() == null) {
                throw new Exception("Branch is required to create branch manager");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                    () -> new Exception("Branch not found")
            );
        }
        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee = userRepository.save(user);
        if (employee.getRole() == UserRole.ROLE_STORE_MANAGER && branch != null) {
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDTO createBranchEmployee(UserDTO employee, Long branchId) throws Exception {
        return null;
    }

    @Override
    public User updateEmployee(Long employeeId, User employeeDetails) {
        return null;
    }

    @Override
    public void deleteEmployee(Long employeeId) {

    }

    @Override
    public List<User> findStoreEmployees(Long storeId, UserRole role) {
        return List.of();
    }

    @Override
    public List<User> findBranchEmployees(Long branchId, UserRole role) {
        return List.of();
    }
}
