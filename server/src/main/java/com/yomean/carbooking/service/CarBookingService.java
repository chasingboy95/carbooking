package com.yomean.carbooking.service;

import com.yomean.carbooking.bean.CarBookingOrder;
import com.yomean.carbooking.bean.vo.CarBookingOrderVo;

import java.util.List;

public interface CarBookingService {

    boolean addBookingOrder(CarBookingOrder carBookingOrder);

    boolean updateBookingOrder(CarBookingOrder carBookingOrder);

    boolean finishRentalOrder(Long id);

    double getBillById(Long id);

    List<CarBookingOrderVo> getOrderVoByUserId(Long userId);

}
