package com.yomean.carbooking.common;

import lombok.Data;

/**
 * @Author hym
 * @Date 2022-06-15 21:44
 * @Version 1.0
 * @Description
 */
@Data
public class PageParam {
    private int curPage = 1;
    private int pageSize = 15;
}
