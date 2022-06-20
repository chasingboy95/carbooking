package com.yomean.carbooking.service;

import com.github.pagehelper.PageInfo;
import com.yomean.carbooking.bean.Car;
import com.yomean.carbooking.bean.condition.CarQC;
import com.yomean.carbooking.common.PageParam;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

    boolean addCar(Car car);

    boolean updateCar(Car car);

    PageInfo<Car> queryCars(CarQC qc, PageParam page);

    Car getById(Long id);

    Car getByIdWithLock(Long id);
}
