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
import java.util.stream.Collectors;

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
      Branch   branch = branchRepository.findById(branchId).orElseThrow(
              () -> new Exception("Branch not found")
      );
      if (employee.getRole() == UserRole.ROLE_BRANCH_CASHIER || employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
          User user = UserMapper.toEntity(employee);
          user.setBranch(branch);
          user.setPassword(passwordEncoder.encode(employee.getPassword()));
          return UserMapper.toDTO(userRepository.save(user));
      }
        throw new Exception("Branch role not supported");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDTO employeeDetails) throws Exception {
        User existingEmployee = userRepository.findById(employeeId).orElseThrow(
                ()-> new  Exception("employee does not exist with given id")
        );
        Branch branch = branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                ()-> new Exception("branch not found")
        );

        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);
        return userRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
         User user = userRepository.findById(employeeId).orElseThrow(
                 ()-> new Exception("User Not Found")
         );
         userRepository.delete(user);

    }

    @Override
    public List<UserDTO> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found")
        );

        return userRepository.findByStore(store)
                .stream()
                .filter(user -> role == null || user.getRole() == role)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                ()-> new Exception (" branch not found")
        );


        return userRepository.findByBranchId(branchId)
                .stream().filter(
                        user -> role == null || user.getRole() == role
                )
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());

    }
}
