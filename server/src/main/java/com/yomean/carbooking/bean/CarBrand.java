package com.yomean.carbooking.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Car Brand")
public class CarBrand {

    @ApiModelProperty("primary key")
    private Long Id;

    @ApiModelProperty("brand name")
    private String name;
}
