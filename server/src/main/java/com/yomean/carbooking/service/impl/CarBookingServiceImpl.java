package com.yomean.carbooking.service.impl;

import com.yomean.carbooking.bean.Car;
import com.yomean.carbooking.bean.CarBookingOrder;
import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.common.ReturnCode;
import com.yomean.carbooking.common.ServiceException;
import com.yomean.carbooking.dao.CarBookingOrderDao;
import com.yomean.carbooking.service.CarBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CarBookingServiceImpl implements CarBookingService {

    @Autowired
    private CarBookingOrderDao carBookingOrderDao;
    @Autowired
    private CarServiceImpl carService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * new order
     * @param carBookingOrder
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addBookingOrder(CarBookingOrder carBookingOrder) {
        Car car = carService.getById(carBookingOrder.getCarId());
        User user = userService.getById(carBookingOrder.getUserId());
        if (Objects.isNull(car)) {
            throw new ServiceException(ReturnCode.EMPTY_CAR);
        }
        if (Objects.isNull(user)) {
            throw new ServiceException(ReturnCode.EMPTY_USER);
        }
        if (car.getStatus() != 0) {
            throw new ServiceException(ReturnCode.BEEN_BOOKED);
        }
        car.setStatus(1);
        carService.updateCar(car);
        carBookingOrder.setStatus(1);
        int add = carBookingOrderDao.add(carBookingOrder);
        return add > 0;
    }

    /**
     * update bookingOrder info
     * @param carBookingOrder
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBookingOrder(CarBookingOrder carBookingOrder) {
        CarBookingOrder bookingOrder = carBookingOrderDao.getById(carBookingOrder.getCarId());
        if (bookingOrder.getStatus() == 2) {
            throw new ServiceException(ReturnCode.FINISHED_ORDER);
        }
        return carBookingOrderDao.update(carBookingOrder) > 0;
    }

    /**
     * finish this order(show be paid)
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean finishRentalOrder(Long id) {
        CarBookingOrder bookingOrder = carBookingOrderDao.getById(id);
        Car car = carService.getById(bookingOrder.getCarId());
        if (bookingOrder.getStatus() == 2) {
            throw new ServiceException(ReturnCode.FINISHED_ORDER);
        }
        bookingOrder.setStatus(2);
        bookingOrder.setActualEndTime(LocalDateTime.now());
        carBookingOrderDao.update(bookingOrder);
        car.setStatus(0);
        carService.updateCar(car);
        return true;
    }

    /**
     * compute order should pay, if now is before EstimatedEndTime, compute with EstimatedEndTime,
     * else compute with now
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public double getBillById(Long id) {
        CarBookingOrder bookingOrder = carBookingOrderDao.getById(id);
        Car car = carService.getById(bookingOrder.getCarId());
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(bookingOrder.getEstimatedEndTime())) {
            return getDaysOfTwo(bookingOrder.getStartTime(), bookingOrder.getEstimatedEndTime()) * car.getPrice();
        } else {
            return getDaysOfTwo(bookingOrder.getStartTime(), now) * car.getPrice();
        }
    }

    /**
     * compute days duration of two LocalDateTime
     * @param start
     * @param end
     * @return
     */
    private int getDaysOfTwo(LocalDateTime start, LocalDateTime end) {
        return (int) Duration.between(start, end).toDays();
    }

}
