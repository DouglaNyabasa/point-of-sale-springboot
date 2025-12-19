package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.mapper.ShiftReportMapper;
import com.doug.pointofsale.models.*;
import com.doug.pointofsale.payload.dto.ShiftReportDTO;
import com.doug.pointofsale.repository.OrderRepository;
import com.doug.pointofsale.repository.RefundRepository;
import com.doug.pointofsale.repository.ShiftReportRepository;
import com.doug.pointofsale.service.ShiftReportService;
import com.doug.pointofsale.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShiftReportImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;

    public ShiftReportImpl(ShiftReportRepository shiftReportRepository, UserService userService, RefundRepository refundRepository, OrderRepository orderRepository) {
        this.shiftReportRepository = shiftReportRepository;
        this.userService = userService;
        this.refundRepository = refundRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception {
        User currentUser = userService.getCurrentUser();
        shiftStart = LocalDateTime.now();
        LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = shiftStart.withHour(23).withMinute(59).withSecond(59);
        Optional<ShiftReport> existing = shiftReportRepository.findTopByCashierAndShiftStartBetween(
                currentUser, startOfDay, endOfDay);
        if (existing.isPresent()) {
            throw new Exception("Shift report already exists");
        }
        Branch branch = currentUser.getBranch();

        ShiftReport shiftReport = new ShiftReport();
        shiftReport.setCashier(currentUser);
        shiftReport.setShiftStart(startOfDay);
        shiftReport.setBranch(branch);
        ShiftReport savedReport =  shiftReportRepository.save(shiftReport);


        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
        User currentUser = userService.getCurrentUser();

        ShiftReport shiftReport = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser).orElseThrow(
                ()-> new Exception("Shift report not found"));

        shiftReport.setShiftEnd(shiftEnd);
        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(

             currentUser.getId(), shiftReport.getShiftStart(), shiftReport.getShiftEnd()
        );
        double totalRefunds = refunds.stream()
                .mapToDouble(refund -> refund.getAmount() != null ? refund.getAmount() : 0.0).sum();

        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(
                currentUser,shiftReport.getShiftStart(),shiftReport.getShiftEnd()
        );
        double totalSales = orders.stream()
                .mapToDouble(Order::getTotalAmount).sum();

        int totalOrders = orders.size();

        double netSales = totalSales - totalRefunds;
        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setNetSales(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders,totalSales));
        shiftReport.setRefunds(refunds);

        return null;

    }



    @Override
    public ShiftReportDTO getShiftReportById(Long shiftReportId) {
        return null;
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public ShiftReportDTO getCurrentShiftReportsProgress(Long cashierId) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        return null;
    }

    private List<PaymentSummary> getPaymentSummaries(List<Order> orders, double totalSales) {
        Map<PaymentType,List<Order>> grouped = orders.stream().collect(Collectors.groupingBy(order -> order.getPaymentType()!= null? order.getPaymentType(): PaymentType.CASH));
        List<PaymentSummary> summaries = new ArrayList<>();
        for (Map.Entry<PaymentType,List<Order>> entry : grouped.entrySet()) {
            double amount = entry.getValue().stream()
                    .mapToDouble(Order::getTotalAmount).sum();
        }

    }

    private List<Product> getTopSellingProducts(List<Order> orders) {
        Map<Product,Integer> productSalesMap = new HashMap<>();
        for (Order order : orders) {
            for (OrderItem item : order.getItems()) {
                Product product = item.getProduct();
                productSalesMap.put(product, productSalesMap.getOrDefault(product, 0) + item.getQuantity());
            }
        }
        return productSalesMap.entrySet().stream().sorted((a,b)-> b.getValue().compareTo(a.getValue())).limit(5).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private List<Order> getRecentOrders(List<Order> orders) {
      return orders.stream().sorted(Comparator.comparing(Order::getCreatedAt).reversed())
              .limit(5).collect(Collectors.toList());
    }
}
