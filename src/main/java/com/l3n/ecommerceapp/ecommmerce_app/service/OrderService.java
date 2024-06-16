package com.l3n.ecommerceapp.ecommmerce_app.service;

import com.l3n.ecommerceapp.ecommmerce_app.entity.*;
import com.l3n.ecommerceapp.ecommmerce_app.exception.ResourceNotFoundException;
import com.l3n.ecommerceapp.ecommmerce_app.model.CreateOrderRequest;
import com.l3n.ecommerceapp.ecommmerce_app.model.CreateOrderResponse;
import com.l3n.ecommerceapp.ecommmerce_app.model.OrderHistoryResponse;
import com.l3n.ecommerceapp.ecommmerce_app.model.OrderItemResponse;
import com.l3n.ecommerceapp.ecommmerce_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User dengan id " + userId + " tidak ditemukan"));

        List<Cart> carts = cartRepository.findAllById(request.getCartId());

        Order order = new Order();
        order.setUser(user);
        order.setAddress(request.getAddress());
        order.setDateOrder(new Date());
        order.setTotal(BigDecimal.ZERO);
        order.setOrderItems(new ArrayList<>());
        order = orderRepository.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (Cart cart : carts) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setCart(cart);
            orderItem = orderItemRepository.save(orderItem);

            // Update stock
            Product product = cart.getProduct();
            product.setStock(product.getStock() - cart.getQuantity());
            productRepository.save(product);

            totalAmount = totalAmount.add(cart.getTotal());

            // Kosongkan cart
            cartRepository.delete(cart);
        }

        order.setTotal(totalAmount);
        orderRepository.save(order);

        // Simpan informasi pembayaran
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod("BANK_TRANSFER");
        payment.setAmount(totalAmount);
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus("PENDING");
        payment.setBank(request.getBank());
        paymentRepository.save(payment);

        // Prepare response
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderId(order.getId());
        response.setTotal(order.getTotal());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setBank(payment.getBank());

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setProductId(item.getCart().getProduct().getId());
            itemResponse.setProductName(item.getCart().getProduct().getName());
            itemResponse.setQuantity(item.getCart().getQuantity());
            itemResponse.setPrice(item.getCart().getPrice());
            itemResponse.setTotal(item.getCart().getTotal());
            itemResponses.add(itemResponse);
        }
        response.setItems(itemResponses);

        return response;
    }

    public List<OrderHistoryResponse> getHistoryOrder(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderHistoryResponse> historyResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryResponse historyResponse = new OrderHistoryResponse();
            historyResponse.setOrderId(order.getId());
            historyResponse.setAddress(order.getAddress());
            historyResponse.setDateOrder(order.getDateOrder());
            historyResponse.setTotal(order.getTotal());

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for (OrderItem item : order.getOrderItems()) {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProductId(item.getCart().getProduct().getId());
                itemResponse.setProductName(item.getCart().getProduct().getName());
                itemResponse.setQuantity(item.getCart().getQuantity());
                itemResponse.setPrice(item.getCart().getPrice());
                itemResponse.setTotal(item.getCart().getTotal());
                itemResponses.add(itemResponse);
            }
            historyResponse.setItems(itemResponses);

            Payment payment = paymentRepository.findByOrderId(order.getId());
            historyResponse.setPaymentStatus(payment.getPaymentStatus());
            historyResponse.setBank(payment.getBank());

            historyResponses.add(historyResponse);
        }

        return historyResponses;
    }
}
