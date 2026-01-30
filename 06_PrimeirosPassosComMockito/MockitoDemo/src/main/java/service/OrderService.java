package service;

import entity.Order;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderService {
    public Order createOrder(String productName, Long amount, String orderId) {
        Order order = new Order();

        order.setId(orderId == null ? UUID.randomUUID().toString() : orderId);
        order.setProductName(productName);
        order.setAmount(amount);
        order.setCreationDate(LocalDateTime.now());
        return order;
    }
}
