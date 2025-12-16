package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.mapper.RefundMapper;
import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.models.Order;
import com.doug.pointofsale.models.Refund;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.RefundDTO;
import com.doug.pointofsale.repository.OrderItemRepository;
import com.doug.pointofsale.repository.OrderRepository;
import com.doug.pointofsale.repository.RefundRepository;
import com.doug.pointofsale.repository.UserRepository;
import com.doug.pointofsale.service.RefundService;
import com.doug.pointofsale.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;

    public RefundServiceImpl(RefundRepository refundRepository, UserRepository userRepository, UserService userService, OrderRepository orderRepository) {
        this.refundRepository = refundRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @Override
    public RefundDTO createRefund(RefundDTO refund) throws Exception {
        User cashier = userService.getCurrentUser();
        Order order = orderRepository.findById(refund.getOrderId()).orElseThrow(
                ()->  new Exception("Order Not Found!!!")
        );
        Branch branch = order.getBranch();

        Refund createRefund = new Refund();
        createRefund.setOrder(order);
        createRefund.setCashier(cashier);
        createRefund.setBranch(branch);
        createRefund.setAmount(refund.getAmount());
        createRefund.setCreatedAt(LocalDateTime.now());
        Refund savedRefund = refundRepository.save(createRefund);


        return RefundMapper.toDTO(savedRefund);
    }

    @Override
    public List<RefundDTO> getAllRefund() throws Exception {
        return refundRepository.findAll().stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception {
        return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashierIdAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return refundRepository.findByCashierIdAndCreatedBetween(
                cashierId,startDate,endDate
        ).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) throws Exception {
        return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RefundDTO getRefundById(Long refundId) throws Exception {
        return refundRepository.findById(refundId).map(RefundMapper::toDTO).orElseThrow(
                ()-> new Exception("Refund Not Found!!!") );
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
        this.getRefundById(refundId);
        refundRepository.deleteById(refundId);

    }
}
