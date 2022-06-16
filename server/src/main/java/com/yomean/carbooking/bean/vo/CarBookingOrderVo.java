package com.yomean.carbooking.bean.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarBookingOrderVo {

    private Long id;

    private Long carId;

    private String carName;

    private Long userId;

    private Long brandId;

    private String brandName;

    private String plateNumber;

    private LocalDateTime startTime;

    private LocalDateTime estimatedEndTime;

    private LocalDateTime actualEndTime;

    private int status;
}
