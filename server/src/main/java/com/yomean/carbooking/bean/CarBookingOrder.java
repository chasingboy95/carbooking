package com.yomean.carbooking.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@ApiModel("car booking order")
public class CarBookingOrder {

    private Long id;

    private Long carId;

    private Long userId;

    private LocalDateTime startTime;

    private LocalDateTime estimatedEndTime;

    private LocalDateTime actualEndTime;

    private int status;
}
