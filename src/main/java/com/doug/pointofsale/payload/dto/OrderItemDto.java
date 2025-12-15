package com.doug.pointofsale.payload.dto;

import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.models.Order;
import com.doug.pointofsale.models.Product;
import jakarta.persistence.ManyToOne;

public class OrderItemDto {


    private Long id;

    private Integer quantity;
    private Double price;
    private ProductDTO product;

    private Long orderId;

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
