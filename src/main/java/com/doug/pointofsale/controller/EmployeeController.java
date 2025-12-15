package com.doug.pointofsale.controller;


import com.doug.pointofsale.domain.UserRole;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDTO;
import com.doug.pointofsale.payload.response.ApiResponse;
import com.doug.pointofsale.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDTO> createStoreEmployee(@PathVariable Long storeId ,@RequestBody UserDTO userDTO) throws Exception {
      UserDTO employee = employeeService.createStoreEmployee(userDTO,storeId);
      return ResponseEntity.ok(employee);
    };

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDTO> createBranchEmployee(@PathVariable Long branchId ,@RequestBody UserDTO userDTO) throws Exception {
        UserDTO employee = employeeService.createBranchEmployee(userDTO,branchId);
        return ResponseEntity.ok(employee);
    };

    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable Long id ,@RequestBody UserDTO userDTO) throws Exception {
        User employee = employeeService.updateEmployee(id,userDTO);
        return ResponseEntity.ok(employee);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> createStoreEmployee(@PathVariable Long id ) throws Exception {
         employeeService.deleteEmployee(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Employee deleted successfully");
        return ResponseEntity.ok(apiResponse);
    };

    @GetMapping("/store/{id}")
    public ResponseEntity<List<User>> storeEmployee(@PathVariable Long id , @RequestParam(required = false)UserRole userRole) throws Exception {
        List<User> employee = employeeService.findStoreEmployees(id,userRole);
        return ResponseEntity.ok(employee);
    };

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<User>> branchEmployee(@PathVariable Long id , @RequestParam(required = false)UserRole userRole) throws Exception {
        List<User> employee = employeeService.findBranchEmployees(id,userRole);
        return ResponseEntity.ok(employee);
    };
}
