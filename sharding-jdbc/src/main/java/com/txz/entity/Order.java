package com.txz.entity;

import lombok.Data;

/**
 * Created by Taxz on 2018/12/27.
 */
@Data
public class Order {

    private Long id;
    private Long orderId;
    private Long userId;
    private String product;
    private String number;
    private String price;
    private String address;
}
