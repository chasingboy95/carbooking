package com.yomean.carbooking.bean.condition;

import lombok.Data;

@Data
public class CarBookingOrderQC {

    private Long id;

    private Long carId;

    private Long userId;

    private Integer status;
}
