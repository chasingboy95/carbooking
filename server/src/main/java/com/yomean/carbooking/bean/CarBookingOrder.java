package com.yomean.carbooking.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("car booking order")
public class CarBookingOrder {

    private Long id;

    private Long carId;

    private Long userId;

    private Timestamp startTime;

    private Timestamp estimatedEndTime;

    private Timestamp actualEndTime;

    private int status;
}
