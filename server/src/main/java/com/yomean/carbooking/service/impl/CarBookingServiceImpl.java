package com.yomean.carbooking.service.impl;

import com.yomean.carbooking.bean.Car;
import com.yomean.carbooking.bean.CarBookingOrder;
import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.bean.vo.CarBookingOrderVo;
import com.yomean.carbooking.common.ReturnCode;
import com.yomean.carbooking.common.ServiceException;
import com.yomean.carbooking.dao.CarBookingOrderDao;
import com.yomean.carbooking.service.CarBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
     *
     * @param carBookingOrder
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addBookingOrder(CarBookingOrder carBookingOrder) {
        if (carBookingOrder.getEstimatedEndTime().isBefore(LocalDate.now())) {
            throw new ServiceException(ReturnCode.START_TIME_IS_AFTER_END_TIME);
        }
        // try to add lock on record
        Car car = carService.getByIdWithLock(carBookingOrder.getCarId());
        User user = userService.getById(carBookingOrder.getUserId());
        if (Objects.isNull(car)) {
            throw new ServiceException(ReturnCode.EMPTY_CAR);
        }
//        if (car.getStatus() != 0) { // other thread are modify
//            throw new ServiceException(ReturnCode.SYSTEM_BUSY_TRY_LATE);
//        }
        if (Objects.isNull(user)) {
            throw new ServiceException(ReturnCode.EMPTY_USER);
        }
        carBookingOrder.setStatus(1);
        int add = carBookingOrderDao.add(carBookingOrder);
        return add > 0;
    }

    /**
     * update bookingOrder info
     *
     * @param carBookingOrder
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBookingOrder(CarBookingOrder carBookingOrder) {
        if (carBookingOrder.getEstimatedEndTime().isBefore(LocalDate.now())) {
            throw new ServiceException(ReturnCode.TIME_IS_BEFORE_NOW);
        }
        CarBookingOrder bookingOrder = carBookingOrderDao.getById(carBookingOrder.getCarId());
        if (bookingOrder.getStatus() == 2) {
            throw new ServiceException(ReturnCode.FINISHED_ORDER);
        }
        return carBookingOrderDao.update(carBookingOrder) > 0;
    }

    /**
     * finish this order(show be paid)
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean finishRentalOrder(Long id) {
        CarBookingOrder bookingOrder = carBookingOrderDao.getById(id);
        if (bookingOrder.getStatus() == 2) {
            throw new ServiceException(ReturnCode.FINISHED_ORDER);
        }
        bookingOrder.setStatus(2);
        bookingOrder.setActualEndTime(LocalDate.now());
        carBookingOrderDao.update(bookingOrder);
        return true;
    }

    /**
     * compute order should pay, if now is before EstimatedEndTime, compute with EstimatedEndTime,
     * else compute with now
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public double getBillById(Long id) {
        CarBookingOrder bookingOrder = carBookingOrderDao.getById(id);
        Car car = carService.getById(bookingOrder.getCarId());
        LocalDate now = LocalDate.now();
        double bill;
        if (now.isBefore(bookingOrder.getEstimatedEndTime())) {
            bill = getDaysOfTwo(bookingOrder.getStartTime(), bookingOrder.getEstimatedEndTime()) * car.getPrice();
        } else {
            bill = getDaysOfTwo(bookingOrder.getStartTime(), now) * car.getPrice();
        }
        return (double) Math.round(bill * 100) / 100;
    }

    @Override
    public List<CarBookingOrderVo> getOrderVoByUserId(Long userId) {
        return carBookingOrderDao.getOrderVoByUserId(userId);
    }

    @Override
    public List<CarBookingOrder> getOrderByCarId(Long carId) {
        return carBookingOrderDao.getActiveOrderByCarId(carId, LocalDate.now());
    }


    @Override
    public List<LocalDate> getBookedData(Long carId) {
        List<CarBookingOrder> orders = getOrderByCarId(carId);
        HashSet<LocalDate> set = new HashSet<>();
        orders.forEach(order -> {
            LocalDate startTime = order.getStartTime();
            LocalDate estimatedEndTime = order.getEstimatedEndTime();
            while (startTime.isBefore(estimatedEndTime)) {
                set.add(startTime);
                startTime = startTime.plusDays(1);
            }
            set.add(startTime);
        });
        return new ArrayList<>(set);
    }

    /**
     * compute days duration of two LocalDateTime
     *
     * @param start
     * @param end
     * @return
     */
    private int getDaysOfTwo(LocalDate start, LocalDate end) {
        Period between = Period.between(start, end);
        return between.getDays();
    }

}
