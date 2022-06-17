package com.yomean.carbooking.bean.condition;

import lombok.Data;

import java.util.List;

@Data
public class CarQC {

    private Long id;

    private List<Long> idList;

    private String name;
}
