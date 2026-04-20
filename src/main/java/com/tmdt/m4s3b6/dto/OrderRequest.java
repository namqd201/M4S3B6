package com.tmdt.m4s3b6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private  String orderId;
    private  String productId;
    private  String productName;
    private  String productPrice;
    private BigDecimal orderAmount;
}
