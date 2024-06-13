package com.l3n.ecommerceapp.ecommmerce_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String address;
    private BigDecimal quantity;
    private BigDecimal total;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOrder;
}