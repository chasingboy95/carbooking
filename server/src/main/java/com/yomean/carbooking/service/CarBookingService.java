package com.yomean.carbooking.service;

import com.yomean.carbooking.bean.CarBookingOrder;

public interface CarBookingService {

    boolean addBookingOrder(CarBookingOrder carBookingOrder);

    boolean updateBookingOrder(CarBookingOrder carBookingOrder);

    boolean finishRentalOrder(Long id);

    double getBillById(Long id);

}
