package com.yomean.carbooking.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("Car entity")
public class Car {

    @ApiModelProperty("primary key")
    private Long id;

    @ApiModelProperty("car name")
    private String name;

    @ApiModelProperty("car brand")
    private Long brandId;

    @ApiModelProperty("car plate number")
    private String plateNumber;

    @ApiModelProperty("rental price / day")
    private Double price;

    @ApiModelProperty("0-available 1-renting 2-unavailable")
    private Integer status;

    @ApiModelProperty("added time")
    private LocalDateTime createdAt;

}
